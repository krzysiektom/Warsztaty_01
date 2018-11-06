package wyszukiwarka_najpopularniejszych_slow;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    final static String[][] WEB_PAGES={{"http://www.onet.pl/","span.title"},
            {"https://www.interia.pl/","a.news-a"},
            {"http://www.neon24.pl/","h2 a"},
            {"https://www.wp.pl/","div.lclzf3-0*"},
            {"http://www.gazeta.pl/0,0.html", "section a"}};
    final static String[] EXCLUDED_WORDS={"oraz","ponieważ"};

    public static void main(String[] words) {
        wordsFromWebPages(WEB_PAGES);
        analysisPopularWord();
    }

    public static void wordsFromWebPages(String[][] web_pages){
        writeToFile(new StringBuilder(),"popular_words.txt", false);
        for (int i = 0; i < web_pages.length; i++) {
            wordsFromPageToFile(web_pages[i][0],web_pages[i][1]);
        }
    }

    public static void wordsFromPageToFile(String url, String cssQuery){
        Connection connect = Jsoup.connect(url);
        try {
            Document document = connect.get();
            Elements links = document.select(cssQuery);
            StringBuilder text = new StringBuilder();
            for (Element elem : links) {
                text.append(elem.text());
                text.append("\n");
            }
            writeToFile(text,"popular_words.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeToFile(StringBuilder text, String fileName, boolean append){
        try {
            FileWriter out = new FileWriter(fileName,append);
            out.append(text.toString());
            out.close();
        }catch (IOException e) {
            System.out.println("Błąd zapisu pliku.");
        }
    }

    static boolean isNotExcludedWord(String word){
        for (String exludedeWord : EXCLUDED_WORDS) {
            if(exludedeWord.equals(word)){
                return false;
            }
        }
        return true;
    }

    static String removeSign(String text){
        String[] sign= {",",".","?",":","\"","!"};
        for (int i = 0; i < sign.length; i++) {
            text=text.replace(sign[i],"");
        }
        return text;
    }

    static void analysisPopularWord(){
        HashMap<String, Integer> popularity=new HashMap<>();
        File file=new File("popular_words.txt");
        try  {
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()){
                String[] lineOfTextTab =removeSign(scanFile.nextLine()).toLowerCase().split(" ");
                for (String word:lineOfTextTab) {
                    if (word.length()>3 && isNotExcludedWord(word) && popularity.putIfAbsent(word,1)!=null){
                        popularity.put(word,popularity.putIfAbsent(word,1)+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Brak pliku.");
        }
        //Sortowanie hashMap
        Stream<Map.Entry<String, Integer>> sorted =
                popularity.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        writeToFile(new StringBuilder(Arrays.toString(sorted.toArray())),"filtered_popular_words.txt",false);
    }


}

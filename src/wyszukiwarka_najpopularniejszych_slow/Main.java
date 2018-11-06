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
    public static void main(String[] words) {
        Connection connect = Jsoup.connect("http://www.onet.pl/");
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            AnailzaZapisDoPliku(links);
        } catch (IOException e) {
            e.printStackTrace();
        }
        analizaPopularWord();



    }

    static void AnailzaZapisDoPliku(Elements links){
        StringBuilder text = new StringBuilder();
        for (Element elem : links) {
            String tempString = elem.text();
            String[] tempStringTab=removeSign(tempString).toLowerCase().split(" ");
            for (String word: tempStringTab) {
                if (word.length()>3){
                    text.append(word).append(" ");
                }                    
            }
            text.append("\n");
        }
        writeToFile(text,"popular_words.txt", false);
    }

    static String removeSign(String text){
        String[] sign= {",",".","?",":","\"","!"};
        for (int i = 0; i < sign.length; i++) {
            text=text.replace(sign[i],"");
        }        
        return text;
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

    static void analizaPopularWord(){
        HashMap<String, Integer> popularity=new HashMap<>();

        File file=new File("popular_words.txt");
        try  {
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()){
                String lineOfText=scanFile.nextLine();
                String[] lineOfTextTab =lineOfText.split(" ");
                for (String word:lineOfTextTab) {
                    if (popularity.putIfAbsent(word,1)!=null){
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
        String objectsArray= Arrays.toString(sorted.toArray());


/*
        String[] slowa=popularity.keySet().toArray(new String[0]);
        Integer[] ilosc=popularity.values().toArray(new Integer[0]);
*/
        StringBuilder textPass=new StringBuilder();
        textPass.append(objectsArray);
/*
        for (int i = 0; i < slowa.length; i++) {
            textPass.append(slowa[i]).append(" ").append(ilosc[i]).append("\n");
        }
*/
        writeToFile(textPass,"filtered_popular_words.txt",false);
    }


}

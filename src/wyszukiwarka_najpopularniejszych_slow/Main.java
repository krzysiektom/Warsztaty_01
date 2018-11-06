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
    public static void main(String[] args) {
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
            String[] tempStringTab=usuwanieZnakow(tempString).toLowerCase().split(" ");
            for (String arg: tempStringTab) {
                if (arg.length()>3){
                    text.append(arg).append(" ");
                }                    
            }
            text.append("\n");
        }
        writeToFile(text,"popular_words.txt");
    }

    static String usuwanieZnakow(String text){
        String[] znaki= {",",".","?",":","\"","!"};
        for (int i = 0; i < znaki.length; i++) {
            text=text.replace(znaki[i],"");
        }        
        return text;
    }

    static void writeToFile(StringBuilder text, String fileName){
        try {
            FileWriter out = new FileWriter(fileName,false);
            out.append(text.toString());
            out.close();
        }catch (IOException e) {
            System.out.println("Błąd zapisu pliku.");
        }
    }

    static void analizaPopularWord(){
        HashMap<String, Integer> popularnosc=new HashMap<>();

        File file=new File("popular_words.txt");
        try  {
            Scanner scanFile = new Scanner(file);
            while (scanFile.hasNextLine()){
                String line=scanFile.nextLine();
                String[] lineTab =line.split(" ");
                for (String arg:lineTab) {
                    if (popularnosc.putIfAbsent(arg,1)!=null){
                        popularnosc.put(arg,popularnosc.putIfAbsent(arg,1)+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Brak pliku.");
        }
        //Sortowanie hashMap
        Stream<Map.Entry<String, Integer>> sorted =
                popularnosc.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
        String objectsArray= Arrays.toString(sorted.toArray());


/*
        String[] slowa=popularnosc.keySet().toArray(new String[0]);
        Integer[] ilosc=popularnosc.values().toArray(new Integer[0]);
*/
        StringBuilder textPass=new StringBuilder();
        textPass.append(objectsArray);
/*
        for (int i = 0; i < slowa.length; i++) {
            textPass.append(slowa[i]).append(" ").append(ilosc[i]).append("\n");
        }
*/
        writeToFile(textPass,"filtered_popular_words.txt");
    }


}

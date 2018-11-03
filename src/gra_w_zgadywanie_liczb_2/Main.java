package gra_w_zgadywanie_liczb_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String ZA_DUZO="za dużo";
        final String ZA_MALO="za mało";
        final String ZGADLES="zgadłeś";

        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 próbach");
        int min=0, max=1000;
        boolean zgadlem=false;
        Scanner scanner=new Scanner(System.in);
        while (!zgadlem) {
            int guess = (max - min) / 2 + min;
            System.out.println("Zgaduję " + guess);
            System.out.println("Wprowadz 'za dużo', 'za mało' lub 'zgadłeś'");

            boolean czyPrawidlowaOdpowiedz=false;
            String odpowiedz="";
            while (!czyPrawidlowaOdpowiedz){
                odpowiedz=scanner.nextLine();
                if(odpowiedz.equals(ZA_DUZO)||odpowiedz.equals(ZA_MALO)||odpowiedz.equals(ZGADLES)){
                    czyPrawidlowaOdpowiedz=true;
                } else {
                    System.out.println("Wprowadz odpowiednią odowiedz");
                }
            }
            switch (odpowiedz) {
                case ZGADLES:
                    zgadlem = true;
                    break;
                case ZA_DUZO:
                    max = guess;
                    break;
                case ZA_MALO:
                    min = guess;
                    break;
            }

        }
        System.out.println("Wygrałem");
    }
}

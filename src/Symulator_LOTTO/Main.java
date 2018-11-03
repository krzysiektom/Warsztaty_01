package Symulator_LOTTO;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] wylosowaneLiczby = new int[6];
        int iloscWylosowanychLiczb=0;
        int wybranaLiczba=0;
        boolean czyJuzIstnieje=false;
        System.out.println("Podaj liczby z zakresu 1-49");
        while (iloscWylosowanychLiczb<6){
            while (!scanner.hasNextInt()){
                System.out.println("Podaj poprawną liczbę");
                scanner.next();
            }
            czyJuzIstnieje=false;
            wybranaLiczba=scanner.nextInt();
            if(wybranaLiczba<1 || wybranaLiczba>49){
                System.out.println("Podaj liczbę z zakresu 1-49");
            } else {
                for (int i = 0; i < iloscWylosowanychLiczb; i++) {
                    if(wybranaLiczba==wylosowaneLiczby[i]){
                        System.out.println("Podana liczba już istniej");
                        czyJuzIstnieje =true;
                        break;
                    }
                }
            }
            if (!czyJuzIstnieje){
                wylosowaneLiczby[iloscWylosowanychLiczb]=wybranaLiczba;
                iloscWylosowanychLiczb++;
            }
        }
        Arrays.sort(wylosowaneLiczby);
        System.out.println(Arrays.toString(wylosowaneLiczby));

        int[] lotto=lotto();
        int iloscTrafien=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(wylosowaneLiczby[i]==lotto[j]){
                    iloscTrafien++;
                }
            }
        }
        if(iloscTrafien==0){
            System.out.println("Trafiłeś "+ iloscTrafien+ " cyfr!");
        } else if(iloscTrafien==1){
            System.out.println("Trafiłeś "+ iloscTrafien+ " cyfrę!");
        } else if(iloscTrafien==2||iloscTrafien==3||iloscTrafien==4){
            System.out.println("Trafiłeś "+ iloscTrafien+ " cyfry");
        } else
        System.out.println("Trafiłeś "+ iloscTrafien+ " cyfr!");
    }

    static int[] lotto(){
        Random random = new Random();
        int[] lotto= new int[6];
        lotto[0]=random.nextInt(49)+1;
        boolean czyIstniej=false;
        int iloscLicz=0;
        while (iloscLicz<6){
            czyIstniej=false;
            int temp=random.nextInt(49)+1;
            for (int i = 0; i < iloscLicz; i++) {
                if (temp==lotto[i]){
                    czyIstniej=true;
                    break;
                }
            }
            if(!czyIstniej){
                lotto[iloscLicz]=temp;
                iloscLicz++;
            }
        }
        Arrays.sort(lotto);
        System.out.println(Arrays.toString(lotto));
        return lotto;
    }
}

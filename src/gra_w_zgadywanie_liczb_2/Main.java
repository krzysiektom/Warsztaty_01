package gra_w_zgadywanie_liczb_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String TOO_MUCH="za dużo";
        final String TOO_LITTLE="za mało";
        final String GUESS="zgadłeś";

        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 próbach");
        int min=0, max=1000;
        boolean isGuess=false;
        Scanner scanner=new Scanner(System.in);
        while (!isGuess) {
            int guess = (max - min) / 2 + min;
            System.out.println("Zgaduję " + guess);
            System.out.println("Wprowadz 'za dużo', 'za mało' lub 'zgadłeś'");

            boolean isCorrectEnteredString=false;
            String enteredString="";
            while (!isCorrectEnteredString){
                enteredString=scanner.nextLine();
                if(enteredString.equals(TOO_MUCH)||enteredString.equals(TOO_LITTLE)||enteredString.equals(GUESS)){
                    isCorrectEnteredString=true;
                } else {
                    System.out.println("Wprowadz odpowiednią odowiedz");
                }
            }
            switch (enteredString) {
                case GUESS:
                    isGuess = true;
                    break;
                case TOO_MUCH:
                    max = guess;
                    break;
                case TOO_LITTLE:
                    min = guess;
                    break;
            }

        }
        System.out.println("Wygrałem");
    }
}

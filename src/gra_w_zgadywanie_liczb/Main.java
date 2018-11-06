package gra_w_zgadywanie_liczb;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random=new Random();
        int randomNumber=random.nextInt(100)+1;
        int enteredNumber=0;
        System.out.println("Zgadnij liczbę w zakresie od 1 do 100");
        boolean isGuess=false;
        while (!isGuess){
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()){
                System.out.println("To nie jest liczba.");
                scanner.next();
            }
            enteredNumber=scanner.nextInt();
            if(enteredNumber==randomNumber){
                isGuess=true;
            }else if(enteredNumber>randomNumber){
                System.out.println("Za dużo!");
                System.out.print("Zgadnij liczbę: ");
            } else {
                System.out.println("Za mało!");
                System.out.print("Zgadnij liczbę: ");
            }
        }
        System.out.println("Zgadłeś");
    }
}

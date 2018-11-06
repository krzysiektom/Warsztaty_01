package symulator_LOTTO;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] enteredNumbers = new int[6];
        int amountEnteredNumbers=0;
        int enteredNumber=0;
        boolean isExists=false;
        boolean isCorrectNumber =true;
        System.out.println("Podaj 6 liczb z zakresu 1-49");
        while (amountEnteredNumbers<6){
            while (!scanner.hasNextInt()){
                System.out.println("Podaj poprawną liczbę");
                scanner.next();
            }
            isExists=false;
            isCorrectNumber=true;
            enteredNumber=scanner.nextInt();
            if(enteredNumber<1 || enteredNumber>49){
                System.out.println("Podaj liczbę z zakresu 1-49");
                isCorrectNumber=false;

            } else {
                for (int i = 0; i < amountEnteredNumbers; i++) {
                    if(enteredNumber==enteredNumbers[i]){
                        System.out.println("Podana liczba już istniej");
                        isExists =true;
                        break;
                    }
                }
            }
            if (!isExists && isCorrectNumber){
                enteredNumbers[amountEnteredNumbers]=enteredNumber;
                amountEnteredNumbers++;
            }
        }
        Arrays.sort(enteredNumbers);
        System.out.println(Arrays.toString(enteredNumbers));

        int[] tableLotto=randomTable();
        System.out.println(Arrays.toString(tableLotto));

        int countHit=0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(enteredNumbers[i]==tableLotto[j]){
                    countHit++;
                }
            }
        }
        switch (countHit) {
            case 0:
                System.out.println("Nie trafiłeś żadnej liczby! - brak wygranych :(");
                break;
            case 1:
                System.out.println("Trafiłeś jedną liczbę! - brak wygranych :(");
                break;
            case 2:
                System.out.println("Trafiłeś dwie liczby! - brak wygranych :(");
                break;
            case 3:
                System.out.println("Trafiłeś trzy liczby! - wygrana :)");
                break;
            case 4:
                System.out.println("Trafiłeś cztery liczby! - wygrana :)");
                break;
            case 5:
                System.out.println("Trafiłeś pięć liczb! - wygrana :)");
                break;
            case 6:
                System.out.println("Trafiłeś sześć liczb! - wygrana :)");
                break;
            default:
                System.out.println("Błąd !!!");
                break;
        }
    }

    static int[] randomTable(){
        Random random = new Random();
        int[] table= new int[6];
        table[0]=random.nextInt(49)+1;
        boolean isExists=false;
        int countNumber=1;
        while (countNumber<6){
            isExists=false;
            int temp=random.nextInt(49)+1;
            for (int i = 0; i < countNumber; i++) {
                if (temp==table[i]){
                    isExists=true;
                    break;
                }
            }
            if(!isExists){
                table[countNumber]=temp;
                countNumber++;
            }
        }
        Arrays.sort(table);
        return table;
    }
}

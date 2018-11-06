package kostka_do_gry;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(dice("2D10+10"));
        System.out.println(dice("D6"));
        System.out.println(dice("2D3"));
        System.out.println(dice("D12-1"));
    }

    public static int dice(String dice){
        int indexOfD=-1;
        int indexOfSign=-1;
        for (int i = 0; i < dice.length(); i++) {
            if(dice.charAt(i)=='D'){
                indexOfD=i;
            }
            if(dice.charAt(i)=='+'||dice.charAt(i)=='-'){
                indexOfSign=i;
            }
        }
        if (indexOfD==-1)
            return Integer.MIN_VALUE;
        if (indexOfD>0 && indexOfSign!=-1){
            int numberOfDice=Integer.valueOf(dice.substring(0,indexOfD));
            int typeOfDice=Integer.valueOf(dice.substring(indexOfD+1,indexOfSign));
            int additionalNumber=Integer.valueOf(dice.substring(indexOfSign));
            return throwTheDice(numberOfDice, typeOfDice, additionalNumber);
        } else if(indexOfD==0 && indexOfSign!=-1){
            int numberOfDice=1;
            int typeOfDice=Integer.valueOf(dice.substring(indexOfD+1,indexOfSign));
            int additionalNumber=Integer.valueOf(dice.substring(indexOfSign));
            return throwTheDice(numberOfDice, typeOfDice, additionalNumber);
        } else if (indexOfD>0 && indexOfSign==-1){
            int numberOfDice=Integer.valueOf(dice.substring(0,indexOfD));
            int typeOfDice=Integer.valueOf(dice.substring(indexOfD+1));
            int additionalNumber=0;
            return throwTheDice(numberOfDice, typeOfDice, additionalNumber);
        } else if(indexOfD==0 && indexOfSign==-1){
            int numberOfDice=1;
            int typeOfDice=Integer.valueOf(dice.substring(indexOfD+1));
            int additionalNumber=0;
            return throwTheDice(numberOfDice, typeOfDice, additionalNumber);
        } else return Integer.MIN_VALUE;
    }

    public static int throwTheDice(int numberOfDice, int typeOfDice, int additionalNumber){
        Random random = new Random();
        int result=0;
        for (int i = 0; i < numberOfDice; i++) {
            result+=random.nextInt(typeOfDice)+1;
        }
        return result+additionalNumber;
    }
}

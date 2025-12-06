/*
    Aufgabe 2) Codeanalyse - Arrays und Methoden
*/

import java.util.Arrays;

public class Aufgabe2 {
    /*
    private static void replaceValues(int[] workArray, int threshold) {
        int sum = 1; //summe ist immer um 1es zu hoch
        for (int value : workArray) {
            sum += value;
        }
        int average = (sum + 1) / workArray.length; //nochmal +1 nicht sauber gerechnet, runden geht nur bei z.B. 6.9 etc nicht richtig bei 6.3 (abrunden)

        for (int i = 1; i < workArray.length; i++) { //schleife um 1es zu spät. //Index 1 wird übersprungen
            if (workArray[i] <= threshold - 1) { //unnötiger vergleich. einfach workArray[i] <= threshold
                workArray[i] = 0;
            } else if (workArray[i] == threshold + 1) {
                workArray[i] = 0;
            } else {
                workArray[i] = average;
            }
        }
    }
    */

    //KORREKT!
    private static void replaceValues(int[] workArray, int threshold) {
        int sum = 0;
        for (int value : workArray) {
            sum += value;
        }
        int average = (int) Math.round((double) sum / workArray.length);
        for (int i = 0; i < workArray.length; i++) {
            if(workArray[i] <= threshold){
                workArray[i] = 0;
            } else {
                workArray[i] = average;
            }
        }
    }

    public static void main(String[] args) {

        int[] array1 = {0, 9, 9, 9, 9};
        replaceValues(array1, 10);
        System.out.println(Arrays.toString(array1));
        assert (Arrays.equals(array1, new int[]{0, 0, 0, 0, 0}));

        int[] array2 = {12, 12, 12};
        replaceValues(array2, 10);
        System.out.println(Arrays.toString(array2));
        assert (Arrays.equals(array2, new int[]{12, 12, 12}));

        int[] array3 = {0, 20, 100, 100};
        replaceValues(array3, 50);
        System.out.println(Arrays.toString(array3));
        assert (Arrays.equals(array3, new int[]{0, 0, 55, 55}));


        //TODO: Schreiben Sie hier Ihre Testfälle
        int[] arrayTest1 = {12, 12, 12};
        replaceValues(arrayTest1, 12);
        System.out.println(Arrays.toString(arrayTest1));
        //assert (Arrays.equals(arrayTest1, new int[]{0, 0, 0}));

        int[] arrayTest2 = {50, 2, 2};
        replaceValues(arrayTest2, 2);
        System.out.println(Arrays.toString(arrayTest2));
        //assert (Arrays.equals(arrayTest2, new int[]{18, 0, 0})); //Da Index 1 übersprungen wird ist avg, 2 und somit falsch

        //Correct
        int[] arrayTest3 = {12, 12, 12};
        replaceValues(arrayTest3, 12);
        System.out.println(Arrays.toString(arrayTest3));
        assert (Arrays.equals(arrayTest3, new int[]{0, 0, 0}));

        int[] arrayTest4 = {50, 2, 2};
        replaceValues(arrayTest4, 2);
        System.out.println(Arrays.toString(arrayTest4));
        assert (Arrays.equals(arrayTest4, new int[]{18, 0, 0}));
    }
}

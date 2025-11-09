/*
    Aufgabe 2) Rekursion
*/
public class Aufgabe2 {

    private static void printEvenNumbersDescending(int end) {
        if(end >= 0){ //Basis
            if(end%2 == 0) {
                System.out.println(end);
            }
            printEvenNumbersDescending(end-1);
        }

    }

    private static int countEqualNeighbors(String text) {
        if(text.length() < 2){//Basis
            return 0;
        }
        int count = 0;
        if(text.charAt(0) == text.charAt(1)){
            count = 1;
        }

        return count + countEqualNeighbors(text.substring(1));

    }

    public static void main(String[] args) {
        printEvenNumbersDescending(14);
        System.out.println();

        System.out.println(countEqualNeighbors("AA"));
        System.out.println(countEqualNeighbors("ABBBCCDDDE"));
        System.out.println(countEqualNeighbors("matt"));
        System.out.println(countEqualNeighbors("Schifffahrt"));
        System.out.println();

        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODE.
        //**********************************************************************
        assert (countEqualNeighbors("AA") == 1);
        assert (countEqualNeighbors("ABBBCCDDDE") == 5);
        assert (countEqualNeighbors("matt") == 1);
        assert (countEqualNeighbors("Schifffahrt") == 2);
        //**********************************************************************
    }
}


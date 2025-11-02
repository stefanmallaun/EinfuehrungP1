/*
    Aufgabe 3) Verschachtelung Schleifen und Methoden - Fröhliche Zahlen
*/
public class Aufgabe3 {

    private static boolean isHappyNumber(int number) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        int currNr = number;
        int ziffer = 0;
        while (currNr != 1 && currNr != 4) { //nur dann unterbrochen da 1 -> Happy, 4 -> Sad

            int summe = 0;
            while (currNr > 0) {
                ziffer = currNr % 10; //um die letzte ziffer einer zahl zu bekommen
                summe += ziffer * ziffer;
                currNr = currNr / 10;
            }
            currNr = summe;
        }
        return currNr == 1;

    }

    private static int countHappyNumbers(int start, int end) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        int count = 0;
        for(int i = start; i <= end; i++){
            if (isHappyNumber(i)){
                count++;
            }
        }
        return count;
    }

    private static void printHappyNumbers(int start, int end) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        for (int i = start; i <= end; i++){
            if (isHappyNumber(i)){
                System.out.print(i);
                if(i < end){
                    System.out.print(" ");
                }
            }
        }
    }

    public static void main(String[] args) {
        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODE isHappyNumber(...) UND countHappyNumbers(...).
        System.out.println("23 --> " + isHappyNumber(23));
        assert (isHappyNumber(23));
        System.out.println("97 --> " + isHappyNumber(97));
        assert (isHappyNumber(97));
        System.out.println("7 --> " + isHappyNumber(7));
        assert (isHappyNumber(7));
        assert (isHappyNumber(1));
        System.out.println("58 --> " + isHappyNumber(58));
        assert (!isHappyNumber(58));
        System.out.println("40 --> " + isHappyNumber(40));
        assert (!isHappyNumber(40));
        System.out.println("5 --> " + isHappyNumber(5));
        assert (!isHappyNumber(5));
        assert (!isHappyNumber(4));
        System.out.println();

        assert(countHappyNumbers(1, 100) == 20);
        assert(countHappyNumbers(50, 250) == 28);
        assert(countHappyNumbers(250, 10000) == 1403);

        //**********************************************************************

        //TODO: Testen Sie hier alle Methoden mit verschiedenen Inputs!
        printHappyNumbers(1,100);
        //printHappyNumbers(50,250);
        //printHappyNumbers(250,10000);
    }

}

/*
    Aufgabe 2) Erste Methoden
*/
public class Aufgabe2 {

    //TODO: Implementieren Sie hier die in der Angabe beschriebenen Methoden

    public static void main(String[] args) {
        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODEN.
        //ENTFERNEN SIE DIE KOMMENTARE, UM IHRE METHODEN ZU TESTEN.

        assert (countDigitsInString("123 wir sind 100 Prozent dabei.") == 6);
        assert (countDigitsInString("keine Ziffern vorhanden!") == 0);
        assert (countDigitsInString("123456789") == 9);

        assert (!isCharOnceInString("", 'a'));
        assert (isCharOnceInString("a", 'a'));
        assert (isCharOnceInString("abc", 'b'));
        assert (!isCharOnceInString("nennenswerte Worte", 'n'));
        assert (isCharOnceInString("nennenswerte Worte", 'o'));
        assert (!isCharOnceInString("nennenswerte Worte", 'z'));

        assert (removeLettersInString("123 wir sind 100 Prozent dabei.").equals("123   100  ."));
        assert (removeLettersInString("keine Ziffern vorhanden!").equals("  !"));
        assert (removeLettersInString("123456789").equals("123456789"));

        //**********************************************************************

        //TODO: Testen Sie hier zusätzlich alle Methoden mit verschiedenen Inputs!
        printNumChars('d', 4);
        printNumbersInInterval(-2,100);

    }
    private static void printNumChars(char startChar, int numChars){
        //Ascii
        for (int i = 0; i < numChars; i++){
            System.out.print(startChar);
            startChar++;
        }
    }
    private static void printNumbersInInterval(int start, int end){
        //alle Zahlen im Intervall ausgeben und Leerzeichen
        for (int i = start; i <= end; i++){
            System.out.print(i);
            if(i < end){
                System.out.print(" ");
            }
        }
    }
    private static int countDigitsInString(String text){
        //Zähle alle Ziffern im text
        int count = 0;
        for (int i = 0; i < text.length(); i++){
            char a = text.charAt(i);
            if(Character.isDigit(a)){
                count++;
            }
        }

        return count;
    }
    private static boolean isCharOnceInString(String text, char character){
        //ist char GENAU einmal in text vorhanden
        int count = 0;
        for (int i = 0; i < text.length(); i++){
            if(text.charAt(i) == character){
                count++;
            }
        }
        return count == 1;
    }

    private static String removeLettersInString(String text){
        //alle Buchstaben entfernen (klein und groß)
        String returnString = "";
        for (int i = 0; i < text.length(); i++){
            if(!Character.isAlphabetic(text.charAt(i))){
                returnString += text.charAt(i);
            }
        }
        return returnString;
    }
}

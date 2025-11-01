/*
    Aufgabe 4) Passwordfinder - Entropieberechnung
*/

import java.util.Scanner;

public class Aufgabe4 {

    private static boolean isValidPassword(String password) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        return false; //Zeile kann geändert oder entfernt werden.
    }

    private static double calculateEntropy(String password) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        return -1.0; //Zeile kann geändert oder entfernt werden.
    }

    public static void main(String[] args) {
        //TODO: Implementieren Sie hier Ihren "Passwordfinder"

        //MIT DEN NACHFOLGENDEN ZEILEN KÖNNEN SIE DIE BEIDEN METHODEN SEPARAT TESTEN!
        /*
        assert (isValidPassword("") == false);
        assert (isValidPassword("BuchstabenAberKeineZiffer") == false);
        assert (isValidPassword("123456789123456789") == false);
        assert (isValidPassword("Passwort12") == false);
        assert (isValidPassword("Passwort1234") == true);
        assert (isValidPassword("passwort1234") == false);
        assert (isValidPassword("Passwort#1234") == false);
        assert (isValidPassword("PasswortKannAuchSehrLangSein123") == true);

        assert (Double.isNaN(calculateEntropy("")) == true);
        assert (calculateEntropy("BuchstabenAberKeineZiffer") == 142.5109929535273);
        assert (calculateEntropy("123456789123456789") == 59.79470570797252);
        assert (calculateEntropy("Passwort12") == 59.54196310386875);
        assert (calculateEntropy("Passwort1234") == 71.45035572464249);
        assert (calculateEntropy("passwort1234") == 62.039100017307746);
        assert (calculateEntropy("PasswortKannAuchSehrLangSein123") == 184.58008562199313);
        */
    }
}

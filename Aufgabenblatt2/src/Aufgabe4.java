/*
    Aufgabe 4) Passwordfinder - Entropieberechnung
*/

import java.util.Scanner;

public class Aufgabe4 {

    private static boolean isValidPassword(String password) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        String requirement = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{12,}$"; //?= Lookaheads(mindestens)
        return password.matches(requirement);
    }

    private static double calculateEntropy(String password) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        int L = password.length();
        int n = 0;
        double entropie;
        if(password.isEmpty()){
            return Double.NaN;
        }
        //Nur Ziffern
        if(password.matches("^[0-9]+$")){
            n = 10;
        }//Nur Groß- oder nur Kleinbuchstaben
        else if (password.matches("^(?:[A-Z]+|[a-z]+)$")) {
            n = 26;
        }//Ziffern und nur ein Typ von Buchstaben
        else if (password.matches("^(?:[a-z0-9]+|[A-Z0-9]+)$")){
            n = 36;
        }//Keine Ziffern, aber Groß- und Kleinbuchstaben
        else if (password.matches("^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z]+$")){
            n = 52;
        }//Ziffern, Groß- und Kleinbuchstaben
        else if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$")){
            n = 62;
        }
        entropie = L*(Math.log10(n)/Math.log10(2));
        return entropie;

    }

    public static void main(String[] args) {
        //TODO: Implementieren Sie hier Ihren "Passwordfinder"
        Scanner reader = new Scanner(System.in);
        String password = "";
        String strength = "";
        do{
            System.out.print("Geben Sie hier Ihr Passwort ein [kein Sonderzeichen und Länge > 11]: ");
            password = reader.next();


        }while (!isValidPassword(password));
        double strengthNr = calculateEntropy(password);
        if(strengthNr < 60){
            strength = "weak";
        }else if(60 <= strengthNr && strengthNr < 120){
            strength = "strong";
        }else{
            strength = "very strong";
        }
        System.out.println("Your password is: " + password);
        System.out.println("Entropy of the password: " + strengthNr + " -> The password is: " + strength);



        //MIT DEN NACHFOLGENDEN ZEILEN KÖNNEN SIE DIE BEIDEN METHODEN SEPARAT TESTEN!

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

    }
}

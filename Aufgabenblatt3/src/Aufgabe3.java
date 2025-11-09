/*
    Aufgabe 3) Rekursion
*/
public class Aufgabe3 {

    private static String checkBrackets(String text) {
        if(text.isEmpty()){//
            return "";
        }
        //Linke Seite so lange einrücken, bis ein '[' kommt
        if(text.charAt(0) != '['){
            return checkBrackets(text.substring(1));
        } else if (text.charAt(text.length()-1) != ']') {//Rechte Seite so lange einrücken, bis ']' kommt
            return checkBrackets(text.substring(0, text.length()-1));
        }else{
            return text.substring(1, text.length()-1);
        }


    }

    public static void main(String[] args) {

        System.out.println(checkBrackets("HGT[CDE]HJH"));
        System.out.println(checkBrackets("aa[[b]"));
        System.out.println(checkBrackets(""));
        System.out.println(checkBrackets("[a"));
        System.out.println(checkBrackets("]b["));
        //DIE NACHFOLGENDEN ZEILEN SIND ZUM TESTEN DER METHODE.
        //**********************************************************************
        assert (checkBrackets("HGT[CDE]HJH").equals("CDE"));
        assert (checkBrackets("aa[[b]").equals("[b"));
        assert (checkBrackets("abc").equals(""));
        assert (checkBrackets("[a").equals(""));
        assert (checkBrackets("]b[").equals(""));
        //**********************************************************************
    }
}

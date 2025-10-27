/*
    Aufgabe 2) while-Schleifen
*/
public class Aufgabe2 {

    public static void main(String[] args) {

        String text = "Eine nennenswerte und geeignete Sprache.";
        //String text = "Anzahl der Zeichen ist nicht genug!";

        //a) Check every 3rd Element, if not 'e' add to result
        int countA = 2;
        String resultA = "";
        while (countA < text.length()){
            if(text.charAt(countA) != 'e'){
                resultA += text.charAt(countA);
            }
            countA +=3;
        }
        System.out.println(resultA);

        //b) dublicate every letter from n-z including upper case
        int countB = 0;
        String resultB = "";
        while(countB < text.length()){
            char c = text.charAt(countB);
            resultB += c;
            if(c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z'){
                resultB += c;
            }
            countB++;
        }
        System.out.println(resultB);

        //c) All odd and divisible by 13 in [13, 130[ with space
        int countC = 13;
        String resultC = "";
        while(countC < 130){
            if(countC % 2 != 0 && countC % 13 == 0){
                if(resultC.length() > 0) {
                    resultC += " ";
                }
                resultC += countC;
            }
            countC++;
        }
        System.out.println(resultC);

    }
}







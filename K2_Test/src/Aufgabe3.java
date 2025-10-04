import java.util.*;

public class Aufgabe3 {

    public static void main(String[] args) {
        //System.out.println(sumUp(8, 8, 40));
        //System.out.println(addMark("Programmieren", "gram", 3)); //Bulletpoint 1
        //System.out.println(addMark("HalloWelt", "Hal", 4));
        //System.out.println(addMark("Programmieren", "ieren", 2));
        //System.out.println(addMark("Programmieren", "adf", 3));
        //System.out.println(digitsToDistance("Vier*mal*vier_=_0"));
        //printPattern(8,5);


    }
    public static int sumUp(int d, int s, int t){
        int counter = 0;
        for(s=s; s < t; s+= s%d ){
            if(s%d == 0){
                s+= 1;
                counter++;
            }
        }
        return counter;
    }
    public static String addMark(String a, String pattern, int pos){
        //when pattern in position pos in Text a
        String Teilstring = a.substring(pos, pos + pattern.length());
        if(Teilstring.equals(pattern)){
            return a.substring(pos);
        }
        //when Text a doesn't start at pos with pattern but anywhere else:
        String Anfangsstring = a.substring(0, pattern.length());
        if(Anfangsstring.equals(pattern)){
            return "--" + a;
        }
        String Endstring = a.substring(a.length() - pattern.length());
        if(Endstring.equals(pattern)){
            return a + "--";
        }
        return "--";
    }
    public static String digitsToDistance(String text){
        int zeiger = 0;
        String returnText = "";
        for(int i = 0; i < text.length(); i++){
            char a =  text.charAt(i);

            if(Character.isDigit(a)){
                String count = text.substring(zeiger, i);
                zeiger = i+1;
                returnText += count + count.length();
            }
        }
        return returnText;
    }
    public static void printPattern(int lineLength, int patternLength){
        //if lineLength/patternLength = depth rounddown
        String pattern = "";
        int depth = lineLength / patternLength;
        int zeiger = 0;

        for(int i = 1; i <= depth; i++){
            for(int j = 0; j < lineLength-(patternLength-1); j++){
                if(j == zeiger){
                    for(int k = 0; k < patternLength; k++){
                        pattern += "?";
                    }
                }else{
                    pattern += "y";
                }
            }
            zeiger +=patternLength;
        pattern += '\n';
        }
        System.out.println(pattern);
    }
}

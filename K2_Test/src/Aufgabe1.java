//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Aufgabe1 {
    public static void main(String[] args) {
        //System.out.println(countDiviosrs(1,28));
        //System.out.println(findDoubles("Schokoladenkuchen"));
        //System.out.println(reverseInsert("qwerty", '-'));
        //printPattern(5, '*');
        short result = (short) countDiviosrs(1,28);
        System.out.println(result);
        String test = "Teststring_Einfungstest";



    }
    public static int countDiviosrs(int first, int last){
        int counter = 0;
        for(int i = first; i <= last; i++){
            if(i%4 == 0 && i%6 != 0){
                counter +=1;
            }
        }


        return counter;
    }

    public static int findDoubles(String text){
        text = text.toLowerCase();
        int counter = 0;
        for (int i = 0; i <= text.length()-1; i++){
            if (text.charAt(i) == 'a' && text.charAt(i+1) == 'a'){
                counter ++;
                i += 2;
            }
        }


        return counter;
    }

    public static String reverseInsert(String text, char charakter){
        String newText = "";
        for(int i = text.length()-1; i >= 0; i--){
            newText = newText + text.charAt(i);
            if(i > 0){
                newText = newText + charakter;
            }
        }
        return newText;
    }

    public static void printPattern(int n, char charakter){
        String pattern = "";

        for(int i = 1; i < n+1; i++){
            if(i % 2 == 0){
               for (int j = 0; j < 10; j++){
                   pattern += charakter;
               }
            }else{
                for (int j = 0; j < 5; j++){
                    pattern +=  charakter + ".";
                }
            }
            pattern += i;
            pattern += '\n';
        }
        System.out.printf(pattern);
    }
}
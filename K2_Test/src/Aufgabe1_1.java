public class Aufgabe1_1 {

    public static void main(String[] args) {
        //System.out.println(countDivisors(8,8));
        //System.out.println(findDoubles("Schokoladenkuchen"));
        //System.out.println(reverseInsert("qwerty", '-'));
        //printPattern(5, '*');
    }

    private static int countDivisors(int x, int y){
        int counter = 0;
        for(int i = x; i <= y; i++){
            if(i %4 == 0 && i%6 != 0){
                counter++;
            }
        }
        return counter;
    }

    private static int findDoubles(String text){
        int counter = 0;
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == 'a' && text.charAt(i+1) == 'a'){
                counter++;
            }
        }
        return counter;
    }
    private static String reverseInsert(String text, char charakter){
        String reverse = "";
        for(int i = text.length()-1; i >= 0; i--){
            reverse += text.charAt(i);
            if(i > 0){
                reverse += charakter;
            }
        }

        return reverse;
    }
    private static void printPattern(int n, char charakter){
        String pattern = "";
        for(int i = 1; i <= n; i++){
            if(i%2 == 0){
                for(int j = 1; j <= n*2; j++){
                    pattern += charakter;
                }
            }else{
                for(int j = 1; j <= n; j++){
                    pattern += charakter;
                    pattern += '.';
                }
            }
            pattern += i;
            pattern += '\n';
        }
        System.out.println(pattern);
    }










}

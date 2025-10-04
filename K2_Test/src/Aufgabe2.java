
public class Aufgabe2 {

    public static void main(String[] args) {
        //System.out.println(getIntegerRoot(1));
        //System.out.println(getThird("blau", "grün", "blau"));
        //System.out.println(replaceA("Hubba bubba!"));
        //printBars(21);
    }

    public static int getIntegerRoot(int k){
        double i = Math.sqrt(k);
        if (i%1 == 0){
            return (int)i;
        }return -1;
    }
    public static String getThird(String a, String b, String c){
        if(!a.equals(b) && !a.equals(c) && !b.equals(c)){
            return "alle unteschiedlich";
        }
        if(a.equals(b) && !a.equals(c)){
            return c;
        }
        if(a.equals(c) && !a.equals(b)){
            return b;
        }
        if(b.equals(c) && !b.equals(a)){
            return a;
        }
        return "alle gleich";
    }
    public static String replaceA(String s){
        int counter = 1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'a'){
                s = s.substring(0,i) + counter + s.substring(i+1);
                counter++;
            }
        }
        return s;
    }
    public static void printBars(int i){
        String s1 = "";
        int counter1 = 0;
        String s2= "";
        int counter2 = 0;
        for (int j = 1; j <= i; j++){

            if(j % 3 != 0){
                s1 += j;
                if(counter1 % 2 == 0){
                    s1 += "-";
                    counter1++;
                }else{
                    s1+= "+";
                    counter1++;
                }
            }
            else{
                s2+=j;
                if(counter2 % 2 == 0){
                    s2+="+";
                    counter2++;
                }else{
                    s2+= "-";
                    counter2++;
                }
            }
        }
        System.out.println(s1);
        System.out.println(s2);
    }
}

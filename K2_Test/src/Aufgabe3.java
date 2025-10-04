public class Aufgabe3 {

    public static void main(String[] args) {
        //System.out.println(sumUp(8, 8, 40));


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
       return "";
    }
}

public class test {


    public static void main(String[] args) {

        System.out.println(calculateSum(14, 57));

    }

    private static int calculateSum(int start, int threshold) {
        int sum = 0;
        sum += start;
        if(sum >= threshold){
            return 0;
        }



        return sum + calculateSum(start +1, threshold - start);


    }
}

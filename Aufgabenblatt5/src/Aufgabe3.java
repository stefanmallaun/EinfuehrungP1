/*
    Aufgabe 3) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class Aufgabe3 {

    private static double[][] genCircleFilter(int n, double radius) {
        //  Überprüfen, ob n != ungerade & <= 1;
        if (n % 2 == 0 || n <= 1) {
            return null;
        }

        //Array generieren
        double[][] filtered = new double[n][n];
        int center = n / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //calc:
                double distance = Math.sqrt(Math.pow(i - center, 2) + Math.pow(j - center, 2));
                if (distance < radius) {
                    filtered[i][j] = 1.00;
                } else {
                    filtered[i][j] = 0.00;
                }
            }
        }
        return filtered;

    }

    /*
    Bei der Kreuzkorrelation wird das Filter Arr mit seinem Mittelpunkt
    auf jedes gültige Feld des workArray gelegt;
    die überlagerten Werte von workArray und filterArray werden paarweise
    multipliziert und aufsummiert – diese Summe ist der Ergebniswert an dieser Position
     */

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        //neues Array mit länge workArray
        double[][] result = new double[workArray.length][workArray[0].length];


        //Mitte von filteredArray
        int filterArrayMidY = filterArray.length / 2;
        int filterArrayMidX = filterArray[0].length / 2;

        //Durchgehen des workArrays
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {

                // Prüfen, ob der Filter hineinpasst
                if (i - filterArrayMidY < 0 || i + filterArrayMidX >= workArray.length ||
                        j - filterArrayMidX < 0 || j + filterArrayMidX >= workArray[0].length) {

                    result[i][j] = 0;
                    continue;

                }

                    double sum = 0.0;

                    //Kreuzrelation berechnen
                    //Durchgehen des filterArray
                    for (int k = 0; k < filterArray.length; k++) {
                        for (int l = 0; l < filterArray[0].length; l++) {

                            //Berechnung wie auf AB blatt.  0*1 + 1*0 + 2*0 +
                            //                              4*1 + 5*2 + 6*0 +
                            //                              8*0 + 9*0 + 10*3
                            int wertY = i + k - filterArrayMidY;
                            int wertX = j + l - filterArrayMidX;

                            sum += workArray[wertY][wertX] * filterArray[k][l];

                        }

                    }
                    result[i][j] = sum;
                }


        }
        return result;

    }

    private static void print(double[][] workArray) {
        if (workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f", workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genCircleFilter(3, 1.2);
        System.out.println("Output -> myFilter1 (genCircleFilter mit size = 3 und radius = 1.2):");
        print(myFilter1);

        double[][] myFilter2 = genCircleFilter(7, 2.5);
        System.out.println("Output -> myFilter2 (genCircleFilter mit size = 7 und radius = 2.5):");
        print(myFilter2);

        double[][] myArray1 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 2, 2, 2, 0}, {0, 3, 3, 3, 0}, {0, 0, 0, 0, 0}};
        System.out.println("Output -> myArray1:");
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        System.out.println("Output -> myFilter1 applied to myArray1:");
        print(myResultArray);

        myResultArray = applyFilter(myArray1, myFilter2);
        System.out.println("Output -> myFilter2 applied to myArray1:");
        print(myResultArray);

        //Beispiel aus Aufgabenblatt
        double[][] myArray3 = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}};
        double[][] myFilter3 = {{1, 0, 0}, {1, 2, 0}, {0, 0, 3}};
        System.out.println("Output -> myArray3:");
        print(myArray3);
        System.out.println("Output -> myFilter3:");
        print(myFilter3);
        myResultArray = applyFilter(myArray3, myFilter3);
        System.out.println("Output -> myFilter3 applied to myArray3:");
        print(myResultArray);

        double[][] myArray4 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 1, 2, 3, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        System.out.println("Output -> myArray4:");
        print(myArray4);
        //TODO: Erstellen Sie den Filter aus dem Aufgabenblatt, wenden Sie ihn auf myArray4 an und geben Sie das Ergebnis mittels print() aus
        double[][] myFilter4 = {{0, 0, 0}, {0, 0, 0}, {0, 0.5, 0}};
        System.out.println("Output -> myFilter4:");
        print(myFilter4);
        myResultArray = applyFilter(myArray4, myFilter4);
        System.out.println("Output -> myFilter4 applied to myArray4:");
        print(myResultArray);
    }
}

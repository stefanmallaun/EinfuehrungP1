/*
    Aufgabe 5) Zweidimensionale Arrays
*/

import java.util.Arrays;

public class Aufgabe5 {

    private static int[][] generateReformattedArray(int[][] inputArray) {

        int col = inputArray.length;
        int sumElemente = 0;
        //initialisieren der länge vom Array (eagerloading)
        for (int[] row : inputArray) {
            sumElemente += row.length;
        }
        int[] einDArray = new int[sumElemente];

        int index = 0;
        //Jedes Element von inputArray in das 1d array geben
        for (int[] row : inputArray) {
            for (int val : row) {
                einDArray[index] = val;
                index++;
            }
        }
        int rows = sumElemente / col;
        //nur eine row hinzufügen wenn rest übrig ist, sonst nicht!!
        if (sumElemente % col != 0) {
            rows++;
        }
        int[][] result = new int[rows][col];
        int zähler = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                //sind noch elemente im 1d Array?
                if (zähler < einDArray.length) {
                    result[i][j] = einDArray[zähler];
                    zähler++;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;

    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int[] arr : inputArray) {
                for (int val : arr) {
                    System.out.print(val + "\t");
                }
                System.out.println();
            }
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int val : inputArray) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] array = new int[][]{{1}};
        System.out.println("Before:");
        printArray(array);
        int[][] arrayRes = generateReformattedArray(array);
        System.out.println("-----");
        System.out.println("After:");
        printArray(arrayRes);
        assert (Arrays.deepEquals(arrayRes, new int[][]{{1}}));
        System.out.println("-----");

        array = new int[][]{{1}, {2, 3, 4, 5, 6, 7}, {8, 9}};
        System.out.println("Before:");
        printArray(array);
        arrayRes = generateReformattedArray(array);
        System.out.println("After:");
        printArray(arrayRes);
        assert (Arrays.deepEquals(arrayRes, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println("-----");

        array = new int[][]{{1}, {3}, {8, 5}, {6, 5, 9}, {10, 4, 7, 11}};
        System.out.println("Before:");
        printArray(array);
        arrayRes = generateReformattedArray(array);
        System.out.println("-----");
        System.out.println("After:");
        printArray(arrayRes);
        assert (Arrays.deepEquals(arrayRes, new int[][]{{1, 3, 8, 5, 6}, {5, 9, 10, 4, 7}, {11, 0, 0, 0, 0}}));
        System.out.println("-----");

        array = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8}, {9, 10, 11}};
        System.out.println("Before:");
        printArray(array);
        arrayRes = generateReformattedArray(array);
        System.out.println("-----");
        System.out.println("After:");
        printArray(arrayRes);
        assert (Arrays.deepEquals(arrayRes, new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8},{9, 10}, {11, 0}}));
        System.out.println("-----");
    }
}

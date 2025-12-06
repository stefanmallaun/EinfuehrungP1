/*
    Aufgabe 4) Zweidimensionale Arrays
*/

import java.util.Arrays;

public class Aufgabe4 {

    private static int[][] generateExtendedArray(int[] inputArray) {
        int[][] result = new int[inputArray[0]][];
        int counter = 1;

        //Zuerst Array initialisieren, eager loading (gegenteil ist lazyloading)
        for (int i = 0; i < inputArray[0]; i++) {
            if (i % 2 == 0) {
                result[i] = new int[inputArray[2]];
            } else {
                result[i] = new int[inputArray[1]];
            }
        }
        int maxCols = Math.max(inputArray[1], inputArray[2]);

        //spalten zuerst durchgehen
        for (int col = 0; col < maxCols; col++) {
            //dann die reihe
            for (int row = 0; row < inputArray[0]; row++) {
                //schauen, ob reihe noch elemente hat
                if (col < result[row].length) {
                    result[row][col] = counter;
                    counter++;
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

        int[] array1 = new int[]{2, 1, 1};
        int[][] array1res = generateExtendedArray(array1);
        printArray(array1res);
        //assert (Arrays.deepEquals(array1res, new int[][]{{1}, {2}}));
        System.out.println("-----");

        array1 = new int[]{4, 3, 0};
        array1res = generateExtendedArray(array1);
        printArray(array1res);
        //assert (Arrays.deepEquals(array1res, new int[][]{{1, 3, 5}, {}, {2, 4, 6}, {}}));
        System.out.println("-----");

        array1 = new int[]{3, 4, 7};
        array1res = generateExtendedArray(array1);
        printArray(array1res);
        //assert (Arrays.deepEquals(array1res, new int[][]{{1, 4, 7, 10}, {2, 5, 8, 11, 13, 14, 15}, {3, 6, 9, 12}}));
        System.out.println("-----");

        array1 = new int[]{7, 4, 2};
        array1res = generateExtendedArray(array1);
        printArray(array1res);
        //assert (Arrays.deepEquals(array1res, new int[][]{{1, 8, 15, 19}, {2, 9}, {3, 10, 16, 20}, {4, 11},
        //                                                 {5, 12, 17, 21}, {6, 13}, {7, 14, 18, 22}}));
        System.out.println("-----");
        System.out.println();
    }
}

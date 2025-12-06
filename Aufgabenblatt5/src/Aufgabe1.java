/*
    Aufgabe 1) Zweidimensionale Arrays
*/

import java.util.Arrays;

public class Aufgabe1 {

    private static void changeRows(int[][] workArray) {
        //Check ob alle gleich lang sind
        if (workArray[0].length == workArray[1].length &&
                workArray[1].length == workArray[2].length) {
            return;
        }


        int saveIndexLongest = 0;
        int saveIndexShortest = 0;
        //schauen, wo das längste und das kürzeste Arr liegt
        for (int i = 1; i < 3; i++) {
            if (workArray[i].length < workArray[saveIndexShortest].length) {
                saveIndexShortest = i;
            }
            if (workArray[i].length > workArray[saveIndexLongest].length) {
                saveIndexLongest = i;
            }
        }

        //falls alle Arr gleich lang sind
        if (saveIndexLongest == saveIndexShortest) {
            return;
        }

        //Längste und kürzeste Vertauschen
        int[] shortest = workArray[saveIndexShortest];
        int[] longest = workArray[saveIndexLongest];
        workArray[0] = shortest;
        workArray[2] = longest;

        //Neues Array erzeugen
        int[] addArray = new int[shortest.length + longest.length];
        int h = 0;
        for (int i = 0; i < shortest.length; i++) {
            addArray[h++] = shortest[i];
        }
        for (int i = 0; i < longest.length; i++) {
            addArray[h++] = longest[i];
        }
        workArray[1] = addArray;
        System.out.println(Arrays.deepToString(workArray));
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Test changeRows:");
        System.out.println("-----");
        int[][] array1 = new int[][]{{1, 6, 9, 5}, {7, 3, 2}, {4, 8}};
        System.out.println("Before:");
        printArray(array1);
        int[] referenceCheck = array1[0];
        changeRows(array1);
        assert referenceCheck == array1[2] : "No reference copy!";
        assert (Arrays.deepEquals(array1, new int[][]{{4, 8}, {4, 8, 1, 6, 9, 5}, {1, 6, 9, 5}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        System.out.println("-----");
        array1 = new int[][]{{7, 8}, {2, 4, 6}, {1, 2, 3, 4}};
        System.out.println("Before:");
        printArray(array1);
        changeRows(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{7, 8}, {7, 8, 1, 2, 3, 4}, {1, 2, 3, 4}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{1, 3, 5}, {2}, {2, 4}};
        System.out.println("Before:");
        printArray(array1);
        changeRows(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{2}, {2, 1, 3, 5}, {1, 3, 5}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{1, 2, 3}, {1, 1}, {2, 1, 2, 1}};
        System.out.println("Before:");
        printArray(array1);
        changeRows(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{1, 1}, {1, 1, 2, 1, 2, 1}, {2, 1, 2, 1}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{3, 4, 1}, {2, 3, 3}, {1, 2, 5}};
        System.out.println("Before:");
        printArray(array1);
        changeRows(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{3, 4, 1}, {2, 3, 3}, {1, 2, 5}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

        array1 = new int[][]{{3, 4, 5}, {6, 7, 8}, {1, 2}};
        System.out.println("Before:");
        printArray(array1);
        changeRows(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{1, 2}, {1, 2, 3, 4, 5}, {3, 4, 5}}));
        System.out.println("-----");
        System.out.println("After:");
        printArray(array1);
        System.out.println("-----");

    }
}

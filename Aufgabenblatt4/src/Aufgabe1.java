import java.util.Arrays;

/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {

    private static void printArray(int[] workArray) {
        for (int i = 0; i < workArray.length; i++) { // statt i = -1 i = 0
            System.out.print(workArray[i] + " "); // hier das i+1 geändert
        }
        System.out.println();
    }

    private static void fillArray(int[] filledArray) {
        int number = 7;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = number;
            number += 7;
        }
    }

        private static void printContentFilteredArray(int[] workArray) {
            int[] copiedArray = workArray; //1
            for (int i = 0; i < copiedArray.length; i++) {
                if (copiedArray[i] % 14 == 0) {
                    copiedArray[i] = -1;
                }
            }
            printArray(copiedArray);
        }

        private static void fillArrayWithNewContent(int[] workArray) {
            int[] helpArray = new int[15];
            int number = 8;
            for (int i = 0; i < helpArray.length; i++) {
                helpArray[i] = number;
                number += 8;
            }
            workArray = helpArray; //D
            printArray(workArray);
        }



    public static void main(String[] args) {
        int[] filledArray = new int[15];
        fillArray(filledArray);
        printArray(filledArray);

        printContentFilteredArray(filledArray);
        printArray(filledArray);

        filledArray[0] = 2412;
        printArray(filledArray);

        fillArrayWithNewContent(filledArray);
        printArray(filledArray);
        int[] a = new int[15];
        a = filledArray;

        if(a == filledArray){
            System.out.println("Richtig");
        }else{
            System.out.println("falsch");
        }
    }

    //***************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ****
    //***************************************************************************
    //Antwort zu Punkt a:
    //Der Index war out of Bounce, das bedeutet das "i+1" war größer wie die erreichbare länge des int[]
    //Antwort zu Punkt b:
    //Die Methode hat keinen Rückgabewert, da sie void ist und hier auf den parameter wert zugegriffen wurde und modifiziert wird. Also es wird über den Parameter auf die Referenz zugegriffen
    //Antwort zu Punkt c:
    // bei 1: Array wird so nicht den Inhalt kopieren sondern nur die Adresse (Zeiger?) im Speicher. => "original" Array wird geändert und somit bekommt man zwei mal den gleichen Wert
    //Antwort zu Punkt d:
    // Bei Zeile 38: Fehler bei der Zuweisung. workArray = helpArray ändert nur wo die lokale Variable zeigt. "filledArray" von Main wird somit nicht geändert. Sichtbarkeit!


    //Zusatzfragen:
    //1: Welchen Datentyp muss der Indexausdruck haben, mit dem die Position in einem Array bestimmt wird?
    //      Der Indexausdruck muss ein Int sein. (Ganzzahl)
    //2: Wie kann die Länge eines Arrays verändert werden?
    //      Man kann die Länge nicht verändern wenn man es beim initialisieren angegeben hat. Nur neues Array würde gehen.
    //3: Wie gehen Sie vor, wenn Sie ein int-Array kopieren müssen?
    //  entweder mit Arrays.copyOf oder .clone();
    //4: Ist es sinnvoll, zwei Arrays mit "==" zu vergleichen? Was passiert im Detail bei einem Vergleich mit "=="?
    //      Bei Referenzvergleichen sinnvoll, sonst nicht. Wenn man zwei Objekte jedoch auf Inhalt vergleichen will, nicht sinnvoll.

}

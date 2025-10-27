/*
    Aufgabe 3) Simulation mit Schleifen
*/

import codedraw.CodeDraw;
import codedraw.Palette;

public class Aufgabe3 {

    public static void main(String[] args) {
        double numNests = 50.0;
        double numBears = 10.0;
        double maxCap = 100.0;

        double r = 0.8; //Wachstumsrate der Bienennester
        double k = 0.045; //Rate, mit der Bienennester von Bären gefressen werden
        double b = 0.17; //Wachstumsrate der Bären durch die Nahrungsaufnahme
        double m = 0.12; //Sterberate der Bären
        double dt = 0.1; //Zeitinkrement in jeder Iteration
        int Iterationen = 1000;
        for(int i = 1; i <= Iterationen; i++){
            double freeCap = maxCap - numNests; //freie Kapazität

            double newNests = numNests + ((1/maxCap) * freeCap * r * numNests - k * numNests * numBears) * dt;
            double newBears = numBears + (b * k * numNests * numBears - m * numBears) * dt;

            numNests = newNests;
            numBears = newBears;

            System.out.format("Iterationen: %d | numNests: %.2f | numBears: %.2f \n", i, newNests , newBears);

            int barLengthNests = (int) Math.round(numNests * 2);
            int barLengthBears = (int) Math.round(numBears * 4);

            barLengthNests = Math.min(barLengthNests, 100);
            barLengthBears = Math.min(barLengthBears, 100);

            for(int j = 0; j < barLengthNests; j++){
                System.out.print("#");
            }
            System.out.println();
            for(int f = 0; f < barLengthBears; f++){
                System.out.print("B");
            }
            System.out.println();
        }
    }
}

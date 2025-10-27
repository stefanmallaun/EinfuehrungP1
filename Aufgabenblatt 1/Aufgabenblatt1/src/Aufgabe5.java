/*
    Aufgabe 5) Schleifen und Zeichnen innerhalb des CodeDraw-Fensters
*/

import codedraw.CodeDraw;
import codedraw.Palette;

public class Aufgabe5 {

    public static void main(String[] args) {

        int pixelWidth = 300;
        int pixelHeight = 300;


        int n = 17;

        if(n >= 5 && n <= 19 && n%2 != 0) {
            CodeDraw myDrawObj = new CodeDraw(pixelWidth, pixelHeight); //hier damit kein Codedraw Fenster geöffnet wird
            //Berechnung w&h von quadraten (wie viele platz haben)
            double boxWidth = pixelWidth / (double) n;
            double boxHeight = pixelHeight / (double) n;

            //Diagonale Boxen von Links oben nach rechts unten ROTE ZUERST
            myDrawObj.setColor(Palette.RED);
            myDrawObj.setLineWidth(4);
            for (int i = 0; i < n; i++) {
                double y = i * boxHeight;
                double x = i * boxWidth;
                myDrawObj.drawRectangle(x, y, boxWidth, boxHeight);

            }

            //Linke Seite oben -1 && Rechte Seite unten -1
            myDrawObj.setColor(Palette.BLUE);
            myDrawObj.setLineWidth(2);
            for (int i = 1; i < n; i++) {
                double y = i * boxHeight;
                double z = (i - 1) * boxHeight;
                myDrawObj.drawRectangle(0, y, boxWidth, boxHeight);
                myDrawObj.drawRectangle(pixelWidth - boxWidth, z, boxWidth, boxHeight);
            }
            myDrawObj.show();
        }else {
            System.out.println("n muss ungerade und zwischen 5 und 19 sein!");
        }
    }
}

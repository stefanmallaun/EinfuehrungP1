/*
    Aufgabe 4) CodeDraw Bibliothek und Schleifen
*/

import codedraw.CodeDraw;
import codedraw.Palette;

public class Aufgabe4 {

    public static void main(String[] args) {

        int pixelWidth = 500;
        int pixelHeight = 500;
        CodeDraw myDrawObj = new CodeDraw(pixelWidth, pixelHeight);

        int numLinesQuadrant = 3;

        myDrawObj.setCanvasPositionX(600);
        myDrawObj.setCanvasPositionX(100);

        for (int i = 0; i < numLinesQuadrant; i++) {
            //Green quadrant top left
            myDrawObj.setColor(Palette.GREEN);
            myDrawObj.drawLine(i * (pixelWidth / 2.0 / numLinesQuadrant), pixelHeight / 2.0, pixelWidth / 2.0, pixelHeight / 2.0 - ((i + 1) * (pixelHeight / 2.0 / numLinesQuadrant)));
            //TODO: Implement ORANGE quadrant (bottom left) here
            myDrawObj.setColor(Palette.ORANGE);//y-achse gespielgelt zu Green
            myDrawObj.drawLine(i * (pixelWidth / 2.0 / numLinesQuadrant), pixelHeight / 2.0, pixelWidth / 2.0, pixelHeight / 2.0 - ((i + 1) * (-pixelHeight / 2.0 / numLinesQuadrant)));
            //TODO: Implement the CYAN quadrant (top right) here
            myDrawObj.setColor(Palette.CYAN);
            myDrawObj.drawLine( pixelWidth / 2.0, i * (pixelHeight / 2.0 / numLinesQuadrant), pixelWidth / 2.0 - ((i + 1) * (-pixelWidth / 2.0 / numLinesQuadrant)), pixelHeight / 2.0);
            //TODO: Implement the MAGENTA quadrant (bottom right) here
            myDrawObj.setColor(Palette.MAGENTA); //y-achse gespiegelt zu Cyan
            myDrawObj.drawLine( pixelWidth / 2.0, pixelHeight - i * (pixelHeight / 2.0 / numLinesQuadrant), pixelWidth / 2.0 - ((i + 1) * (-pixelWidth / 2.0 / numLinesQuadrant)), pixelHeight / 2.0);

        }

        myDrawObj.setLineWidth(2);
        myDrawObj.setColor(Palette.BLACK);
        myDrawObj.drawLine(pixelWidth / 2.0, 0, pixelWidth / 2.0, pixelHeight);//vertical line
        myDrawObj.drawLine(0, pixelHeight / 2.0, pixelWidth, pixelHeight / 2.0);//horizontal line

        myDrawObj.show();
    }
}

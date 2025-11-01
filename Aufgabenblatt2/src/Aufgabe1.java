/*
    Aufgabe 1) Verschachtelte Schleifen - Optische Täuschung
*/

import codedraw.CodeDraw;
import codedraw.Palette;

import java.awt.*;

public class Aufgabe1 {

    public static void main(String[] args) {

        int boxWidth = 600;
        int boxHeight = 400;
        int boxSize = 40;
        CodeDraw myDrawObj = new CodeDraw(boxWidth,boxHeight);
        int col = boxHeight / boxSize;
        int rows = boxWidth/boxSize;
        int offSet = 20;

        for(int i = 0; i < col; i++) {
            for (int j = 0; j < rows; j++) {
                myDrawObj.setColor(Color.black);
                if(i%2 == 0){
                    double x = offSet + j * (boxSize*2);
                    double y = boxSize * i;
                    myDrawObj.fillSquare(x, y, boxSize);

                }else {
                    double x = offSet-10 + j * (boxSize*2);
                    double y = boxSize * i;
                    myDrawObj.fillSquare(x, y, boxSize);
                }

            }
            if(i > 0)  { //Keine weitere überprüfung, da wir immer von der Unterkante (y = boxSize) ausgehen
                myDrawObj.setColor(Color.gray);
                myDrawObj.setLineWidth(2);
                myDrawObj.drawLine(0, boxSize * i, boxWidth, boxSize * i);
            }
        }
        myDrawObj.show();
    }
}

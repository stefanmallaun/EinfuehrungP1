/*
    Aufgabe 4) Quadrate => Rekursiv
*/

import codedraw.*;

import java.awt.*;

public class Aufgabe5 {

    private static void drawRecursiveSquares(CodeDraw myDrawObj, int x, int y, int s) {
        if(s<4){
            return;
        }

        int x0 = x - s/2;
        int x1 = x + s/2;
        int y0 = y - s/2;
        int y1 = y + s/2;
        int s1 = s/2;

        drawRecursiveSquares(myDrawObj, x0, y0, s1);
        drawRecursiveSquares(myDrawObj, x0, y1, s1);
        drawRecursiveSquares(myDrawObj, x1, y0, s1);
        drawRecursiveSquares(myDrawObj, x1, y1, s1);

        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawSquare(x - s1, y - s1, s);
        myDrawObj.setColor(Color.YELLOW);
        myDrawObj.fillSquare(x - s1, y - s1, s);

        myDrawObj.show(500);

    }

    public static void main(String[] args) {
        int cWidth = 512;
        int cHeight = 512;
        CodeDraw myDrawObj = new CodeDraw(cWidth, cHeight);


        drawRecursiveSquares(myDrawObj, 256, 256,256);
        myDrawObj.show();


    }
}




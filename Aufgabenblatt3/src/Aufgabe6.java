/*
    Aufgabe 6) Quadrate => Iterativ
*/

import codedraw.*;

import java.awt.*;

public class Aufgabe6 {

    // Iterative Version => Z.ai GLM4.5v generiert
    public static void drawIterativeSquaresGLM45v(CodeDraw myDrawObj, int width) {

        int k_max = 0;
        int size = width;
        while (size > 4) {
            k_max++;
            size /= 2;
        }
        //Es wird direkt das ganz große quadrat gemalt, dann die kleineren
        for (int k = 0; k <= k_max; k++) {
            int current_size = width / (1 << k);
            int num_squares = 1 << (2 * k);

            for (int i = 0; i < num_squares; i++) {
                int x = width / 2;
                int y = width / 2;
                int temp = i;
                for (int j = 0; j < k; j++) {
                    int dir = temp % 4; //es wird nur ein Raster produziert, welches sich verschiebt
                    temp /= 4;

                    int dx = (dir == 0 || dir == 1) ? 1 : -1;
                    int dy = (dir == 0 || dir == 2) ? 1 : -1;

                    x += (current_size / 2) * dx;
                    y += (current_size / 2) * dy;
                }

                if (current_size > 4) {
                    myDrawObj.setColor(Color.YELLOW);
                    myDrawObj.fillRectangle(x - current_size/2, y - current_size/2, current_size, current_size);
                    myDrawObj.setColor(Color.BLACK);
                    myDrawObj.drawRectangle(x - current_size/2, y - current_size/2, current_size, current_size);
                }

            }
        }
    }

    // Iterative Version => ChatGPT 5 generiert
    public static void drawIterativeSquaresChatGPT5(CodeDraw myDrawObj, int width) {
        int s = width / 2;             // Startgröße !!!!! Es wird zuerst das größte und dann die kleineren gemalt, überschneidung
        int center = width / 2;        // Mittelpunkt des Fensters

        // Wir speichern keine Werte, sondern berechnen die Koordinaten schrittweise
        // Solange die Seitenlänge > 4 ist, zeichnen wir Quadrate auf allen Ebenen
        while (s > 4) {
            int step = s / 2;
            int n = (width / s);  // Anzahl potenzieller Quadrate in dieser Ebene (nicht wirklich gebraucht) !!! Wird nie verwendet

            // Zeichne alle Quadrate dieser "Ebene"
            for (int dx = -step; dx <= step; dx += s) {
                for (int dy = -step; dy <= step; dy += s) {
                    myDrawObj.setColor(Color.YELLOW);
                    myDrawObj.fillSquare(center + dx - s / 2, center + dy - s / 2, s);
                    myDrawObj.setColor(Color.BLACK);
                    myDrawObj.drawSquare(center + dx - s / 2, center + dy - s / 2, s);
                }
            }

            s /= 2;  // Halbierung für nächste Ebene
        }
    }


    // Iterative Version -> handgeschrieben
    /* VERSION 1
    private static void drawIterativeSquares(CodeDraw myDrawObj, int width) {

        int sSize = 4;
        for (int y = 4; y < width; y += sSize * 2) {
            for (int x = 4; x < width; x += sSize * 2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize);
            }
        }
        for (int y = 4*2 - 2; y < width; y += (sSize * 2)*2) {
            for (int x = 4*2 - 2; x < width; x += (sSize * 2)*2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize*2);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize*2);
            }
        }
        for (int y = 4*4 - 6; y < width; y += ((sSize * 2)*2)*2) {
            for (int x = 4*4 - 6; x < width; x += ((sSize * 2)*2)*2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize*4);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize*4);
            }
        }
        for (int y = 4*8 - 14; y < width; y += (((sSize * 2)*2)*2)*2) {
            for (int x = 4*8 - 14; x < width; x += (((sSize * 2)*2)*2)*2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize*8);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize*8);
            }
        }

        for (int y = 4*16 - 30 ; y < width; y += ((((sSize * 2)*2)*2)*2)*2) {
            for (int x = 4*16 -30 ; x < width; x += ((((sSize * 2)*2)*2)*2)*2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize*16);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize*16);
            }
        }
        for (int y = 4*32 -62; y < width; y += (((((sSize * 2)*2)*2)*2)*2)*2) {
            for (int x = 4*32 -62 ; x < width; x += (((((sSize * 2)*2)*2)*2)*2)*2) {
                myDrawObj.setColor(Color.BLACK);
                myDrawObj.drawSquare(x, y, sSize*32);
                myDrawObj.setColor(Color.YELLOW);
                myDrawObj.fillSquare(x, y, sSize*32);
            }
        }
        myDrawObj.setColor(Color.BLACK);
        myDrawObj.drawSquare(width/2 - width/4, width/2 - width/4, width/2);

        myDrawObj.setColor(Color.YELLOW);
        myDrawObj.fillSquare(width/2 - width/4, width/2 - width/4, width/2);



        myDrawObj.show();

    }

     */
    // VERSION 2
    private static void drawIterativeSquares(CodeDraw myDrawObj, int width) {
        int currentSize = 4;
        int offset = 2;

        while (currentSize <= width / 2) {

            int step = currentSize * 2;

            //Zeichnen Musters für eine Quadrat Größe
            for (int y = offset; y < width; y += step) {
                for (int x = offset; x < width; x += step) {
                    myDrawObj.setColor(Color.YELLOW);
                    myDrawObj.fillSquare(x, y, currentSize);
                    myDrawObj.setColor(Color.BLACK);
                    myDrawObj.drawSquare(x, y, currentSize);

                }
            }

            offset += currentSize / 2 ;


            currentSize *= 2;

        }

        myDrawObj.show();
    }

    public static void main(String[] args) {

        int pixelWidth = 512;
        int pixelHeight = 512;

        CodeDraw myDrawObjIterGLM45v = new CodeDraw(pixelWidth, pixelHeight);
        myDrawObjIterGLM45v.setTitle("Output Iterative Method -> GLM 4.5v");
        myDrawObjIterGLM45v.setCanvasPositionX(50);
        myDrawObjIterGLM45v.setCanvasPositionY(50);

        CodeDraw myDrawObjIterChatGPT5 = new CodeDraw(pixelWidth, pixelHeight);
        myDrawObjIterChatGPT5.setTitle("Output Iterative Method -> ChatGPT 5");
        myDrawObjIterChatGPT5.setCanvasPositionX(600);
        myDrawObjIterChatGPT5.setCanvasPositionY(50);

        CodeDraw myDrawObjIter = new CodeDraw(pixelWidth, pixelHeight);
        myDrawObjIter.setTitle("Output Iterative Method");
        myDrawObjIter.setCanvasPositionX(1150);
        myDrawObjIter.setCanvasPositionY(50);

        drawIterativeSquaresGLM45v(myDrawObjIterGLM45v, pixelWidth);
        myDrawObjIterGLM45v.show();

        drawIterativeSquaresChatGPT5(myDrawObjIterChatGPT5, pixelWidth);
        myDrawObjIterChatGPT5.show();

        drawIterativeSquares(myDrawObjIter, pixelWidth);
        //myDrawObjIter.show(50);
    }
}




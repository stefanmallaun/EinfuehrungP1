/*
    Aufgabe 4) Zweidimensionale Arrays und CodeDraw - "Schwärzen ähnlicher Bildbereiche"
*/

import codedraw.CodeDraw;
import codedraw.Palette;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Aufgabe4 {

    // converts BufferedImage object to a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int) (tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }

        }
        return imgArray;
    }

    //draws the image array to the canvas
    private static void drawImage(int[][] imgArray) {
        CodeDraw cd = new CodeDraw(Math.max(imgArray[0].length, 150), Math.max(imgArray.length, 150));

        for (int y = 0; y < imgArray.length; y++) {
            for (int x = 0; x < imgArray[y].length; x++) {
                cd.setPixel(x, y, Palette.fromGrayscale(imgArray[y][x]));
            }
        }
        cd.show();
    }

    private static int[][] blackenSimilarRegions(int[][] imgArray, int rowStart, int rowEnd, int colStart, int colEnd, double threshold) {
        int[][] result = new int[imgArray.length][imgArray[0].length];
        //copy
        for (int i = 0; i < imgArray.length; i++) {
            result[i] = imgArray[i].clone();
        }

        //Template erstellen
        //Berechnung height und width
        int templateHeight = rowEnd - rowStart + 1;
        int templateWidth = colEnd - colStart + 1;
        int[][] template = new int[templateHeight][templateWidth];

        //Bildbereich extrahieren
        for (int i = 0; i < templateHeight; i++) {
            for (int j = 0; j < templateWidth; j++) {
                template[i][j] = imgArray[rowStart + i][colStart + j];
            }
        }
        //Über das Hauptbild iterieren und schauen, ob die Ähnlichkeit größer threshold ist
        //zuerst schauen, dass man im Bild bleibt und das template nicht darüber hinaus iteriert
        for (int i = 0; i < imgArray.length - templateHeight; i++) {
            for (int j = 0; j < imgArray[0].length - templateWidth; j++) {

                double ssd = 0;

                //iterieren durch das Template:
                for (int k = 0; k < templateHeight; k++) {
                    for (int l = 0; l < templateWidth; l++) {
                        //differenz ausrechnen:
                        ssd += Math.pow(imgArray[i + k][j + l] - template[k][l], 2);
                    }
                }

                //Checken ob Ähnlichkeit groß genug ist
                if (ssd < threshold) {
                    //Wieder durchiterien
                    for (int k = 0; k < templateHeight; k++) {
                        for (int l = 0; l < templateWidth; l++) {
                            result[k + i][j + l] = 0;
                        }
                    }
                }


            }
        }
        return result;
    }


    public static void main(String[] args) {

        String fileName = "./src/page4.png";
        BufferedImage img = null;
        // try to open image file
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int[][] imgArray = convertImg2Array(img);

        //blacken the "g"
        //int[][] resultImg = blackenSimilarRegions(imgArray, 148, 158, 321, 328, 1e5);

        //blacken the "while"
        //int[][] resultImg = blackenSimilarRegions(imgArray, 214, 230, 233, 270, 1e6);

        //binarize by comparing to a single black pixel region
        int[][] resultImg = blackenSimilarRegions(imgArray, 150, 150, 95, 95, 220 * 220);

        drawImage(imgArray);
        if (resultImg != null) {
            drawImage(resultImg);
        }
    }
}

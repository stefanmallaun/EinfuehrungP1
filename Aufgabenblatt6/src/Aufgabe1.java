/*
    Aufgabe 1) Zweidimensionale Arrays und Methoden - Game "Black Box Chess"
*/

import codedraw.*;
import codedraw.Image;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Aufgabe1 {

    // the size of everything is calculated in relation to the canvas height
    private static final int CANVAS_HEIGHT = 800;
    private static final int SQUARE_SIZE = CANVAS_HEIGHT * 3 / 31;
    private static final int BOARD_SIZE = SQUARE_SIZE * 8;
    private static final int OFFSET = CANVAS_HEIGHT / 100;
    private static final int CANVAS_WIDTH = CANVAS_HEIGHT - OFFSET;

    private static int score = 100; // We start with 100 points - each hint deducts 3, each wrong guess 7 points
    private static String info = "Good Luck!";
    private static Piece selectedPiece = Piece.NONE; // The currently selected piece

    // this enum improves human readability, as it is error-prone to rely on numbers only
    // the order must be adhered on other logics such as placements and images!
    private enum Piece {
        NONE,
        KNIGHT,
        BISHOP,
        ROOK,
        QUEEN,
        KING
    }

    private static final Image PLACE = CodeDraw.fromFile("src/pics/place.png");
    private static final Image KNIGHT = CodeDraw.fromFile("src/pics/knight.png");
    private static final Image BISHOP = CodeDraw.fromFile("src/pics/bishop.png");
    private static final Image ROOK = CodeDraw.fromFile("src/pics/rook.png");
    private static final Image QUEEN = CodeDraw.fromFile("src/pics/queen.png");
    private static final Image KING = CodeDraw.fromFile("src/pics/king.png");
    private static final Image[] images = {PLACE, KNIGHT, BISHOP, ROOK, QUEEN, KING}; // must adhere to the order of the Piece enum!


    public static void main(String[] args) {
        CodeDraw game = new CodeDraw(CANVAS_WIDTH, CANVAS_HEIGHT);
        game.setTitle("Black Box Chess");

        for (int i = 0; i < images.length; i++) {
            double scaleX = (double) SQUARE_SIZE / images[i].getWidth();
            double scaleY = (double) SQUARE_SIZE / images[i].getHeight();
            double scale = Math.min(scaleX, scaleY);
            images[i] = Image.scale(images[i], scale);
        }

        // the number of rows/columns/squares is currently hardcoded as an 8x8 standard chess board
        int[][] board = new int[8][8]; // entries represent the count of pieces which can move there, -1 if a piece is placed there
        boolean[][] hints = new boolean[8][8]; // saves which hints have already been "bought"

        // placement logic is currently hard coded in order to simulate a hashtable:
        // 0: represents "no piece" (will be needed on other occasions)
        // 1: knight, 2: bishop, 3: rook, 4: queen, 5: king
        // in other words: must adhere to the order of the Piece enum!
        // inner arrays represent rank(=row), file(=column) in this order. rank, column [-1, -1] means "not placed"
        int[][] placements = generatePlacement(board, hints, new Random()); // the placement the player has to find
        int[][] attempt = new int[6][2]; // the current placement the player set
        for (int i = 0; i < attempt.length; i++) {
            attempt[i] = new int[]{-1, -1};
        }

        drawGame(game, board, hints, attempt, placements);
        game.show();

        boolean gameRunning = true;
        while (gameRunning && !game.isClosed() && score > 0) {
            for (var event : game.getEventScanner()) {
                switch (event) {
                    case MouseClickEvent click -> {
                        info = "";
                        int mouseX = click.getX();
                        int mouseY = click.getY();
                        gameRunning = handleMouseClick(mouseX, mouseY, board, hints, attempt, placements);
                        drawGame(game, board, hints, attempt, placements);
                    }
                    case KeyPressEvent keystroke -> {
                        Key key = keystroke.getKey();
                        if (key.equals(Key.ESCAPE)) {
                            info = "You surrendered.";
                            gameRunning = false;
                        } else if (key.equals(Key.ENTER)) {
                            if (isAttemptComplete(attempt)) {
                                gameRunning = !isAttemptCorrect(attempt, placements);
                            }
                            drawGame(game, board, hints, attempt, placements);
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        if (score <= 0) {
            score = 0;
            info = "GAME OVER\nBetter luck next time!";
        }
        if (game.isClosed()) {
            info = "You surrendered.";
        } else {
            drawGame(game, board, hints, attempt, placements);
            game.show(5000);
        }
        System.out.println(info);
        game.close();
    }

    private static void drawGame(CodeDraw game, int[][] board, boolean[][] hints, int[][] attempt, int[][] placements) {

        TextFormat numbersFormat = new TextFormat();
        numbersFormat.setTextOrigin(TextOrigin.CENTER);
        numbersFormat.setFontSize(2 * SQUARE_SIZE / 3);

        TextFormat textFormat = new TextFormat();
        textFormat.setTextOrigin(TextOrigin.CENTER_LEFT);
        textFormat.setFontSize(SQUARE_SIZE / 3);

        game.clear(Palette.WHITE);

        // draw board
        game.setColor(Palette.BLACK);
        game.setTextFormat(numbersFormat);
        for (int i = 0; i <= 8; i++) {
            int currentPos = i * SQUARE_SIZE + OFFSET;
            game.drawLine(currentPos, OFFSET, currentPos, BOARD_SIZE + OFFSET);
            game.drawLine(OFFSET, currentPos, BOARD_SIZE + OFFSET, currentPos);
        }
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if ((rank + file) % 2 == 0) {
                    game.setColor(Palette.WHITE);
                } else {
                    game.setColor(Palette.BLACK);
                }
                game.fillSquare(file * SQUARE_SIZE + OFFSET, rank * SQUARE_SIZE + OFFSET, SQUARE_SIZE);

                if (hints[rank][file] && board[rank][file] != -1) {
                    game.setColor(Palette.CYAN);
                    game.drawText(file * SQUARE_SIZE + OFFSET + SQUARE_SIZE / 2., rank * SQUARE_SIZE + OFFSET + SQUARE_SIZE / 2., String.valueOf(board[rank][file]));
                }
            }
        }

        // sidebar, pieces, placement markers
        game.setColor(Palette.BLACK);
        game.drawRectangle(BOARD_SIZE + 2 * OFFSET, OFFSET + SQUARE_SIZE, 2 * SQUARE_SIZE, 7 * SQUARE_SIZE);
        int[] notPlaced = {-1, -1};

        for (int i = 1; i < attempt.length; i++) {
            if (Arrays.equals(attempt[i], notPlaced)) {
                game.drawImage(BOARD_SIZE + 2 * OFFSET, OFFSET + i * SQUARE_SIZE, images[i]);
            } else {
                game.drawImage(attempt[i][1] * SQUARE_SIZE + OFFSET, attempt[i][0] * SQUARE_SIZE + OFFSET, images[i]);
            }
        }
         /*
        for (int i = 1; i < placements.length; i++) {
            int rank = placements[i][0];
            int file = placements[i][1];

            if (rank != -1 && file != -1) {
                // platziere direkt die Figur auf dem Board
                game.drawImage(file * SQUARE_SIZE + OFFSET, rank * SQUARE_SIZE + OFFSET, images[i]);
            }
        }

          */
        for (int i = 1; i < placements.length; i++) {
            if (placedPiece(placements[i][0], placements[i][1], attempt) == Piece.NONE) {
                game.drawImage(placements[i][1] * SQUARE_SIZE + OFFSET, placements[i][0] * SQUARE_SIZE + OFFSET, images[0]);
            }
        }

        // selection marker
        if (selectedPiece != Piece.NONE) {
            int pieceIndex = selectedPiece.ordinal();
            game.setColor(Palette.RED);
            if (Arrays.equals(attempt[pieceIndex], notPlaced)) {
                game.drawSquare(BOARD_SIZE + 2 * OFFSET, OFFSET + pieceIndex * SQUARE_SIZE, SQUARE_SIZE);
            } else {
                game.drawSquare(attempt[pieceIndex][1] * SQUARE_SIZE + OFFSET, attempt[pieceIndex][0] * SQUARE_SIZE + OFFSET, SQUARE_SIZE);
            }
        }

        // display score
        game.setColor(Palette.BLACK);
        game.setTextFormat(textFormat);
        game.drawText(BOARD_SIZE + 2 * OFFSET, OFFSET + 0.5 * SQUARE_SIZE, "Score: " + score);

        // buttons
        game.setColor(Palette.YELLOW);
        game.fillRectangle(BOARD_SIZE + 2 * OFFSET, BOARD_SIZE + 2 * OFFSET, 2 * SQUARE_SIZE, SQUARE_SIZE);
        game.setColor(Palette.BLACK);
        game.drawText(BOARD_SIZE + 3 * OFFSET, BOARD_SIZE + 0.5 * SQUARE_SIZE + 2 * OFFSET, "Submit");

        game.setColor(Palette.RED);
        game.fillRectangle(BOARD_SIZE + 2 * OFFSET, BOARD_SIZE + SQUARE_SIZE + 3 * OFFSET, 2 * SQUARE_SIZE, SQUARE_SIZE);
        game.setColor(Palette.BLACK);
        game.drawText(BOARD_SIZE + 3 * OFFSET, BOARD_SIZE + 1.5 * SQUARE_SIZE + 3 * OFFSET, "Give up");

        //Added: Reset Button
        game.setColor(Palette.LIGHT_GRAY);
        game.fillRectangle(BOARD_SIZE - OFFSET*20, BOARD_SIZE + 2 * OFFSET,SQUARE_SIZE *2, SQUARE_SIZE);
        game.setColor(Palette.BLACK);
        game.drawText(BOARD_SIZE - OFFSET*15, BOARD_SIZE + 2 * OFFSET + 40, "Reset");

        // info
        game.drawText(4 * OFFSET, BOARD_SIZE + 0.5 * SQUARE_SIZE + 2 * OFFSET, info);



        game.show();
    }

    // generates the placement the player has to guess
    // the placements array simulates a hashtable, with the piece identifier as the key and a pair of coordinates as the value
    // order of the Piece enum must be adhered!
    private static int[][] generatePlacement(int[][] board, boolean[][] hints, Random random) {
        int[][] placements = new int[6][2];
        for (int i = 0; i < placements.length; i++) {
            placements[i] = new int[]{-1, -1};
        }

        int pieceIndex = 1;
        while (pieceIndex < Piece.values().length) {
            int rank = random.nextInt(8);
            int file = random.nextInt(8);
            boolean coordinatesUsed = false;
            for (int i = 1; i < pieceIndex; i++) {
                if (Arrays.equals(placements[i], new int[]{rank, file})) {
                    coordinatesUsed = true;
                    break;
                }
            }
            if (!coordinatesUsed) {
                placements[pieceIndex] = new int[]{rank, file};
                board[rank][file] = -1;
                hints[rank][file] = true;
                Piece piece = Piece.values()[pieceIndex];
                simulateMovements(piece, rank, file, board);
                pieceIndex++;
            }
        }
        return placements;
    }

    // overloading generatePlacement for testing purposes
    /*
    private static int[][] generatePlacement(int[][] board, boolean[][] hints) {
        return generatePlacement(board, hints, new Random());
    }

     */

    private static void simulateMovements(Piece piece, int rank, int file, int[][] board) {
        // file = spalten, rank = reihen
        switch (piece) {

            case KNIGHT:
                moveKnight(rank, file, board);
                break;

            case ROOK:
                moveRook(rank, file, board);
                break;

            case KING:
                moveKing(rank, file, board);
                break;

            case BISHOP:
                moveBishop(rank, file, board);
                break;

            case QUEEN:
                //Methode von Rook und Bishop zusammen
                moveRook(rank, file, board);
                moveBishop(rank, file, board);
                break;
            default:
                break;


        }
    }

    // diese move Methoden wurden ausgelagert, da eine Methode nur eine "Sache" erledigen sollen und
    //      und da die Methode "simulateMovements" schon die auswahl der gewählten Methoden handlet habe ich die
    //      Berechnungen ausgelagert
    private static void moveKnight(int rank, int file, int[][] board) {
        // Knight bewegt sich in L form und hat 8 verschiedene positionen
        for (int i = 0; i < 8; i++) {
            // oben, rechts, unten, links
            int[] x = {-1, 1, 2, 2, -1, 1, -2, -2};
            int[] y = {2, 2, 1, -1, -2, -2, -1, 1};

            reachPosition(rank + x[i], file + y[i], board);
        }
    }

    private static void moveRook(int rank, int file, int[][] board) {
        // Spalte
        for (int i = 0; i < board.length; i++) {
            //unten
            reachPosition(rank + i, file, board);
            //oben
            reachPosition(rank - i, file, board);
        }
        // Reihe
        for (int i = 0; i < board.length; i++) {
            //rechts
            reachPosition(rank, file + i, board);
            //links
            reachPosition(rank, file - i, board);
        }
    }

    private static void moveKing(int rank, int file, int[][] board) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //das Feld vom King überspringen
                if (i != 0 || j != 0) {
                    reachPosition(rank + i, file + j, board);
                }
            }
        }
    }

    private static void moveBishop(int rank, int file, int[][] board) {
        for (int i = 1; i < board.length; i++) {
            //links oben
            reachPosition(rank - i, file - i, board);
            //links unten
            reachPosition(rank - i, file + i, board);
            //rechts oben
            reachPosition(rank + i, file - i, board);
            //rechts unten
            reachPosition(rank + i, file + i, board);
        }
    }


    private static void reachPosition(int rank, int file, int[][] board) {
        // Check: Zeile: oben, unten | Spalte: links, rechts | Feld ist nicht -1 (pos für Figur)
        if (rank >= 0 && rank < board.length && file >= 0 && file < board.length && board[rank][file] != -1) {
            board[rank][file]++;
        }
    }

    // executes the changes in the game data / board which should be triggered by the mouse click
    // returns true if the game should continue running, false otherwise
    private static boolean handleMouseClick(int mouseX, int mouseY, int[][] board, boolean[][] hints, int[][] attempt, int[][] placements) {
        // clicked board
        if (mouseX > OFFSET && mouseX < BOARD_SIZE + OFFSET && mouseY > OFFSET && mouseY < BOARD_SIZE + OFFSET) {
            int file = (int) (((double) (mouseX - OFFSET) / SQUARE_SIZE));
            int rank = (int) (((double) (mouseY - OFFSET) / SQUARE_SIZE));
            if (selectedPiece != Piece.NONE) {
                if (isPlacement(rank, file, board)) {
                    Piece placedPiece = placedPiece(rank, file, attempt);
                    if (placedPiece != Piece.NONE) {
                        removePiece(placedPiece, attempt);
                    }
                    if (placedPiece != selectedPiece) {
                        attempt[selectedPiece.ordinal()] = new int[]{rank, file};
                    }
                }
                selectedPiece = Piece.NONE;
            } else if (isPlacement(rank, file, board)) {
                Piece placedPiece = placedPiece(rank, file, attempt);
                if (placedPiece != Piece.NONE) {
                    info = "You chose a placed piece.\nClick it again to remove it.";
                    selectedPiece = placedPiece;
                }
            } else if (hints[rank][file]) {
                info = "Hint already bought.";
            } else {
                info = "Bought a hint for 3 points.";
                hints[rank][file] = true;
                score -= 3;
            }
        }
        //Reset scan
        else if (mouseX > BOARD_SIZE - OFFSET * 20 && mouseX < BOARD_SIZE - OFFSET * 20 + 2 * SQUARE_SIZE &&
                mouseY > BOARD_SIZE + 2 * OFFSET && mouseY < BOARD_SIZE + 2 * OFFSET + SQUARE_SIZE) {

            resetAttempt(attempt);
        }


        // clicked sidebar (= unplaced pieces)
        else if (mouseX > BOARD_SIZE + 2 * OFFSET && mouseX < BOARD_SIZE + SQUARE_SIZE + 2 * OFFSET &&
                mouseY > SQUARE_SIZE + OFFSET && mouseY < attempt.length * SQUARE_SIZE + OFFSET) {
            int pieceIndex = (int) ((double) (mouseY - OFFSET) / SQUARE_SIZE);
            if (pieceIndex >= 1 && pieceIndex <= 5) {
                if (Arrays.equals(attempt[pieceIndex], new int[]{-1, -1})) {
                    selectedPiece = Piece.values()[pieceIndex];
                }
            }
        }



        // clicked submit
        else if (mouseX > BOARD_SIZE + 2 * OFFSET && mouseX < BOARD_SIZE + 2 * OFFSET + 2 * SQUARE_SIZE &&
                mouseY > BOARD_SIZE + 2 * OFFSET && mouseY < BOARD_SIZE + SQUARE_SIZE + 2 * OFFSET) {
            selectedPiece = Piece.NONE;
            if (isAttemptComplete(attempt)) {
                boolean winning = isAttemptCorrect(attempt, placements);
                if (!winning) {
                    score -= 7;
                    info = "Nice try, but wrong!\n7 Points deducted.";
                } else {
                    info = "Congratulations!\nYour Score: " + score;
                }
                return !winning;
            } else {
                info = "Please place all pieces before submitting.";
            }
        }

        // clicked give up
        else if (mouseX > BOARD_SIZE + 2 * OFFSET && mouseX < BOARD_SIZE + 2 * OFFSET + 2 * SQUARE_SIZE &&
                mouseY > BOARD_SIZE + SQUARE_SIZE + 3 * OFFSET && mouseY < BOARD_SIZE + 2 * SQUARE_SIZE + 3 * OFFSET) {
            selectedPiece = Piece.NONE;
            info = "You surrendered.";
            return false;
        } else {
            selectedPiece = Piece.NONE;
        }

        return true;
    }

    private static boolean isPlacement(int rank, int file, int[][] board) {
        if(board[rank][file] == -1){
            return true;
        }else{
            return false;
        }
    }

    // returns the piece currently placed at the specified coordinates
    private static Piece placedPiece(int rank, int file, int[][] attempt) {
        int[] check = {rank, file};
        for (int i = 1; i < attempt.length; i++) {
            if (Arrays.equals(attempt[i], check)) {
                return Piece.values()[i];
            }
        }
        return Piece.NONE;
    }

    // removes the piece currently placed at the specified coordinates
    private static void removePiece(Piece piece, int[][] attempt) {
        attempt[piece.ordinal()] = new int[]{-1, -1};
    }

    private static boolean isAttemptComplete(int[][] attempt) {
        // jedes der Elemente des Attempts Prüfen, ob es [-1][-1] ist, wenn nicht -> alle Figuren platziert
        for (int i = 1; i < attempt.length; i++) {
            if(attempt[i][0] == -1 && attempt[i][1] == -1){
                return false;
            }
        }
        return true;
    }

    private static boolean isAttemptCorrect(int[][] attempt, int[][] placements) {
        for (int i = 0; i < attempt.length; i++) {
            if (!Arrays.equals(attempt[i], placements[i])) {
                return false;
            }
        }
        return true;
    }

    //Reset Button Logic:
    private static void resetAttempt(int[][] attempt) {
        for (int i = 1; i < attempt.length; i++) {
            attempt[i][0] = -1;
            attempt[i][1] = -1;
        }
        selectedPiece = Piece.NONE;
        info = "All pieces reset.";
    }
}

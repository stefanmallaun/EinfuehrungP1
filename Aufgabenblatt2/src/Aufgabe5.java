import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/*
    Aufgabe 5) Kreativaufgabe
*/
public class Aufgabe5 {

    //Baccarat
    //Anleitung: https://www.spielregeln-spielanleitungen.de/spielenews/wie-spielt-man-baccarat/
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        char playAgain = 'j';
        int geld = 100;

        System.out.println("Wollen Sie spielen? [j/n]: ");
        playAgain = reader.next().charAt(0);

        while(playAgain == 'j'){
            System.out.println("Ihr Guthaben: " + geld);
            System.out.println("Ihr Wetteinsatz: ");
            int wetteinsatz = reader.nextInt();
            System.out.println("Für wen sind Sie? [Dealer 1:2, Player 1:2, Draw 1:9]: ");
            String choice = reader.next().toLowerCase();

            ArrayList<String> deck = createDeck();

            // beide Hände ziehen:
            ArrayList<String> dealerHand = drawHand(deck);
            ArrayList<String> playerHand = drawHand(deck);

            // Punktezahl beider hände
            int dealerScore = calculateScore(dealerHand);
            int playerScore = calculateScore(playerHand);

            char gameWinner = winner(dealerScore, playerScore);
            System.out.println("Dealer zieht: " + dealerScore + " Player zieht: " + playerScore);
            switch (gameWinner) {
                case 'd':
                    if (choice.equals("dealer")){
                        geld += wetteinsatz*2;
                        gewinnAusgabe(true, geld);
                    }else{
                        geld -= wetteinsatz;
                        gewinnAusgabe(false, geld);
                    }break;
                case 'p':
                    if (choice.equals("player")){
                        geld += wetteinsatz*2;
                        gewinnAusgabe(true, geld);
                    }else{
                        geld -= wetteinsatz;
                        gewinnAusgabe(false, geld);
                    }break;
                case 'x':
                    if (choice.equals("draw")){
                        geld += wetteinsatz*9;
                        gewinnAusgabe(true, geld);
                        System.out.println("Gewonnen! ");
                    }else{
                        geld -= wetteinsatz;
                        gewinnAusgabe(false, geld);
                    }break;
                default:
                    System.out.println("Ungültige Eingabe!");
            }
            System.out.println("Wollen Sie nochmal spielen? [j/n]: ");
            playAgain = reader.next().charAt(0);

        }


    }
    private static ArrayList<String> createDeck(){
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        ArrayList<String> deck = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            for (String value : values){
                deck.add(value);
            }
        }
        return deck;
    }
    private static String drawCard(ArrayList<String> deck){
        Random rand = new Random();
        int index = rand.nextInt(deck.size());
        return deck.remove(index);
    }
    private static int calculateScore(ArrayList<String> hand){
        int score = 0;
        for(String card : hand){
            String value = card;
            if(value.equals("J") || value.equals("Q") || value.equals("K") || value.equals("10")){
                score += 0;
            }else if(value.equals("A")){
                score +=1;
            }else{
                score += Integer.parseInt(value);
            }
        }
        return score%10;

    }
    private static ArrayList<String> drawHand(ArrayList<String> deck){
        ArrayList<String> hand = new ArrayList<>();
        hand.add(drawCard(deck));
        hand.add(drawCard(deck));
        return hand;
    }
    private static char winner(int dealerCount, int playerCount){
        if (dealerCount > playerCount){
            return 'd';
        }else if(playerCount > dealerCount){
            return 'p';
        }else{
            return 'x';
        }
    }
    private static void gewinnAusgabe(boolean status, int geld){
        if(status){
            System.out.println("Sie haben gewonnen! Ihr Guthaben liegt bei: " + geld);
        }else{
            System.out.println("Sie haben verloren! Ihr Guthaben liegt bei: " + geld);
        }
    }

}

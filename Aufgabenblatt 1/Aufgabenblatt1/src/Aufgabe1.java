/*
    Aufgabe 1) for-Schleifen
*/
public class Aufgabe1 {

    public static void main(String[] args) {

        //a) sum all Elements in [17,340[ divisible by 17
        int sum1 = 0;
        for(int i = 17; i < 340; i++){
            if(i%17 == 0){
                sum1 += i;
            }
        }
        System.out.println(sum1);



        //b) All Numbers [63, 119] %7 && !%8 and add #
        String folge1 = "#";
        for(int i = 63; i <= 119; i++){
            if(i%7 == 0 && i%8 != 0){
                folge1 +=  i;
                folge1 += "#";
            }
        }
        System.out.println(folge1);

        //c) alle Zeichen der ASCII-Werte in ]47, 58[ in desc und ","
        String asciiFolge = "";
        for(int i = 57; i > 47; i--){
            asciiFolge += (char) i;
            if(i > 48) {
                asciiFolge += ",";
            }
        }
        System.out.println(asciiFolge);

        //d)
        String satz = "Drei durstige Dromedare durchqueren die Wüste der dutzenden dunklen Dünen!";
        int count = 0;
        for (int i = 0; i < satz.length(); i++){
            if (satz.charAt(i) == 'd' || satz.charAt(i) == 'D'){
                count++;
            }
        }
        System.out.println(count);

    }
}

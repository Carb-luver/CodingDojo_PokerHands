package ManifestSolutions.Megan;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
        Player black = null;
        Player white = null;
        String nextToken;
        HashMap<Integer,String> cardMapBlack = new HashMap();
        HashMap<Integer,String> cardMapWhite = new HashMap();
        int count =0;

        //scans user input
        while (scanner.hasNext()){
            nextToken = scanner.next().toLowerCase(Locale.ROOT).trim();

            if (nextToken.contains("black")){
                count++;
                for (int i = 1; i<=5; i++){
                    cardMapBlack.put(i,scanner.next().trim());
                }
                black = new Player("Black", cardMapBlack);
                black.determineWinner();
//                System.out.println(black.highCard());

            }
            if (nextToken.contains("white")){
                count++;
                for (int i = 1; i<=5; i++){
                    cardMapWhite.put(i,scanner.next().trim());
                }
                white = new Player("White", cardMapWhite);
                white.determineWinner();
//                System.out.println(white.highCard());
            }
        }
    }
}

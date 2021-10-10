package ManifestSolutions.Megan;

import java.util.*;

public class Player {
    String playerName;
    HashMap<Integer, String> cardMap = new HashMap();
    private static ArrayList playerList = new ArrayList();


    public Player(String player, HashMap cardMap) {
        this.playerName = player;
        this.cardMap = cardMap;
        playerList.add(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public HashMap<Integer, String> getCardMap() {
        return cardMap;
    }

    public ArrayList getPlayerList() {
        return playerList;
    }

    private int[][] countCardNumbers() {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int countT = 0;
        int countJ = 0;
        int countQ = 0;
        int countK = 0;
        int countA = 0;
        int[][] countNumber = {{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0},{10,0},{11,0},{12,0},{13,0},{14,0}};

        for (int i = 1; i <= 5; i++) {
            if (cardMap.get(i).contains("1")) {
                count1++;
                countNumber[0][1]=count1;
            } else if (cardMap.get(i).contains("2")) {
                count2++;
                countNumber[1][1]=count2;
            } else if (cardMap.get(i).contains("3")) {
                count3++;
                countNumber[2][1]=count3;
            } else if (cardMap.get(i).contains("4")) {
                count4++;
                countNumber[3][1]=count4;
            } else if (cardMap.get(i).contains("5")) {
                count5++;
                countNumber[4][1]=count5;
            } else if (cardMap.get(i).contains("6")) {
                count6++;
                countNumber[5][1]=count6;
            } else if (cardMap.get(i).contains("7")) {
                count7++;
                countNumber[6][1]=count7;
            } else if (cardMap.get(i).contains("8")) {
                count8++;
                countNumber[7][1]=count8;
            } else if (cardMap.get(i).contains("9")) {
                count9++;
                countNumber[8][1]=count9;
            } else if (cardMap.get(i).contains("T")) {
                countT++;
                countNumber[9][1]=countT;
            } else if (cardMap.get(i).contains("J")) {
                countJ++;
                countNumber[10][1]=countJ;
            } else if (cardMap.get(i).contains("Q")) {
                countQ++;
                countNumber[11][1]=countQ;
            } else if (cardMap.get(i).contains("K")) {
                countK++;
                countNumber[12][1]=countK;
            } else if (cardMap.get(i).contains("A")) {
                countA++;
                countNumber[13][1]=countA;
            }
        }
        return countNumber;
    }

    private int[][] countCardSuits(){
        int countC = 0;
        int countD = 0;
        int countS = 0;
        int countH = 0;
        int[][] countSuit = {{1,0},{2,0},{3,0},{4,0}};

        for (int i = 1; i <= 5; i++) {
            if (cardMap.get(i).contains("C")) {
                countC++;
                countSuit[0][1] = countC;
            } else if (cardMap.get(i).contains("D")) {
                countD++;
                countSuit[1][1] = countD;
            } else if (cardMap.get(i).contains("S")) {
                countS++;
                countSuit[2][1] = countS;
            } else if (cardMap.get(i).contains("H")) {
                countH++;
                countSuit[3][1] = countH;
            }
        }
        return countSuit;
    }

    public String cardRank() {

        int[][] countNumber = countCardNumbers();
        int[][] countSuit = countCardSuits();

        for (int x = 0; x < (countNumber.length - cardMap.size()); x++) {
            if ((countNumber[x][1] > 1 && countNumber[x+1][1] > 1 && countNumber[x+2][1] > 1 && countNumber[x+3][1] > 1 && countNumber[x+4][1] > 1)) {
                for (int i = 0; i < countSuit.length; i++) {
                    if (countSuit[i][1] == 5) {
                        return "9 Straight Flush ";
                    }
                }
            }
        }

        for (int i = 0; i < countNumber.length; i++) {
            if (countNumber[i][1] > 3) {
                return "8 Four of a Kind: " + (i+1);
            }
        }

        for (int i = 0; i < countNumber.length; i++) {
            if (countNumber[i][1] == 3) {
                for (int x = 0; x < countNumber.length; x++) {
                    if (countNumber[x][1] == 2) {
                        return "7 Full House: " + (i+1) + " over " + (x+1);
                    }
                }
            }
        }

        for (int x = 0; x < countSuit.length; x++) {
            if (countSuit[x][1] == 5) {
                return "6 Flush: ";
            }
        }

        for (int x = 0; x < (countNumber.length - cardMap.size()); x++) {
            if ((countNumber[x][1] > 1 && countNumber[x+1][1] > 1 && countNumber[x+2][1] > 1 && countNumber[x+3][1] > 1 && countNumber[x+4][1] > 1)) {
                return "5 Straight: " ;
            }
        }

        for (int i = 0; i < countNumber.length; i++) {
            if (countNumber[i][1] == 3) {
                return "4 Three of a Kind: "  + (i+1);
            }
        }

        for (int i = 0; i < countNumber.length; i++) {
            if (countNumber[i][1] == 2) {
                for (int x = (i + 1); x <= countNumber.length; x++) {
                    if (countNumber[x][1] == 2) {
                        return "3 Two Pairs: " + (i+1) + " and " + (x+1);
                    }
                }
            }
        }

        for (int i = 0; i < countNumber.length; i++) {
            if (countNumber[i][1] == 2) {
                return "2 Pair: " + i;
            }
        }

        int max = 0;
        for (int i = 0; i < countNumber.length; i++) {
            if ((countNumber[i][1] > max)) {
                max = i;
            }
            return "1 High Card " + max;
        }

        return "";
    }

    public void determineWinner(){

        if(playerList.size()%2 == 0) {
            Player player1 = (Player) playerList.get(playerList.size() - 2);
            Player player2 = (Player) playerList.get(playerList.size() - 1);

            if ((player1.cardRank().charAt(0)) == (player2.cardRank().charAt(0))) {
                tieBreak();
            } else if (Integer.parseInt(player1.cardRank().substring(0, 1)) > Integer.parseInt(player2.cardRank().substring(0, 1))) {
                System.out.println(player1.getPlayerName() + " wins with a " + player1.cardRank().substring(2));
            } else if (Integer.parseInt(player2.cardRank().substring(0, 1)) > Integer.parseInt(player1.cardRank().substring(0, 1))) {
                System.out.println(player2.getPlayerName() + " wins with a " + player2.cardRank().substring(2));
            }
        }
    }

    private void tieBreak(){

        if(playerList.size()%2 == 0) {
            Player player1 = (Player) playerList.get(playerList.size() - 2);
            Player player2 = (Player) playerList.get(playerList.size() - 1);
            StringTokenizer highCard = new StringTokenizer(highCard());


            if (player1.cardRank().charAt(0) == '9') {
                if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with a Straight Flush and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '8') {
                if (Integer.parseInt(player1.cardRank().substring(18).trim()) > Integer.parseInt(player2.cardRank().substring(18).trim()))
                    System.out.println(player1.getPlayerName() + " wins with Four of a Kind with value: " + numberToCard(Integer.parseInt(player1.cardRank().substring(18).trim())));
                else if (Integer.parseInt(player1.cardRank().substring(18).trim()) < Integer.parseInt(player2.cardRank().substring(18).trim()))
                    System.out.println(player2.getPlayerName() + " wins with Four of a Kind with value: " + numberToCard(Integer.parseInt(player2.cardRank().substring(18).trim())));
                else if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with Four of a Kind and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '7') {
                if (Integer.parseInt(player1.cardRank().substring(15, 17).trim()) > Integer.parseInt(player2.cardRank().substring(15, 17).trim()))
                    System.out.println(player1.getPlayerName() + " wins with Full House with value: " + numberToCard(Integer.parseInt(player1.cardRank().substring(15, 17).trim())));
                else if (Integer.parseInt(player1.cardRank().substring(15, 17).trim()) < Integer.parseInt(player2.cardRank().substring(15, 17).trim()))
                    System.out.println(player2.getPlayerName() + " wins with Full House with value: " + numberToCard(Integer.parseInt(player2.cardRank().substring(15, 17).trim())));
                else if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with a Full House and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '6') {
                if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with a Flush and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '5') {
                if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with a Straight and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '4') {
                if (Integer.parseInt(player1.cardRank().substring(19).trim()) > Integer.parseInt(player2.cardRank().substring(19).trim()))
                    System.out.println(player1.getPlayerName() + " wins with Three of a Kind with value of: " + numberToCard(Integer.parseInt(player1.cardRank().substring(19).trim())));
                else if (Integer.parseInt(player1.cardRank().substring(19).trim()) < Integer.parseInt(player2.cardRank().substring(19).trim()))
                    System.out.println(player2.getPlayerName() + " wins with Three of a Kind with value of: " + numberToCard(Integer.parseInt(player2.cardRank().substring(19).trim())));
                else if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with Three of a Kind and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '3') {
                if (Math.max(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim())) > Math.max(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim())))
                    System.out.println(player1.getPlayerName() + " wins with Two Pairs with value of: " + numberToCard(Math.max(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim()))));

                else if (Math.max(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim())) < Math.max(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim())))
                    System.out.println(player2.getPlayerName() + " wins with Two Pairs with value of: " + numberToCard(Math.max(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim()))));

                else if (Math.min(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim())) > Math.min(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim()))) {
                    System.out.println(player1.getPlayerName() + " wins with Two Pairs with value of: " + numberToCard(Math.min(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim()))));

                } else if (Math.min(Integer.parseInt(player1.cardRank().substring(13, 15).trim()), Integer.parseInt(player1.cardRank().substring(19).trim())) < Math.min(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim())))
                    System.out.println(player2.getPlayerName() + " wins with Two Pairs with value of: " + numberToCard(Math.min(Integer.parseInt(player2.cardRank().substring(13, 15).trim()), Integer.parseInt(player2.cardRank().substring(19).trim()))));
                else if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with Two Pairs and high card: " + highCard.nextToken());


            }

            else if (player1.cardRank().charAt(0) == '2') {
                if (Integer.parseInt(player1.cardRank().substring(8).trim()) > Integer.parseInt(player2.cardRank().substring(8).trim()))
                    System.out.println(player1.getPlayerName() + " wins with a Pair with value: " + numberToCard(Integer.parseInt(player1.cardRank().substring(8).trim())));
                else if (Integer.parseInt(player1.cardRank().substring(8)) < Integer.parseInt(player2.cardRank().substring(8)))
                    System.out.println(player2.getPlayerName() + " wins with a Pair with value: " + numberToCard(Integer.parseInt(player2.cardRank().substring(8).trim())));
                else if (highCard() != "Tie" && highCard() != "")
                    System.out.println(highCard.nextToken() + " wins with a Pair and high card: " + highCard.nextToken());
            }

            else if (player1.cardRank().charAt(0) == '1') {
                if (highCard() != "Tie" && highCard() != "") {
                    System.out.println(highCard.nextToken() + " wins with a high card: " + highCard.nextToken());
                }else {
                    System.out.println(highCard());
                }
            }

        }

    }

    private String highCard(){
        if(playerList.size()%2 == 0) {
            Player player1 = (Player) playerList.get(playerList.size() - 2);
            Player player2 = (Player) playerList.get(playerList.size() - 1);
            int[][] countNumber1 = player1.countCardNumbers();
            int[][] countNumber2 = player2.countCardNumbers();

            for (int i = (countNumber1.length - 1); i>=0; i--) {
                if (countNumber1[i][1] > 0 && countNumber2[i][1] == 0) {
                    return player1.getPlayerName() + " " + numberToCard(countNumber1[i][0]);
                } else if (countNumber2[i][1] > 0 && countNumber1[i][1] == 0) {
                    return player2.getPlayerName() + " " + numberToCard(countNumber2[i][0]);
                }
            }
            if(Arrays.deepEquals(countNumber1, countNumber2)){
                return "Tie";
            }
        }
        return "";
    }

    private String numberToCard(int card){

        if(card == 11)
            return "Jack";
        else if(card == 12)
            return "Queen";
        else if(card == 13)
            return  "King";
        else if(card == 14)
            return  "Ace";
        else
            return Integer.toString(card);
    }

}

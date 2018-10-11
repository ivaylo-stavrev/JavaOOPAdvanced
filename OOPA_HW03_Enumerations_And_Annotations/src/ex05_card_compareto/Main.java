package ex05_card_compareto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String firstCardRank = reader.readLine();
        String firstCardSuit = reader.readLine();
        String secondCardRank = reader.readLine();
        String secondCardSuit = reader.readLine();

        Card card1 = new Card(firstCardRank, firstCardSuit);
        Card card2 = new Card(secondCardRank, secondCardSuit);

        switch (card1.compareTo(card2)) {
            case 1:
                System.out.println(card1.toString());
                break;
            case -1:
                System.out.println(card2.toString());
                break;
            case 0:
                System.out.println(card1.toString());
                break;
        }
    }
}
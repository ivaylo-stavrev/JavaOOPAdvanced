package ex07_deck_of_cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Card card;

        while (!"Card Deck".equals(reader.readLine())) {

        }

        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                card = new Card(rank.name(), suit.name());
                System.out.println(card.toString());
            }
        }


    }
}
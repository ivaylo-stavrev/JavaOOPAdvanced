package ex03_cards_with_power;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CardRank[] ranks = CardRank.values();
        CardSuit[] suits = CardSuit.values();

        String card = reader.readLine();
        String suit = reader.readLine();
        int powerSum = CardSuit.valueOf(suit).getSuitPower() + CardRank.valueOf(card).getCardPower();

        System.out.println(String.format("Card name: %s of %s; Card power: %d", card, suit, powerSum));

    }
}

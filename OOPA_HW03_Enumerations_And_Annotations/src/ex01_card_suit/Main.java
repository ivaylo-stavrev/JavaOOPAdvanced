package ex01_card_suit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CardSuit[] suit = CardSuit.values();

        System.out.println(String.format("%s:", reader.readLine()));
        for (CardSuit cardSuit : suit) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", cardSuit.ordinal(), cardSuit.name()));
        }

    }
}

package ex02_cards_rank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CardRank[] suit = CardRank.values();

        System.out.println(String.format("%s:", reader.readLine()));
        for (CardRank cardRank : suit) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", cardRank.ordinal(), cardRank.name()));
        }

    }
}

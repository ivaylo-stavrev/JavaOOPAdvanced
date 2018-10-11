package ex08_card_game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Map<String, List<Card>> player1 = new HashMap<>();
        Map<String, List<Card>> player = new HashMap<>();
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();
        Deck deck = new Deck();

        String nameP1 = reader.readLine();
        String nameP2 = reader.readLine();

        while (true) {
            if (player1Cards.size() >= 5) {
                break;
            }
            String drawCard = reader.readLine();
            playerDrawingCards(player1Cards, deck, drawCard);
            player.put(nameP1, player1Cards);
        }

        while (true) {
            if (player2Cards.size() >= 5) {
                break;
            }
            String drawCard = reader.readLine();
            playerDrawingCards(player2Cards, deck, drawCard);
            player.put(nameP2, player2Cards);
        }

        //Card p1maxPowerCard = null;
        String winner = "";
        Card overallMaxPowerCard = null;
        for (Card p1card : player.get(nameP1)) {
            if (overallMaxPowerCard == null) {
                overallMaxPowerCard = p1card;
            } else if (p1card.compareTo(overallMaxPowerCard) > 0) {
                overallMaxPowerCard = p1card;
            }
            winner = nameP1;
        }
        for (Card p2card : player.get(nameP2)) {
            if (p2card.compareTo(overallMaxPowerCard) > 0) {
                overallMaxPowerCard = p2card;
                winner = nameP2;
            }
        }
        System.out.println(String.format("%s wins with %s.", winner, overallMaxPowerCard.toString()));
    }

    private static void playerDrawingCards(List<Card> playerCards, Deck deck, String drawCard) {
        String[] lineSplitted = drawCard.split(" of ");
        String drawCardRank = lineSplitted[0];
        String drawCardSuit = lineSplitted[1];

        if (deck.removeCardFromDeck(drawCard) == 0) {
            Card card = new Card(drawCardRank, drawCardSuit);
            playerCards.add(card);
        } else if (deck.removeCardFromDeck(drawCard) > 0){
            System.out.println("Card is not in the deck.");
        } else {
            System.out.println("No such card exists.");
        }
    }
}
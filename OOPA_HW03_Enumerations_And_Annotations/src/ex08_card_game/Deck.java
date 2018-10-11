package ex08_card_game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deck {
    private List<Card> cards;
    private Map<String, Card> cardsMap;
    private Map<String, Card> cardsDrawnMap;

    public Deck() {
        this.cards = new ArrayList<>();
        this.cardsMap = new HashMap<>();
        this.cardsDrawnMap = new HashMap<>();
        this.loadAllCardsInDeck();
    }

    private void loadAllCardsInDeck() {
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                Card card = new Card(cardRank.name(), cardSuit.name());
                this.cardsMap.put(card.toString(), card);
                this.cards.add(card);
            }
        }
    }

    public int removeCardFromDeck(String cardName) {
        if (this.cardsMap.containsKey(cardName)) {
            //this.cardsMap.remove(cardName);
            this.cardsDrawnMap.put(cardName, this.cardsMap.remove(cardName));
            return 0;
        } else {
            if (this.cardsDrawnMap.containsKey(cardName)) {
                return 1; // Card is not in the deck.
            } else {
                return -1; // No such card exists.
            }
        }
    }
}

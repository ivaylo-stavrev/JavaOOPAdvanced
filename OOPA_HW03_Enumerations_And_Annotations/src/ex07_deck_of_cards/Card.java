package ex07_deck_of_cards;

public class Card implements Comparable<Card>{
    CardRank cardRank;
    CardSuit cardSuit;
    int cardPower;

    public Card(String rank, String suit) {
        this.cardRank = CardRank.valueOf(rank);
        this.cardSuit = CardSuit.valueOf(suit);
        this.calculatePower();
    }

    public int getCardPower() {
        return this.cardPower;
    }

    private void calculatePower() {
        this.cardPower = cardRank.getCardPower() + cardSuit.getSuitPower();
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.cardRank, this.cardSuit);
    }

    @Override
    public int compareTo(Card other) {
        return this.cardPower == other.getCardPower() ? 0 : (this.cardPower > other.getCardPower()) ? 1 : -1;
    }
}

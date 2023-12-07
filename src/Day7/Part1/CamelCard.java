package Day7.Part1;

import java.util.HashMap;
import java.util.Map;

public class CamelCard {
    private char[] cards;
    private int bet;
    private int handValue;

    public CamelCard(char[] cards, int bet) {
        this.cards = cards;
        this.bet = bet;
        handValue = calculateHandValue(cards);
    }

    private int calculateHandValue(char[] cards) {
        Map<Character, Integer> cardCount = new HashMap<>();
        for (Character s : cards) {
            cardCount.put(s, cardCount.getOrDefault(s, 0) + 1);
        }
        if (cardCount.size() == 1 && cardCount.containsValue(5)) return 7; // Five of a kind
        if (cardCount.size() == 2 && cardCount.containsValue(4)) return 6; // Four of a kind
        if (cardCount.size() == 2 && (cardCount.containsValue(3) && cardCount.containsValue(2))) return 5; // Full house
        if (cardCount.size() == 3 && cardCount.containsValue(3)) return 4; // Three of a kind
        if (cardCount.size() == 3 && cardCount.containsValue(2)) return 3; // Two pair
        if (cardCount.size() == 4 && cardCount.containsValue(2)) return 2; // One pair
        return 1; // Default for high card
    }

    public char[] getCards() {
        return cards;
    }

    public int getBet() {
        return bet;
    }

    public int getHandValue() {
        return handValue;
    }
}

package Day7.Part2;

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
        // All 5 same
        if (cardCount.size() == 1) return 7; // Five of a kind

        // 4 of one plus joker, or 4 joker + 1 = five of a kind
        if (cardCount.size() == 2 && cardCount.containsKey('J')) return 7; // Five of a kind

        // Two kinds with no joker and has value 4 = four of a kind
        if (cardCount.size() == 2 && cardCount.containsValue(4)) return 6; // Four of a kind

        // Three kinds that has three of one kind and joker is four of a kind
        if (cardCount.size() == 3 && cardCount.containsValue(3) && cardCount.containsKey('J')) return 6; // Four of a kind

        // Three kinds that has two of one kind and jokers is full house
        if (cardCount.size() == 3 && cardCount.containsValue(2) && cardCount.containsKey('J') && cardCount.get('J') == 2) return 6; // Four of a kind

        // Three kinds that has two of one kind and single joker is full house
        if (cardCount.size() == 3 && cardCount.containsValue(2) && cardCount.containsKey('J') && cardCount.get('J') == 1) return 5; // Full house

        // Two kind contains 3 is full house
        if (cardCount.size() == 2 && (cardCount.containsValue(3))) return 5; // Full house

        // Three kinds has three of one is three of a kind
        if (cardCount.size() == 3 && cardCount.containsValue(3)) return 4; // Three of a kind

        // Three of a kind since four of a kind and full house is already checked
        if (cardCount.size() == 3 && cardCount.containsValue(2) && cardCount.containsKey('J')) return 4; // Three of a kind

        // Four kinds and has joker is three of a kind
        if (cardCount.size() == 4 && cardCount.containsKey('J')) return 4; // Three of a kind

        // Three kinds only left is two pair
        if (cardCount.size() == 3) return 3; // Two pair

        // Only left is one pair
        if (cardCount.size() == 4) return 2; // One pair
        if (cardCount.containsKey('J')) return 2; // One pair
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

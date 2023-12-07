package Day7.Part1;

import java.util.HashMap;
import java.util.Map;

public class CamelCardComparator implements java.util.Comparator<CamelCard> {
    private static final Map<Character, Integer> cardValues = initializeCardValues();

    private static Map<Character, Integer> initializeCardValues() {
        Map<Character, Integer> values = new HashMap<>();
        values.put('T', 10);
        values.put('J', 11);
        values.put('Q', 12);
        values.put('K', 13);
        values.put('A', 14);
        return values;
    }

    @Override
    public int compare(CamelCard hand1, CamelCard hand2) {
        int result = Integer.compare(hand1.getHandValue(), hand2.getHandValue());
        return result != 0 ? result : compareCards(hand1, hand2);
    }
    private int compareCards(CamelCard hand1, CamelCard hand2) {
        for (int i = 0; i < hand1.getCards().length; i++) {
            char card1 = hand1.getCards()[i];
            char card2 = hand2.getCards()[i];

            int value1 = cardValues.getOrDefault(Character.toUpperCase(card1), Character.getNumericValue(card1));
            int value2 = cardValues.getOrDefault(Character.toUpperCase(card2), Character.getNumericValue(card2));

            int comparison = Integer.compare(value1, value2);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }
}
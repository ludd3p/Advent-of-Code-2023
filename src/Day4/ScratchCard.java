package Day4;

/**
 * Custom data class for holding information about a scratch card.
 * Key is the scratch card string.
 * Copies is the amount of copies for this specific scratch card.
 */
public class ScratchCard {
    private final String key;
    private int copies;

    public ScratchCard(String key) {
        this.key = key;
        this.copies = 1;
    }

    public String getKey() {
        return key;
    }

    public int getCopies() {
        return copies;
    }
    public void addCopy() {
        copies++;
    }
}

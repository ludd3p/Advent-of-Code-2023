package Day5;
import java.util.ArrayList;
import java.util.List;

public class RangeMap {
    List<Long> start;
    List<Long> destination;
    List<Long> range;

    public RangeMap(List<Range> rangeList) {
        this.start = new ArrayList<>();
        this.destination = new ArrayList<>();
        this.range = new ArrayList<>();
        for (Range r : rangeList) {
            start.add(r.start);
            destination.add(r.destination);
            range.add(r.range);
        }
    }

    /**
     * Checks if the passed long is within range for a destination
     * @param startIn Start number to check
     * @return Number representing the destination
     */
    public long convertStartToDest(long startIn) {
        // Iterate the numbers
        for (int i = 0; i < start.size(); i++) {
            // Check if number is within possible range
            if (start.get(i) <= startIn && start.get(i) + range.get(i) > startIn) {
                // Return the destination number
                return destination.get(i) + (startIn - start.get(i));
            }
        }
        return startIn;
    }

    /**
     * Checks if the passed long is within range for a destination
     * @param startIn Start number to check
     * @return Array with numbers representing the destination, and remaining items in the range
     */
    public long[] convertStartToDestWithRange(long startIn) {
        // Set a base value to compare with
        long nextStart = Long.MAX_VALUE;
        // Iterate through the ranges defined by start and range lists
        for (int i = 0; i < start.size(); i++) {
            // Check if the current range starts after the current position
            if (start.get(i) > startIn) {
                // Calculate and store the distance to the next range start
                nextStart = Math.min(nextStart, start.get(i) - startIn - 1);
            }
            // Check if the current position is within the current range
            if (start.get(i) <= startIn && start.get(i) + range.get(i) > startIn) {
                // Calculate and return the new destination and remaining range
                return new long[]{destination.get(i) + (startIn - start.get(i)), range.get(i) - (startIn - start.get(i)) - 1};
            }
        }
        // If no match is found, return the original startIn and 0 as remaining range
        return new long[]{startIn, nextStart == Long.MAX_VALUE ? 0 : nextStart};
    }

}


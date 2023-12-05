package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void parseFile(String filePath) {
        // For time checking
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Trim useless text and separate seed numbers into array
            String[] seedStrings = br.readLine().split(":")[1].trim().split(" ");
            // Create lists to hold data
            List<Long> seedNumbers = new ArrayList<>();
            List<RangeMap> rangeMaps = new ArrayList<>();
            List<Range> rangeList = new ArrayList<>();
            // Convert string to long
            for (String s : seedStrings) {
                seedNumbers.add(Long.parseLong(s));
            }
            // Iterate the lines of input and fill data structures
            String line;
            while ((line = br.readLine()) != null) {
                // Check if line is empty
                if (line.equals("")) {
                    continue;
                }
                // Check if line contains "map"
                if (line.contains("map")) {
                    // if list has entries when word "map" is found, sore it in rangeMaps
                    if (!rangeList.isEmpty()) {
                        rangeMaps.add(new RangeMap(rangeList));
                    }
                    // Create new rangeList for next dataset
                    rangeList = new ArrayList<>();
                } else {
                    // Not clear line and not "map" so add the data to list
                    rangeList.add(new Range(line));
                }
            }
            // Last RangeMap after iteration is complete added to list
            rangeMaps.add(new RangeMap(rangeList));

            // Setting closestDestination to max value since we're looking for smaller numbers
            long closestDestination = Long.MAX_VALUE;
            // Iterate seedNumbers with +2 since every second entry is a range.
            for (int i = 0; i < seedNumbers.size(); i += 2) {
                // Getting the range
                long range = seedNumbers.get(i+1);
                //Getting the seed number
                long temp = seedNumbers.get(i);
                // Iterate seeds in a range until a valid match is found
                for (long j = temp; j < temp + range; j++) {
                    // Gets a value (distance to destination) and the remaining range since the lowest match will be closest destination in that range.
                    long[] valueAndRange = calcValueAndRange(j, rangeMaps);
                    // Checking if destination os closer/lower that previous, if so set closestDestination to new value.
                    closestDestination = Math.min(valueAndRange[0], closestDestination);
                    // adding the remaining range to j, returned value will be either 0 or the actual remaining range.
                    // If match is found j will go out of bound and finish the loop.
                    j += valueAndRange[1];
                }
            }
            System.out.println(closestDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Log end time
        long endTime = System.currentTimeMillis();
        // Print end time in millis
        System.out.println((endTime - startTime) + " Milliseconds");
    }

    /**
     * Iterates through RangeMaps for a seed number to get a destination
     * @param l Seed number
     * @param r List of RangeMap
     * @return Array with distance to destination and remaining range
     */
    public static long[] calcValueAndRange(long l, List<RangeMap> r) {
        long range = Long.MAX_VALUE;
        for (RangeMap rm : r) {
            range = Math.min(range, rm.convertStartToDestWithRange(l)[1]);
            l = rm.convertStartToDestWithRange(l)[0];
        }
        return new long[]{l, range};
    }

    public static void main(String[] args) {
        parseFile("src/day5/input.txt");
    }
}

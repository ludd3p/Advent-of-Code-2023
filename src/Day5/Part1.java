package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void parseFile(String filePath) {
        long startTime = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] seedStrings = br.readLine().split(":")[1].trim().split(" ");
            List<Long> seedNumbers = new ArrayList<>();
            List<RangeMap> rangeMaps = new ArrayList<>();
            List<Range> rangeList = new ArrayList<>();
            for (String s : seedStrings) {
                seedNumbers.add(Long.parseLong(s));
            }
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (line.contains("map")) {
                    if (!rangeList.isEmpty()) {
                        rangeMaps.add(new RangeMap(rangeList));
                    }
                    rangeList = new ArrayList<>();
                } else {
                    rangeList.add(new Range(line));
                }
            }
            rangeMaps.add(new RangeMap(rangeList));

            long closestDestination = Long.MAX_VALUE;
            for (Long seed : seedNumbers) {
                long temp = seed;
                for (RangeMap r : rangeMaps) {
                    temp = r.convertStartToDest(temp);
                }
                if (temp < closestDestination) {

                    closestDestination = temp;
                }
            }
            System.out.println(closestDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000 + " microseconds");
    }



    public static void main(String[] args) {
        parseFile("src/day5/input.txt");
    }
}

package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<List<Character>> rows = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                List<Character> cols = line.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toCollection(ArrayList::new));
                rows.add(cols);
            }
            System.out.println(calculateDistances(rows));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static long calculateDistances(List<List<Character>> rows) {
        long columnCounter = 0;
        long rowCounter = 0;
        long[] columnOffset = new long[rows.get(0).size()];
        long[] rowOffset = new long[rows.size()];
        long totalDistance = 0;
        List<Galaxy> galaxyList = new ArrayList<>();

        // Calculate the offsets horizontally
        for (int x = 0; x < rows.get(0).size(); x++) {
            boolean empty = true;
            for (List<Character> row : rows) {
                if (row.get(x) == '#') {
                    empty = false;
                    break;
                }
            }
            if (empty) rowCounter += 999999;
            columnOffset[x] = rowCounter;
        }

        // Calculate the offsets vertically
        for (int y = 0; y < rows.size(); y++) {
            boolean empty = true;
            for (int x = 0; x < rows.get(0).size(); x++) {
                if (rows.get(y).get(x) == '#') {
                    empty = false;
                    break;
                }
            }
            if (empty) columnCounter += 999999;
            rowOffset[y] = columnCounter;
        }

        // Find galaxies
        for (int y = 0; y < rows.size(); y++) {
            for (int x = 0; x < rows.get(0).size(); x++) {
                if (rows.get(y).get(x) == '#') {
                    galaxyList.add(new Galaxy(y + rowOffset[y], x + columnOffset[x]));
                }
            }
        }

        // Calculate the distance between each pair of galaxies
        for (int i = 0; i < galaxyList.size(); i++) {
            for (int j = i + 1; j < galaxyList.size(); j++) {
                totalDistance += calculateDistance(galaxyList.get(i), galaxyList.get(j));
            }
        }
        return totalDistance;
    }


    private static long calculateDistance(Galaxy galaxy1, Galaxy galaxy2) {
        // Manhattan
        return Math.abs(galaxy1.getRow() - galaxy2.getRow()) + Math.abs(galaxy1.getCol() - galaxy2.getCol());
    }

    public static void main(String[] args) {
        parseFile("src/Day11/input.txt");
    }
}
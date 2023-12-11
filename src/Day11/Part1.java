package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<List<Character>> rows = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                List<Character> cols = line.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toCollection(ArrayList::new));
                // Expand the universe: add a copy of the row below the original row if it doesn't contain '#'
                rows.add(cols);
                if (!cols.contains('#')) {
                    rows.add(new ArrayList<>(cols));
                }
            }

            // Expand the universe column-wise: add a copy of each column to the right if it doesn't contain '#'
            int numCols = rows.get(0).size();
            for (int i = numCols-1; i > 0; i--) {
                boolean shouldExpand = true;
                for (List<Character> characters : rows) {
                    if (characters.get(i) == '#') {
                        shouldExpand = false;
                        break;
                    }
                }

                if (shouldExpand) {
                    for (List<Character> row : rows) {
                        row.add(i, row.get(i));
                    }
                }
            }
            System.out.println(calculateDistances(rows));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static int calculateDistances(List<List<Character>> rows) {
        int totalDistance= 0;
        List<Galaxy> galaxyList = new ArrayList<>();
        // Find galaxies
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(0).size(); j++) {
                if (rows.get(i).get(j) == '#') galaxyList.add(new Galaxy(i, j));
            }
        }
        // Calculate the distance between each pair of galaxies
        for (int i = 0; i < galaxyList.size(); i++) {
            for (int j = i + 1; j < galaxyList.size(); j++) {
                totalDistance += calculateDistance(galaxyList.get(i), galaxyList.get(j));
            }
        }
        System.out.println(galaxyList.size());
        return totalDistance;
    }

    private static int calculateDistance(Galaxy galaxy1, Galaxy galaxy2) {
        // Manhattan
        return Math.abs(galaxy1.getRow() - galaxy2.getRow()) + Math.abs(galaxy1.getCol() - galaxy2.getCol());
    }

    public static void main(String[] args) {
        parseFile("src/Day11/input.txt");
    }
}

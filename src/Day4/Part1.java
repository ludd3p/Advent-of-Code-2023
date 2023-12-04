package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part1 {
    private static int pointsSum = 0;
    public static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                checkLine(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pointsSum);
    }

    public static void checkLine(String s) {
        int localPoints = 0;
        // Split the string by colon
        String[] parts = s.split(":");

        // Extract numbers after the colon
        String numbersString = parts[1].trim();

        // Split the numbers string by pipe
        String[] allNumbers = numbersString.split("\\|");

        String cleanedWinningNumbers = allNumbers[0].replaceAll("\\s+", " ").trim();
        String cleanedOurNumbers = allNumbers[1].replaceAll("\\s+", " ").trim();

        String[] winningNumbers = cleanedWinningNumbers.split(" ");
        String[] ourNumbers = cleanedOurNumbers.split(" ");

        Set<String> winningNumbersSet = new HashSet<>(Arrays.asList(winningNumbers));

        for (String ourNumber : ourNumbers) {
            if (winningNumbersSet.contains(ourNumber)) {
                if (localPoints == 0) localPoints = 1;
                else localPoints*=2;
            }
        }

        pointsSum += localPoints;
    }

    public static void main(String[] args) {
        readFile("src/Day4/input.txt");
    }
}



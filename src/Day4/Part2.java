package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part2 {
    private static int pointsSum = 0;

    /**
     * Reads a file and creates a list of tickets and their amount of copies.
     * @param filePath Path to test data.
     */
    public static void readFile(String filePath) {
        // Creates list with custom data class
        List<ScratchCard> scratchCardList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Iterate lines of test data and create instances of data class
            while ((line = br.readLine()) != null) {
                scratchCardList.add(new ScratchCard(line));
            }
            // Iterate list of tickets
            for (int i = 0; i < scratchCardList.size(); i++) {
                ScratchCard sc = scratchCardList.get(i);
                // Iterate each ticket for the amount of copies
                for (int j = 0; j < sc.getCopies(); j++) {
                    int wins = checkLine(sc.getKey());
                    // Add new copies for following tickets
                    for (int k = 1; k <= wins && i + k < scratchCardList.size(); k++) {
                        scratchCardList.get(i + k).addCopy();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pointsSum);
    }

    /**
     * Checks a ticket for winning numbers and returns the amount.
     * @param s String of ticket to be checked
     * @return number of wins as int
     */
    public static int checkLine(String s) {
        int localPoints = 0;
        // Split the string by colon, keeps rows of winning and our numbers
        String[] parts = s.split(":");

        // Extract numbers after the colon , keeps string of winning and our numbers
        String numbersString = parts[1].trim();

        // Split the numbers string, now we have entries for winning and our numbers
        String[] allNumbers = numbersString.split("\\|");

        // Clean the strings of extra whitespaces
        String cleanedWinningNumbers = allNumbers[0].replaceAll("\\s+", " ").trim();
        String cleanedOurNumbers = allNumbers[1].replaceAll("\\s+", " ").trim();

        // Split the string into individual numbers
        String[] winningNumbers = cleanedWinningNumbers.split(" ");
        String[] ourNumbers = cleanedOurNumbers.split(" ");

        //Create a set of winning numbers for fast lookup
        Set<String> winningNumbersSet = new HashSet<>(Arrays.asList(winningNumbers));

        // Go through our numbers and compare to winning numbers
        for (String ourNumber : ourNumbers) {
            // If we win, add to local points
            if (winningNumbersSet.contains(ourNumber)) {
                localPoints++;
            }
        }
        // Add to the tally of total tickets scratched
        pointsSum++;
        // Return our amount of wins
        return localPoints;
    }

    public static void main(String[] args) {
        readFile("src/Day4/input.txt");
    }
}



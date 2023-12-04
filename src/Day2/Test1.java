package Day2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCounter = 1;
            int maxBlue = 14;
            int maxRed = 12;
            int maxGreen = 13;
            int sumOfGameIds = 0;
            boolean gameAcceptable;

            while ((line = br.readLine()) != null) {
                Map<String, Integer> colorCounter = new HashMap<>();
                String result = line.split(":")[1];
                String[] rounds = result.split(";");
                gameAcceptable = true;

                for (String round : rounds) {
                    String[] roundResults = round.split(",");
                    for (String roundResult : roundResults) {
                        roundResult = roundResult.trim();
                        String[] colorAndCount = roundResult.split(" ");
                        colorCounter.put(colorAndCount[1], Integer.parseInt(colorAndCount[0]));
                    }

                    if (colorCounter.getOrDefault("red", 0) > maxRed ||
                            colorCounter.getOrDefault("blue", 0) > maxBlue ||
                            colorCounter.getOrDefault("green", 0) > maxGreen) {
                        gameAcceptable = false;
                        break;
                    }
                }

                if (gameAcceptable) {
                    sumOfGameIds += lineCounter;
                    System.out.println("Accepted round: " + lineCounter);
                }

                lineCounter++;
            }

            System.out.println(sumOfGameIds);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile("src/Day2/input.txt");
    }
}

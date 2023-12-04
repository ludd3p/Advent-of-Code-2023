package Day2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test2 {

    public static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCounter = 1;
            int sumOfPower = 0;

            while ((line = br.readLine()) != null) {
                Map<String, Integer> maxColorCount = new HashMap<>();
                String result = line.substring(line.indexOf(":") + 1);
                String[] rounds = result.split(";");
                for (String round : rounds) {
                    Map<String, Integer> colorCounter = new HashMap<>();
                    for (String roundResult : round.split(",")) {
                        roundResult = roundResult.trim();
                        String[] colorAndCount = roundResult.split(" ");
                        String color = colorAndCount[1];
                        int count = Integer.parseInt(colorAndCount[0]);
                        colorCounter.put(color, colorCounter.getOrDefault(color, 0) + count);
                    }
                    for (Map.Entry<String, Integer> entry : colorCounter.entrySet()) {
                        String color = entry.getKey();
                        int count = entry.getValue();
                        maxColorCount.put(color, Math.max(maxColorCount.getOrDefault(color, 0), count));
                    }
                }
                System.out.print("Game " + lineCounter + ": ");
                int powerSum = 1;
                for (Map.Entry<String, Integer> entry : maxColorCount.entrySet()) {
                    int count = entry.getValue();
                    System.out.print(count + " " + entry.getKey() + ", ");
                    powerSum *= count;
                }
                System.out.println("Power Sum: " + powerSum);
                sumOfPower += powerSum;
                lineCounter++;
            }

            System.out.println("Sum of Power: " + sumOfPower);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile("src/Day2/input.txt");
    }

}

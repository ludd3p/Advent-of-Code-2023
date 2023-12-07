package Day7.Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        long totalWinnings = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<CamelCard> allHands = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split(" ");
                allHands.add(new CamelCard(split[0].toCharArray(), Integer.parseInt(split[1])));
            }
            allHands.sort(new CamelCardComparator());
            for (int i = 1; i <= allHands.size(); i++) {
                totalWinnings += (long) allHands.get(i - 1).getBet() * i;
            }
            System.out.println("Resultatet: " + totalWinnings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static void main(String[] args) {
        parseFile("src/Day7/input.txt");
    }
}

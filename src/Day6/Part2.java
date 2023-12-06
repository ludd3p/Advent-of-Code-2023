package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long time = 0;
            long distance = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains("Time")) time = Long.parseLong((line.split(":")[1].replaceAll(" ", ""))) ;
                else distance = Long.parseLong((line.split(":")[1].replaceAll(" ", "")));
            }
            System.out.println(calculateRange(time, distance));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static int calculateRange(long time, long distance) {
        int range = 0;
        for (int i = 0; i <= time; i++) {
            if (i * (time-i) > distance) {
                range++;
            }
        }
        return range;
    }

    public static void main(String[] args) {
        parseFile("src/Day6/input.txt");
    }
}

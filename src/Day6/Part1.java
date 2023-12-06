package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {

    public static void parseFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<Integer> times = null;
            ArrayList<Integer> distances = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("Time")) times = convertStringToInt((line.split(":")[1].trim().split("\\s+")));
                else distances = convertStringToInt((line.split(":")[1].trim().split("\\s+")));
            }
            System.out.println(calculateNumberOfWays(times, distances));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Integer> convertStringToInt(String[] s) {
        ArrayList<Integer> intList = new ArrayList<>();
        for (String string : s) {
            intList.add(Integer.parseInt(string));
        }
        return intList;
    }

    public static int calculateNumberOfWays(ArrayList<Integer> times, ArrayList<Integer> distances) {
        int numberOfWays = 1;
        for (int i = 0; i < times.size(); i++) {
            numberOfWays *= calculateRange(times.get(i), distances.get(i));
        }
        return numberOfWays;
    }

    public static int calculateRange(int time, int distance) {
        int range = 0;
        for (int i = 0; i <= time; i++) {

            if (i * (time-i) > distance) {
                range++;
            }
        }

        System.out.println(range);
        return range;
    }

    public static void main(String[] args) {
        parseFile("src/Day6/input.txt");
    }
}

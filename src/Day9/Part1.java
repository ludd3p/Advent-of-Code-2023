package Day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        long sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] nbrsStrings;
            while ((line = br.readLine()) != null) {
                nbrsStrings = line.trim().split(" ");
                List<Integer> nbrs = Arrays.stream(nbrsStrings).map(Integer::valueOf).toList();
                sum += calc(nbrs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum: " + sum);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static int calc(List<Integer> nbrs) {
        List<Integer> nextNbrs = new ArrayList<>();
        for (int i = 0; i < nbrs.size()-1; i++) {
            nextNbrs.add(nbrs.get(i+1) - nbrs.get(i));
        }
        if (nextNbrs.stream().allMatch(element -> element == 0)) return nbrs.getLast();
        else return calc(nextNbrs) + nbrs.getLast();
    }

    public static void main(String[] args) {
        parseFile("src/Day9/input.txt");
    }
}

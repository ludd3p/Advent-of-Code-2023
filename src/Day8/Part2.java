package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Part2 {
    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        char[] instructions;
        Map<String, Node> nodes = new HashMap<>();
        ArrayList<String> aNodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            instructions = br.readLine().trim().toCharArray();
            br.readLine();
            String[] nodeString;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[ ()]", "");
                nodeString = line.split("[=,]");
                nodes.put(nodeString[0], new Node(nodeString[1], nodeString[2]));
                if (nodeString[0].charAt(2) == 'A') aNodes.add(nodeString[0]);
            }
            System.out.println("Total steps taken: " + executeInstructions(instructions, nodes, aNodes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }


    public static long executeInstructions(char[] instructions, Map<String, Node> nodes, ArrayList<String> aNodes) {
        ArrayList<Integer> stepList = new ArrayList<>();
        int stepsTaken = 0;
        String currentNode;
        for (String s : aNodes) {
            currentNode = s;
            while (currentNode.charAt(2) != 'Z') {
                for (char c : instructions) {
                    if (currentNode.charAt(2) == 'Z') break;
                    if (c == 'L') currentNode = nodes.get(currentNode).getLeftNode();
                    else currentNode = nodes.get(currentNode).getRightNode();
                    stepsTaken++;
                }
            }
            stepList.add(stepsTaken);
            stepsTaken = 0;
        }
        return findLCM(stepList);
    }

    // Finds the Least Common Multiple (LCM) of a list of integers.
    public static long findLCM(ArrayList<Integer> steps) {
        // Initialize LCM with the first element in the list
        long lcm = steps.get(0);

        // Iterate through the rest of the elements and update LCM
        for (int i = 1; i < steps.size(); i++) {
            lcm = calculateLCM(lcm, steps.get(i));
        }

        // Return the final LCM
        return lcm;
    }

    // Calculate the LCM  using their GCD.
    private static long calculateLCM(long a, long b) {
        // LCM calculation using the formula: LCM(a, b) = (a * b) / GCD(a, b)
        return (a * b) / calculateGCD(a, b);
    }

    // Calculates the greatest common divisor using Euclidean algorithm.
    private static long calculateGCD(long a, long b) {
        // Euclidean algorithm for GCD calculation
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        // Return the final GCD
        return a;
    }

    public static void main(String[] args) {
        parseFile("src/Day8/input.txt");
    }
}
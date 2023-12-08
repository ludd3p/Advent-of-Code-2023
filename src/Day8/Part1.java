package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Part1 {
    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        char[] instructions;
        Map<String, Node> nodes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            instructions = br.readLine().trim().toCharArray();
            br.readLine();
            String[] nodeString;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[ ()]", "");
                nodeString = line.split("[=,]");
                nodes.put(nodeString[0], new Node(nodeString[1], nodeString[2]));
            }
            System.out.println("Total steps taken: " + executeInstructions(instructions, nodes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static int executeInstructions(char[] instructions, Map<String, Node> nodes) {
        int stepsTaken = 0;
        String currentNode = "AAA";

        while (!currentNode.equals("ZZZ")) {
            System.out.println("Starting a round of instructions, steps taken: " + stepsTaken);
            for (char c : instructions) {
                if (currentNode.equals("ZZZ")) return stepsTaken;
                if (c == 'L') currentNode = nodes.get(currentNode).getLeftNode();
                else currentNode = nodes.get(currentNode).getRightNode();
                stepsTaken++;
            }
        }

        return stepsTaken;
    }

    public static void main(String[] args) {
        parseFile("src/Day8/input.txt");
    }
}

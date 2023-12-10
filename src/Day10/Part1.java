package Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Part1 {

    public static void parseFile(String filePath) {
        long start = System.currentTimeMillis();
        int lineCount = 0;
        Node[][] map = new Node[140][140];
        List<Node> allNodes = new ArrayList<>();
        Node startNode = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                for (int x = 0; x < line.length()-1; x++) {
                    allNodes.add(map[x][lineCount] = new Node(line.charAt(x), x, lineCount));
                    if (line.charAt(x) == 'S') startNode = map[x][lineCount];
                }
                lineCount++;
            }

            for (Node n : allNodes) {
                n.addAdjacent(map);
            }

            for (Node n : allNodes) {
                for (Node no : n.getAdjacentNodes().keySet()) {
                    if (no.getC() == 'S') no.addAdjecentNode(n, 1);
                }
            }

            Graph graph = new Graph(allNodes);
            graph = dijkstra(graph, startNode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int distance = allNodes.stream().mapToInt(Node::getDistance).filter(d -> d!= Integer.MAX_VALUE).max().orElseThrow();
        System.out.println("Result: " + distance);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static Graph dijkstra(Graph graph, Node source) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public static void calculateMinimumDistance(Node evaluationNode,  Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static void main(String[] args) {
        parseFile("src/Day10/input.txt");
    }
}

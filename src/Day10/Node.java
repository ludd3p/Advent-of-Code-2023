package Day10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Basic node implementation for dijkstra algorithm
 */
public class Node {
    private char c;
    private int x;
    private int y;
    private int distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    private List<Node> shortestPath = new LinkedList<>();

    public Node(char c, int x, int y) {
        this.c = c;
        this.x = x;
        this.y = y;
    }

    /**
     * Adds adjecent nodes based on selfs character.
     * @param graph
     */
    public void addAdjacent(Node[][] graph) {
        if (c == '|') {
            addIfExists(getUp(graph));
            addIfExists(getDown(graph));
        } else if (c == '-') {
            addIfExists(getLeft(graph));
            addIfExists(getRight(graph));
        } else if (c == 'J') {
            addIfExists(getLeft(graph));
            addIfExists(getUp(graph));
        } else if (c == 'L') {
            addIfExists(getRight(graph));
            addIfExists(getUp(graph));
        } else if (c == 'F') {
            addIfExists(getDown(graph));
            addIfExists(getRight(graph));
        } else if (c == '7') {
            addIfExists(getLeft(graph));
            addIfExists(getDown(graph));
        }
    }

    private void addIfExists(Node node) { if (node != null) adjacentNodes.put(node, 1); }

    public Node getUp(Node[][] graph) { return y > 0 ? graph[x][y-1] : null; }
    public Node getDown(Node[][] graph) { return y < graph[0].length -1 ? graph[x][y+1] : null; }
    public Node getLeft(Node[][] graph) { return x > 0 ? graph[x-1][y] : null; }
    public Node getRight(Node[][] graph) { return x < graph.length - 1 ? graph[x+1][y] : null;  }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public char getC() {
        return c;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void addAdjecentNode(Node n, int i) {
        adjacentNodes.put(n, i);
    }
}

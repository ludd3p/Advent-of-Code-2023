package Day10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Default Graph implementaion for Dijkstra
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public Graph(List<Node> allNodes) {
        nodes.addAll(allNodes);
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

}
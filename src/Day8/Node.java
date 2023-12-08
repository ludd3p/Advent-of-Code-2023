package Day8;

public class Node {
    private final String leftNode;
    private final String rightNode;

    public Node(String left, String right) {
        leftNode = left;
        rightNode = right;
    }

    public String getLeftNode() {
        return leftNode;
    }

    public String getRightNode() {
        return rightNode;
    }
}

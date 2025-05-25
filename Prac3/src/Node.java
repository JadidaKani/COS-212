public class Node implements Comparable<Node> {
    public int studentNumber; // the key for the node
    public Integer mark; // represents the mark, the value of the node?
    public Node left;
    public Node right;

    public Node(int studentNumber, Integer mark) {
        this.studentNumber = studentNumber;
        this.mark = mark;
        left = null;
        right = null;
    }

    public String toString() {
        return "[u" + studentNumber + ":" + (mark == null ? "null" : mark) + "%]";
    }

    @Override
    public int compareTo(Node o) {
        return ((Integer) studentNumber).compareTo((Integer) o.studentNumber);
    }

    public int compareToMark(Node o) {
        return mark == null ? (o.mark == null ? 0 : -1) : (o.mark == null ? 1 : mark.compareTo(o.mark));
    }
}

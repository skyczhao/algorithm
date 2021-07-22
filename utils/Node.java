package utils;


/**
 * Node
 *
 * @author tobin
 * @since 2021-07-22
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

package leetcode;

import java.util.*;

/**
 * clone_graph
 * https://leetcode-cn.com/problems/clone-graph/
 *
 * @since 2020-08-12
 */
public class clone_graph {
    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Node node_1 = new Node(1);
        Node node_2 = new Node(2);
        Node node_3 = new Node(3);
        Node node_4 = new Node(4);
        node_1.neighbors.add(node_2);
        node_1.neighbors.add(node_4);
        node_2.neighbors.add(node_1);
        node_2.neighbors.add(node_3);
        node_3.neighbors.add(node_2);
        node_3.neighbors.add(node_4);
        node_4.neighbors.add(node_1);
        node_4.neighbors.add(node_3);

        Node res = cloneGraph(node_1);
        System.out.println(res);
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> originNodes = new HashMap<>();
        List<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.get(0);
            queue.remove(0);
            if (originNodes.containsKey(curr.val)) {
                continue;
            }

            queue.addAll(curr.neighbors);
            originNodes.put(curr.val, curr);
        }

        Map<Integer, Node> copyNode = new HashMap<>();
        for (Integer key : originNodes.keySet()) {
            Node origin = originNodes.get(key);
            Node copy = null;
            if (copyNode.containsKey(origin.val)) {
                copy = copyNode.get(origin.val);
            } else {
                copy = new Node(origin.val);
                copyNode.put(copy.val, copy);
            }

            for (Node oriNei : origin.neighbors) {
                Node copyNei = null;
                if (copyNode.containsKey(oriNei.val)) {
                    copyNei = copyNode.get(oriNei.val);
                } else {
                    copyNei = new Node(oriNei.val);
                    copyNode.put(copyNei.val, copyNei);
                }

                copy.neighbors.add(copyNei);
            }
        }

        return copyNode.get(node.val);
    }
}

package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedTransferQueue;

/**
 * BinaryTreeZigzagLevelOrderTraversal
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 103. 二叉树的锯齿形层序遍历
 *
 * @since 2020-12-22
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = Trees.fromIntegers(new Integer[]{3, 9, 20, null, null, 15, 7});

        BinaryTreeZigzagLevelOrderTraversal sol = new BinaryTreeZigzagLevelOrderTraversal();
        List<List<Integer>> res = sol.zigzagLevelOrder(root);
        for (List<Integer> row : res) {
            row.forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }

        boolean inv = false;
        Queue<TreeNode> queue = new LinkedTransferQueue<>();
        List<List<Integer>> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            queue.clear();
            List<Integer> row = new LinkedList<>();
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                row.add(curr.val);

                if (inv) {
                    if (curr.right!= null) queue.add(curr.right);
                    if (curr.left!= null) queue.add(curr.left);
                } else {
                    if (curr.left!= null) queue.add(curr.left);
                    if (curr.right!= null) queue.add(curr.right);
                }
            }
            res.add(row);

            inv = !inv;
            while (!queue.isEmpty()) {
                stack.add(queue.poll());
            }
        }

        return res;
    }
}

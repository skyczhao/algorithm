package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTreePostorderTraversal
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @since 2020-09-29
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        Integer[] values = {1, null, 2, 3};
        TreeNode head = Trees.fromIntegers(values);

        BinaryTreePostorderTraversal sol = new BinaryTreePostorderTraversal();
        System.out.println(sol.postorderTraversal(head));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<Object> stack = new Stack<>();
        stack.push(root);

        List<Integer> result = new LinkedList<>();
        while (!stack.isEmpty()) {
            Object curr = stack.pop();
            if (curr == null) {
                // skip
            } else if (curr instanceof TreeNode) {
                TreeNode node = (TreeNode) curr;
                if (node.left == null && node.right == null) {
                    result.add(node.val);
                } else {
                    stack.add(node.val);
                    stack.add(node.right);
                    stack.add(node.left);
                }
            } else {
                result.add((int) curr);
            }
        }
        return result;
    }
}

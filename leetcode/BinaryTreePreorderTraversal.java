package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTreePreorderTraversal
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * @since 2020-10-27
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode head = Trees.fromIntegers(new Integer[]{1, null, 2, 3});
        BinaryTreePreorderTraversal sol = new BinaryTreePreorderTraversal();
        List<Integer> res = sol.preorderTraversal(head);
        for (Integer value : res) {
            System.out.println(value);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            if (head != null) {
                res.add(head.val);
                stack.add(head.right);
                stack.add(head.left);
            }
        }

        return res;
    }
}

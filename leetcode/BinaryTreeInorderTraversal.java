package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTreeInorderTraversal
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * @since 2020-09-14
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode head = Trees.fromIntegers(new Integer[]{1, null, 2, 3});

        BinaryTreeInorderTraversal sol = new BinaryTreeInorderTraversal();
        List<Integer> res = sol.inorderTraversal(head);
        for (Integer value : res) {
            System.out.println(value);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>(); // 刷题一般不返回null
        if (root == null) {
            return res;
        }
        Stack<TreeNode> next = new Stack<>();

        next.add(root);
        TreeNode currLeft = root.left;
        while (currLeft != null) {
            next.add(currLeft);
            currLeft = currLeft.left;
        }
        while (!next.isEmpty()) {
            TreeNode curr = next.pop();
            res.add(curr.val);

            TreeNode toAdd = curr.right;
            while (toAdd != null) {
                next.add(toAdd);
                toAdd = toAdd.left;
            }
        }

        return res;
    }

}

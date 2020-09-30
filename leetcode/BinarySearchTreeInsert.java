package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * BinarySearchTreeInsert
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 *
 * @since 2020-09-30
 */
public class BinarySearchTreeInsert {
    public static void main(String[] args) {
        Integer[] values = new Integer[]{4, 2, 7, 1, 3};
        TreeNode head = Trees.fromIntegers(values);

        BinarySearchTreeInsert sol = new BinarySearchTreeInsert();
        TreeNode res = sol.insertIntoBST(head, 5);
        System.out.println();
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val); // null插入值就是新的树
        }

        TreeNode curr = root;
        while (curr != null) {
            if (val > curr.val) {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            } else if (val < curr.val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                break;
            }
        }
        return root;
    }
}

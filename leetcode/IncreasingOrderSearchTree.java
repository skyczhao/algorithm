package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * IncreasingOrderSearchTree
 * https://leetcode-cn.com/problems/increasing-order-search-tree/
 * 897. 递增顺序搜索树
 * https://leetcode-cn.com/problems/increasing-order-search-tree/solution/yuan-di-xuan-zhuan-di-gui-by-oshdyr-s5a7/
 *
 * @since 2021-04-25
 */
public class IncreasingOrderSearchTree {

    public static void main(String[] args) {
        // TODO: add preorder/inorder/postorder function to Trees
        TreeNode head = Trees.fromIntegers(new Integer[]{5, 3, 6, 2, 4, null, 8, 1, null, null, null, 7, 9});

        IncreasingOrderSearchTree sol = new IncreasingOrderSearchTree();
        TreeNode newHead = sol.increasingBST(head);

        System.out.println(head.val);
        System.out.println(newHead.val);
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root;
        while (head.left != null) {
            TreeNode newHead = head.left;
            head.left = null;

            TreeNode mostRight = newHead;
            while (mostRight.right != null) {
                mostRight = mostRight.right;
            }

            mostRight.right = head;
            head = newHead;
        }

        if (head.right != null) {
            TreeNode newRight = increasingBST(head.right);
            head.right = newRight;
        }

        return head;
    }
}

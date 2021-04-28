package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * RangeSumOfBst
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 * 938. 二叉搜索树的范围和
 * https://leetcode-cn.com/problems/range-sum-of-bst/solution/yi-dong-de-di-gui-si-lu-by-oshdyr-ihz2/
 *
 * @since 2021-04-27
 */
public class RangeSumOfBst {
    public static void main(String[] args) {
        RangeSumOfBst sol = new RangeSumOfBst();
        TreeNode head = Trees.fromIntegers(new Integer[]{10, 5, 15, 3, 7, null, 18});
        System.out.println(sol.rangeSumBST(head, 7, 15));
        // TreeNode head = Trees.fromIntegers(new Integer[]{10, 5, 15, 3, 7, 13, 18, 1, null, 6});
        // System.out.println(sol.rangeSumBST(head, 6, 10));
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        } else if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high) + root.val + rangeSumBST(root.right, low, high);
        }
    }
}

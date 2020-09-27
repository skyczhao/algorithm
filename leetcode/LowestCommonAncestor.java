package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * LowestCommonAncestor
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @since 2020-09-27
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        Integer[] values = {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        TreeNode head = Trees.fromIntegers(values);

        LowestCommonAncestor sol = new LowestCommonAncestor();
        TreeNode c1 = sol.lowestCommonAncestor(head, new TreeNode(2), new TreeNode(8));
        System.out.println(c1.val);

        TreeNode c2 = sol.lowestCommonAncestor(head, new TreeNode(2), new TreeNode(4));
        System.out.println(c2.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return root;
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}


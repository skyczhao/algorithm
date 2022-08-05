package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * AddOneRowToTree
 * https://leetcode.cn/problems/add-one-row-to-tree/
 * 623. 在二叉树中增加一行
 * https://leetcode.cn/problems/add-one-row-to-tree/solution/shu-by-oshdyr-7wu6/
 *
 * @author Tobin
 * @since 2022-08-05
 */
public class AddOneRowToTree {
    public static void main(String[] args) {

        TreeNode tree1 = Trees.fromIntegers(new Integer[]{4, 2, 6, 3, 1, 5});
        TreeNode tree2 = Trees.fromIntegers(new Integer[]{4, 2, null, 3, 1});
        TreeNode tree3 = Trees.fromIntegers(new Integer[]{1, 2, 3, 4});

        AddOneRowToTree sol = new AddOneRowToTree();
        TreeNode res1 = sol.addOneRow(tree1, 1, 2);
        TreeNode res2 = sol.addOneRow(tree2, 100, 3);
        TreeNode res3 = sol.addOneRow(tree3, 5, 4);
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth < 2) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        dfs_add(root, 1, val, depth);
        return root;
    }

    private void dfs_add(TreeNode curr, int level, int val, int depth) {
        if (curr == null) {
            return;
        }

        if (level < depth - 1) {
            dfs_add(curr.left, level + 1, val, depth);
            dfs_add(curr.right, level + 1, val, depth);
        } else if (level == depth - 1) {
            // bug 1: no need to avoid null
            TreeNode newLeft = new TreeNode(val);
            newLeft.left = curr.left;
            curr.left = newLeft;

            TreeNode newRight = new TreeNode(val);
            newRight.right = curr.right;
            curr.right = newRight;
        } else {
            // error
        }
    }
}

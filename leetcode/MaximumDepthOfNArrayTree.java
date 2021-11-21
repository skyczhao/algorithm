package leetcode;

import utils.NTreeNode;

/**
 * MaximumDepthOfNArrayTree
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 * 559. N 叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/solution/di-gui-jie-ti-by-oshdyr-snf6/
 *
 * @author tobin
 * @since 2021-11-21
 */
public class MaximumDepthOfNArrayTree {

    public static void main(String[] args) {
        MaximumDepthOfNArrayTree sol = new MaximumDepthOfNArrayTree();

//        TreeNode root = Trees.fromIntegers(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14});
        System.out.println(sol.maxDepth(null));
    }

    public int maxDepth(NTreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth_max = 0;
        if (root.children != null) {
            for (NTreeNode child : root.children) {
                int depth_child = maxDepth(child);
                if (depth_child > depth_max) {
                    depth_max = depth_child;
                }
            }
        }
        return depth_max + 1;
    }
}

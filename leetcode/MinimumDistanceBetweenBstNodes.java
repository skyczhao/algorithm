package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * MinimumDistanceBetweenBstNodes
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 * 783. 二叉搜索树节点最小距离
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/solution/di-gui-chu-li-by-oshdyr-7vsc/
 *
 * @since 2021-04-13
 */
public class MinimumDistanceBetweenBstNodes {

    public static void main(String[] args) {
        MinimumDistanceBetweenBstNodes sol = new MinimumDistanceBetweenBstNodes();

        TreeNode head = Trees.fromIntegers(new Integer[]{27, null, 34, null, 58, 50, null, 44});
        System.out.println(sol.minDiffInBST(head));
    }

    class RangeResult {
        public int minDist;
        public int maxValue;
        public int minValue;

        public RangeResult(int minDist, int maxValue, int minValue) {
            this.minDist = minDist;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public int minDiffInBST(TreeNode root) {
        return getMinDiff(root).minDist;
    }

    private RangeResult getMinDiff(TreeNode root) {
        if (root == null) {
            return null;
        }

        RangeResult res = new RangeResult(Integer.MAX_VALUE, root.val, root.val);

        RangeResult leftMin = getMinDiff(root.left);
        if (leftMin != null) {
            res.minValue = leftMin.minValue;
            int leftDist = root.val - leftMin.maxValue;
            if (leftDist < res.minDist) {
                res.minDist = leftDist;
            }
            if (leftMin.minDist < res.minDist) { // bug: 漏比较
                res.minDist = leftMin.minDist;
            }
        }

        RangeResult rightMin = getMinDiff(root.right);
        if (rightMin != null) {
            res.maxValue = rightMin.maxValue;
            int rightDist = rightMin.minValue - root.val;
            if (rightDist < res.minDist) {
                res.minDist = rightDist;
            }
            if (rightMin.minDist < res.minDist) { // bug: 漏比较
                res.minDist = rightMin.minDist;
            }
        }

        return res;
    }
}

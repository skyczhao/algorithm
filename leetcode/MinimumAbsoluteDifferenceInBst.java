package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * MinimumAbsoluteDifferenceInBst
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 *
 * @since 2020-10-12
 */
public class MinimumAbsoluteDifferenceInBst {
    public static void main(String[] args) {
        Integer[] values = new Integer[]{1, null, 3, 2};
        TreeNode root = Trees.fromIntegers(values);

        MinimumAbsoluteDifferenceInBst sol = new MinimumAbsoluteDifferenceInBst();
        System.out.println(sol.getMinimumDifference(root));
    }

    public int getMinimumDifference(TreeNode root) {
        SubTreeProp subTreeProp = getSubTreeProp(root);
        return subTreeProp.minDist;
    }

    private SubTreeProp getSubTreeProp(TreeNode root) {
        if (root == null) {
            return null;
        }

        int max = 0;
        int min = 0;
        int minDist = Integer.MAX_VALUE;
        if (root.left != null) {
            SubTreeProp leftTreeProp = getSubTreeProp(root.left);
            if (leftTreeProp.minDist < minDist) {
                minDist = leftTreeProp.minDist;
            }
            if (root.val - leftTreeProp.max < minDist) {
                minDist = root.val - leftTreeProp.max;
            }
            min = leftTreeProp.min;
        } else {
            min = root.val;
        }

        if (root.right != null) {
            SubTreeProp rightTreeProp = getSubTreeProp(root.right);
            if (rightTreeProp.minDist < minDist) {
                minDist = rightTreeProp.minDist;
            }
            if (rightTreeProp.min - root.val < minDist) {
                minDist = rightTreeProp.min - root.val;
            }
            max = rightTreeProp.max;
        } else {
            max = root.val;
        }

        return new SubTreeProp(max, min, minDist);
    }

    class SubTreeProp {
        public int max;
        public int min;
        public int minDist;

        public SubTreeProp(int max, int min, int minDist) {
            this.max = max;
            this.min = min;
            this.minDist = minDist;
        }
    }
}

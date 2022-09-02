package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * LongestUnivaluePath
 * https://leetcode.cn/problems/longest-univalue-path/
 * 687. 最长同值路径
 * https://leetcode.cn/problems/longest-univalue-path/solution/lu-jing-chang-du-by-oshdyr-5uo8/
 *
 * @author Tobin
 * @since 2022-09-02
 */
public class LongestUnivaluePath {
    public static void main(String[] args) {
        LongestUnivaluePath sol = new LongestUnivaluePath();

        TreeNode tree1 = Trees.fromIntegers(new Integer[]{5, 4, 5, 1, 1, null, 5});
        System.out.println(sol.longestUnivaluePath(tree1));
        TreeNode tree2 = Trees.fromIntegers(new Integer[]{1, 4, 5, 4, 4, null, 5});
        System.out.println(sol.longestUnivaluePath(tree2));
        TreeNode tree3 = Trees.fromIntegers(new Integer[]{});
        System.out.println(sol.longestUnivaluePath(tree3));

        TreeNode tree4 = Trees.fromIntegers(new Integer[]{1, null, 1, 1, 1, 1, 1, 1});
        System.out.println(sol.longestUnivaluePath(tree4));
    }


    public int longestUnivaluePath(TreeNode root) {
        if (root == null) { // BUG 1: ???
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>();
        dfs(root, count);
        return Collections.max(count.values());
    }

    public int dfs(TreeNode root, Map<Integer, Integer> count) {
        int leftStat = 0;
        int rightStat = 0;
        if (root.left != null) {
            leftStat = dfs(root.left, count); // 遍历是必须做的
            if (root.val == root.left.val) {
                leftStat += 1; // 加上连接父节点的边
            } else {
                leftStat = 0;
            }
        }
        if (root.right != null) {
            rightStat = dfs(root.right, count); // 遍历是必须做的
            if (root.val == root.right.val) {
                rightStat += 1; // 加上连接父节点的边
            } else {
                rightStat = 0;
            }
        }

        // BUG 2: 路径长度???
//        System.out.println(root.val + ", " + stat);
        // 计算长度要加起
        int walkStat = leftStat + rightStat;
        // 返回左右子树的最大值
        int stat = Integer.max(leftStat, rightStat);

        // 都要计算最大值
        // walkStat >= stat
        if (count.containsKey(root.val)) {
            if (count.get(root.val) < walkStat) {
                count.put(root.val, walkStat);
            }
        } else {
            count.put(root.val, walkStat);
        }

        // 返回stat
        return stat;
    }

}

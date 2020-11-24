package leetcode;

import utils.TreeNode;
import utils.Trees;

/**
 * CountCompleteTreeNodes
 * https://leetcode-cn.com/problems/count-complete-tree-nodes/
 *
 * @since 2020-11-24
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        CountCompleteTreeNodes sol = new CountCompleteTreeNodes();
        System.out.println(sol.countNodes(Trees.fromIntegers(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 })));
    }

    public int countNodes(TreeNode root) {
        Container container = new Container();
        fetchMaxLevel(root, 1, -1, container);

        int counts = 1;
        for (int i = 1; i < container.totalLevel; i++) {
            counts = counts << 1;
        }
        counts -= 1;
        counts += container.lastLevelCounts;
        return counts;
    }

    public int fetchMaxLevel(TreeNode subRoot, int level, int maxLevel, Container container) {
        if (subRoot == null) {
            return level - 1;
        }
        if (level == maxLevel) {
            // current is max level
            //System.out.println(subRoot.val);
            container.lastLevelCounts++;
            return maxLevel;
        }
        if (container.isStop) {
            return maxLevel;
        }

        int estimateMaxLevel = Math.max(level, maxLevel);
        if (subRoot.left != null) {
            estimateMaxLevel = fetchMaxLevel(subRoot.left, level + 1, estimateMaxLevel, container);
            if (subRoot.right != null) {
                fetchMaxLevel(subRoot.right, level + 1, estimateMaxLevel, container); // ignore right sub-tree level,
                                                                                      // the max must in left
            } else {
                container.isStop = true;
            }
        } else {
            if (level < maxLevel) {
                container.isStop = true;
            }
        }
        if (level == estimateMaxLevel) {
            // keep the first max level
            //System.out.println(subRoot.val);
            container.totalLevel = estimateMaxLevel;
            container.lastLevelCounts++;
        }
        return estimateMaxLevel;
    }

    class Container {
        public int totalLevel = 0;
        public int lastLevelCounts = 0;
        public boolean isStop = false;
    }
}
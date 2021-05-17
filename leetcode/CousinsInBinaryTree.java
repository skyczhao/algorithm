package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * CousinsInBinaryTree
 * https://leetcode-cn.com/problems/cousins-in-binary-tree/
 * 993. 二叉树的堂兄弟节点
 * https://leetcode-cn.com/problems/cousins-in-binary-tree/solution/zui-po-su-de-bao-li-sou-suo-by-oshdyr-q5o5/
 *
 * @since 2021-05-17
 */
public class CousinsInBinaryTree {
    public static void main(String[] args) {
        CousinsInBinaryTree sol = new CousinsInBinaryTree();
        System.out.println(sol.isCousins(Trees.fromIntegers(new Integer[]{1, 2, 3, 4}), 4, 3));
        System.out.println(sol.isCousins(Trees.fromIntegers(new Integer[]{1, 2, 3, null, 4, null, 5}), 4, 5));
        System.out.println(sol.isCousins(Trees.fromIntegers(new Integer[]{1, 2, 3, null, 4, null, 5}), 4, 4));
        System.out.println(sol.isCousins(Trees.fromIntegers(new Integer[]{1, 2, 3, null, 4}), 2, 3));
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        int[] parents = new int[101];
        int[] levels = new int[101];
        levels[x] = -1;
        levels[y] = -2;

        Queue<TreeNode> queue = new LinkedTransferQueue<>();
        queue.add(root);
        Queue<Integer> levelQueue = new LinkedTransferQueue<>();
        levelQueue.add(0);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int currLevel = levelQueue.poll();

            levels[curr.val] = currLevel;
            if (curr.left != null) {
                parents[curr.left.val] = curr.val;
                levels[curr.left.val] = currLevel + 1;
                queue.add(curr.left);
                levelQueue.add(currLevel + 1);
            }
            if (curr.right != null) {
                parents[curr.right.val] = curr.val;
                levels[curr.right.val] = currLevel + 1;
                queue.add(curr.right);
                levelQueue.add(currLevel + 1);
            }
        }

        return parents[x] != parents[y] && levels[x] == levels[y];
    }
}

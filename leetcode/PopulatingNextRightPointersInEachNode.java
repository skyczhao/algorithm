package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * PopulatingNextRightPointersInEachNode
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * @since 2020-10-15
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        TreeNode head = Trees.fromIntegers(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        PopulatingNextRightPointersInEachNode sol = new PopulatingNextRightPointersInEachNode();
        TreeNode res = sol.connect(head);
        System.out.println(res.val);
    }

    public TreeNode connect(TreeNode root) {
        if (root == null) { // 小坑一下
            return root;
        }

        Queue<TreeNode> visits = new LinkedTransferQueue<>();
        Queue<Integer> levels = new LinkedTransferQueue<>();

        visits.add(root);
        levels.add(0);
        while (!visits.isEmpty() && !levels.isEmpty()) {
            TreeNode curr = visits.poll();
            int currLevel = levels.poll();

            if (visits.isEmpty() || levels.isEmpty()) {
                curr.next = null;
            } else {
                if (levels.peek().equals(currLevel)) {
                    curr.next = visits.peek();
                } else {
                    curr.next = null;
                }
            }
            if (curr.left != null) {
                visits.add(curr.left);
                levels.add(currLevel + 1);
            }
            if (curr.right != null) {
                visits.add(curr.right);
                levels.add(currLevel + 1);
            }
        }
        return root;
    }
}

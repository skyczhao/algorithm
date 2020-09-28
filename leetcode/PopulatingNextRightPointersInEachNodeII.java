package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * PopulatingNextRightPointersInEachNodeII
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * @since 2020-09-28
 */
public class PopulatingNextRightPointersInEachNodeII {

    public static void main(String[] args) {
        Integer[] values = {1, 2, 3, 4, 5, null, 7};
        TreeNode head = Trees.fromIntegers(values);

        PopulatingNextRightPointersInEachNodeII sol = new PopulatingNextRightPointersInEachNodeII();
        TreeNode res = sol.connect(head);
        System.out.println(res.val);
    }

    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> nodes = new LinkedTransferQueue<>();
        Queue<Integer> levels = new LinkedTransferQueue<>();

        nodes.add(root);
        levels.add(0);
        TreeNode last = null;
        int lastLevel = -1;
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            int currLevel = levels.poll();

            if (last != null && lastLevel == currLevel) {
                last.next = curr;
            }

            if (curr.left != null) {
                nodes.add(curr.left);
                levels.add(currLevel + 1);
            }
            if (curr.right != null) {
                nodes.add(curr.right);
                levels.add(currLevel + 1);
            }
            last = curr;
            lastLevel = currLevel;
        }
        return root;
    }
}

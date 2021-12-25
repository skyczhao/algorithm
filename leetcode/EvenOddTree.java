package leetcode;

import utils.TreeNode;
import utils.Trees;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * EvenOddTree
 * https://leetcode-cn.com/problems/even-odd-tree/
 * 1609. 奇偶树
 * https://leetcode-cn.com/problems/even-odd-tree/solution/yan-du-you-xian-bian-li-by-oshdyr-mbsv/
 *
 * @author tobin
 * @since 2021-12-25
 */
public class EvenOddTree {
    public static void main(String[] args) {
        EvenOddTree sol = new EvenOddTree();

        TreeNode root = Trees.fromIntegers(new Integer[]{
                1,
                10, 4,
                3, null, 7, 9,
                12, 8, 6, null, null, 2});
        System.out.println(sol.isEvenOddTree(root));
    }

    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> evenFloor = new LinkedTransferQueue<>();
        Queue<TreeNode> oddFloor = new LinkedTransferQueue<>();

        evenFloor.add(root);
        int currFloor = 0;
        while (!evenFloor.isEmpty() || !oddFloor.isEmpty()) {
            if (currFloor % 2 == 0) { // 偶数层
                TreeNode last = evenFloor.poll();
                if (last.val % 2 != 1) {
                    // 非奇数即错误
                    return false;
                }
                while (!evenFloor.isEmpty()) {
                    // 偶数层的下层即奇数层
                    if (last.left != null) {
                        oddFloor.add(last.left);
                    }
                    if (last.right != null) {
                        oddFloor.add(last.right);
                    }

                    TreeNode curr = evenFloor.poll();
                    if (curr.val % 2 != 1) {
                        // 非奇数即错误
                        return false;
                    }
                    if (curr.val <= last.val) {
                        // 非严格递增即错误
                        return false;
                    }
                    last = curr;
                }
                // 偶数层的下层即奇数层
                if (last.left != null) {
                    oddFloor.add(last.left);
                }
                if (last.right != null) {
                    oddFloor.add(last.right);
                }

            } else { // 奇数层
                TreeNode last = oddFloor.poll();
                if (last.val % 2 != 0) {
                    // 非偶数即错误
                    return false;
                }
                while (!oddFloor.isEmpty()) {
                    // 奇数层的下层即偶数层
                    if (last.left != null) {
                        evenFloor.add(last.left);
                    }
                    if (last.right != null) {
                        evenFloor.add(last.right);
                    }

                    TreeNode curr = oddFloor.poll();
                    if (curr.val % 2 != 0) {
                        // 非偶数即错误
                        return false;
                    }
                    if (curr.val >= last.val) {
                        // 非严格递减即错误
                        return false;
                    }
                    last = curr;
                }
                // 奇数层的下层即偶数层
                if (last.left != null) {
                    evenFloor.add(last.left);
                }
                if (last.right != null) {
                    evenFloor.add(last.right);
                }
            }

            // 累计层数
            currFloor++;
        }
        return true;
    }
}

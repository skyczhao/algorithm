package utils;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;


/**
 * Trees
 *
 * @since 2020-09-27
 */
public class Trees {

    public static TreeNode fromIntegers(Integer[] values) {
        if (values == null || values.length < 1) {
            return null;
        }

        TreeNode head = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedTransferQueue<>();
        queue.add(head);
        for (int i = 1; i < values.length; i++) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                throw new IllegalStateException();
            }

            if (values[i] != null) {
                curr.left = new TreeNode(values[i]);
                queue.add(curr.left);
            } else {
                curr.left = null;
            }

            i++;
            if (i < values.length && values[i] != null) {
                curr.right = new TreeNode(values[i]);
                queue.add(curr.right);
            } else {
                curr.right = null;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Integer[] test = {0,
                null, 0,
                null, 0,
                null, 0,
                null, 0,
                0, 0,
                null, null, 0, 0};

        TreeNode node = Trees.fromIntegers(test);
        System.out.println(node.val);

        Integer[] test2 = {6,
                2, 8,
                0, 4, 7, 9,
                null, null, 3, 5};
        TreeNode node2 = Trees.fromIntegers(test2);
        System.out.println(node2.val);
    }
}

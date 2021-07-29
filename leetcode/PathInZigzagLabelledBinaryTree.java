package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * PathInZigzagLabelledBinaryTree
 * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/
 * 1104. 二叉树寻路
 * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/solution/man-su-jie-ti-li-jie-si-lu-by-oshdyr-7ix0/
 *
 * @author tobin
 * @since 2021-07-29
 */
public class PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        PathInZigzagLabelledBinaryTree sol = new PathInZigzagLabelledBinaryTree();
        System.out.println(sol.pathInZigZagTree(26));
        System.out.println(sol.pathInZigZagTree(14));
        System.out.println(sol.pathInZigZagTree(1));
        System.out.println(sol.pathInZigZagTree(1000000));
    }

    private List<Integer> powers = new ArrayList<>();

    public List<Integer> pathInZigZagTree(int label) {
        int base = 2;
        for (int i = 0; i < 20; i++) {
            powers.add(base);
            base = base << 1;
        }

        int pos = labelRealPos(label);
        Stack<Integer> stack = new Stack<>();
        while (pos > 1) {
            stack.push(posRefLabel(pos));
            pos = pos >> 1;
        }

        List<Integer> result = new LinkedList<>();
        result.add(1);
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private int labelRealPos(int label) {
        for (int i = 0; i < powers.size(); i++) {
            if (label < powers.get(i)) {
                if (i % 2 == 0) {
                    return label;
                } else {
                    return powers.get(i) - label + powers.get(i - 1) - 1;
                }
            }
        }

        return -1;
    }

    private int posRefLabel(int pos) {
        for (int i = 0; i < powers.size(); i++) {
            if (pos < powers.get(i)) {
                if (i % 2 == 0) {
                    return pos;
                } else {
                    return powers.get(i) - (pos - powers.get(i - 1) + 1);
                }
            }
        }
        return 0;
    }
}

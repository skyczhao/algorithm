package leetcode;


import java.util.LinkedList;
import java.util.List;

/**
 * ArithmeticSlices
 * https://leetcode-cn.com/problems/arithmetic-slices/
 * 413. 等差数列划分
 * https://leetcode-cn.com/problems/arithmetic-slices/solution/sou-suo-kan-si-jian-dan-dan-shi-yao-kao-me9ax/
 * 这个月题目平均错2次, 3次才能pass. 可能技能下降, 或者没有考虑周全.
 *
 * @author tobin
 * @since 2021-08-12
 */
public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices sol = new ArithmeticSlices();
        System.out.println(sol.numberOfArithmeticSlices(new int[]{1, 3, 5, 7, 9}));
        System.out.println(sol.numberOfArithmeticSlices(new int[]{1, 3, 5, 7}));
        System.out.println(sol.numberOfArithmeticSlices(new int[]{2, 1, 3, 4, 2, 3}));
        System.out.println(sol.numberOfArithmeticSlices(new int[]{1, 2, 3, 5, 7}));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        List<Integer> avilables = new LinkedList<>();
//        boolean[] flags = new boolean[nums.length]; // bug2: 可以重复取
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];

            int j = i + 1;
            if (j >= nums.length) {
                continue;
            }
            int second = nums[j];

            int dist = second - first;
            int next = second + dist;
            int length = 2;
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] == next) {
                    length++;
                    next = next + dist;
                } else {
                    break; // bug1: 题目要求连续
                }
            }

            if (length > 2) {
                avilables.add(length);
            }
        }

        int total = 0;
        for (int length : avilables) {
            total += (length - 2);

        }
        return total;
    }

    public int numberOfArithmeticSlices_2(int[] nums) {
        List<Integer> avilables = new LinkedList<>();
        boolean[] flags = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (flags[i]) {
                continue;
            }

            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (flags[j]) {
                    break;
                }
                int second = nums[j];

                int dist = second - first;
                int next = second + dist;
                int length = 2;
                for (int k = j + 1; k < nums.length; k++) {
                    if (flags[k]) {
                        break;
                    }

                    if (nums[k] == next) {
                        flags[k] = true;
                        length++;
                        next = next + dist;
                    } else {
                        break;
                    }
                }

                if (length > 2) {
                    flags[i] = true;
                    flags[j] = true;
                    avilables.add(length);
                } else {
                    break;
                }
            }
        }

        int total = 0;
        for (int length : avilables) {
            for (int range = 3; range <= length; range++) {
                total += (length - range + 1);
            }
        }
        return total;
    }
}

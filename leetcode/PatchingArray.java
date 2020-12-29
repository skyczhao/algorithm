package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * PatchingArray
 * 330. 按要求补齐数组, https://leetcode-cn.com/problems/patching-array/
 * 题解: https://leetcode-cn.com/problems/patching-array/solution/ji-yu-shu-xue-de-tan-xin-by-oshdyr-i219/
 *
 * @since 2020-12-29
 */
public class PatchingArray {

    // 给定一个已排序的正整数数组 nums，和一个正整数 n 。
    public static void main(String[] args) {
        PatchingArray sol = new PatchingArray();

        System.out.println(sol.minPatches(new int[]{1, 3}, 6));
        System.out.println("---");
        System.out.println(sol.minPatches(new int[]{1, 5, 10}, 20));
        System.out.println("---");
        System.out.println(sol.minPatches(new int[]{1, 2, 31, 33}, 2147483647));
        System.out.println("---");
        System.out.println(sol.minPatches(new int[]{1, 5}, 20));
        System.out.println("---");
    }

    public int minPatches(int[] nums, int n) {
        long x = 1; // int可能越界
        int index = 0;
        int counts = 0;
        while (x <= n) {
            // key: 任何时候都满足区间 [1, x-1] 内的所有数字都被覆盖
            
            if (index < nums.length && nums[index] <= x) {
                // key: 对于正整数 x，同时满足 x <= y，如果区间 [1, y-1] 内的所有数字都已经被覆盖，且 x 在数组中，则区间 [1, x+y-1] 内的所有数字也都被覆盖
                x += nums[index]; // ?? 为什么不是2*nums[index]
//                x = 2 * nums[index];
                index++;
            } else {
                counts++;
                // key: 对于正整数 x，如果区间 [1, x-1] 内的所有数字都已经被覆盖，且 x 在数组中，则区间 [1, 2x-1] 内的所有数字也都被覆盖
                x *= 2;
            }
        }
        return counts;
    }
}

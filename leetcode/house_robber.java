package leetcode;

/**
 * house_robber
 * https://leetcode-cn.com/problems/house-robber/
 * Solution: DP, 不是简单的奇偶位置相加就好了
 *
 * @since 2020-05-29
 */
public class house_robber {
    public static void main(String[] args) {
        house_robber_Solution s = new house_robber_Solution();

        int[] nums = {2, 1, 1, 2};
        System.out.println(s.rob(nums));

        int[] nums2 = {1, 2, 3, 1};
        System.out.println(s.rob(nums2));
    }
}


class house_robber_Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length < 2) {
            return nums[0];
        }

        int[] maximum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            maximum[i] = 0;
            if (i - 2 >= 0) {
                for (int j = 0; j < i - 1; j++) { // BUG 1: 边界没有定好
                    if (maximum[j] + nums[i] > maximum[i]) {
                        maximum[i] = maximum[j] + nums[i];
                    }
                }
            } else {
                maximum[i] = nums[i];
            }
        }

        // BUG 2: 没注意好最大值可能在倒数第二位
        return maximum[nums.length - 1] > maximum[nums.length - 2] ? maximum[nums.length - 1] : maximum[nums.length - 2];
    }
}
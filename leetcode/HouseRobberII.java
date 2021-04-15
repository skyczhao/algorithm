package leetcode;

/**
 * HouseRobberII
 * https://leetcode-cn.com/problems/house-robber-ii/
 * 213. 打家劫舍 II
 * https://leetcode-cn.com/problems/house-robber-ii/solution/liang-ci-dong-tai-gui-hua-by-oshdyr-q0tr/
 *
 * @since 2021-04-15
 */
public class HouseRobberII {
    public static void main(String[] args) {
        HouseRobberII sol = new HouseRobberII();
        System.out.println(sol.rob(new int[]{2, 3, 2}));
        System.out.println(sol.rob(new int[]{1, 2, 3, 8}));
        System.out.println(sol.rob(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
        System.out.println(sol.rob(new int[]{0}));
        System.out.println(sol.rob(new int[]{200, 3, 140, 20, 10}));
        System.out.println(sol.rob(new int[]{1}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // 0 ~ size - 1
        int[] maxFromZero = new int[nums.length];
        int maxRobZero = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int currRob = nums[i];
            int lastRob = 0;
            if (i - 1 >= 0) {
                lastRob = maxFromZero[i - 1];
            }
            if (i - 2 >= 0) {
                currRob += maxFromZero[i - 2];
            }
            if (lastRob > currRob) {
                currRob = lastRob;
            }
            maxFromZero[i] = currRob;
            if (currRob > maxRobZero) {
                maxRobZero = currRob;
            }
        }

        // 1 ~ size
        int[] maxFromOne = new int[nums.length];
        int maxRobOne = 0;
        for (int i = 1; i < nums.length; i++) {
            int currRob = nums[i];
            int lastRob = maxFromOne[i - 1];
            if (i - 2 >= 0) {
                currRob += maxFromOne[i - 2];
            }
            if (lastRob > currRob) {
                currRob = lastRob;
            }
            maxFromOne[i] = currRob;
            if (currRob > maxRobOne) {
                maxRobOne = currRob;
            }
        }

        if (maxRobOne > maxRobZero) {
            return maxRobOne;
        }
        return maxRobZero;
    }


    public int rob_fail(int[] nums) {
        int[] maxRob = new int[nums.length];
        // {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
        boolean[] containFirst = new boolean[nums.length]; // NOT GOOD, STILL BUG

        for (int i = 0; i < nums.length; i++) {
            int searchEnd = i - 1;
            int searchStart = i - 3;
            if (searchStart < 0) {
                searchStart = 0;
            }
            boolean skipFirst = false;
            if (i + 1 == nums.length) {
                searchStart = 1;
                skipFirst = true;
            }

            int maxLast = 0;
            boolean maxContainFist = false;
            for (int j = searchStart; j < searchEnd; j++) {
                if (skipFirst && containFirst[j]) {
                    continue;
                }
                if (maxRob[j] > maxLast) {
                    maxLast = maxRob[j];
                    maxContainFist = containFirst[j];
                }
            }

            maxRob[i] = nums[i] + maxLast;
            containFirst[i] = maxContainFist;
            containFirst[0] = true;
        }

        int max = 0;
        for (int i = 0; i < maxRob.length; i++) {
            if (maxRob[i] > max) {
                max = maxRob[i];
            }
        }
        return max;
    }
}

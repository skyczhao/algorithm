package leetcode;

import java.util.TreeSet;

/**
 * ContainsDuplicateIII
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 * 220. 存在重复元素 III
 * https://leetcode-cn.com/problems/contains-duplicate-iii/solution/hua-dong-chuang-kou-dong-tai-you-xu-ji-h-nhm3/
 * 注意掌握TreeSet数据结构的特性。类似不熟悉的数据结构还有并查集等。
 * 同时提醒注意掌握通过数据结构解题，而不仅仅只关注算法。完整的程序=算法+数据结构，同等重要。
 * 
 * @since 2021-04-17
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        ContainsDuplicateIII sol = new ContainsDuplicateIII();
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{}, 0, 0));
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{-2147483648, 2147483647}, 1, 1));
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 1));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }

        TreeSet<Long> range = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!range.isEmpty()) {
                Long ceil = range.ceiling(nums[i] * 1L - t);
                if (ceil != null && ceil <= nums[i] * 1L + t) {
                    return true;
                }
            }
            if (i - k >= 0) {
                range.remove((long) nums[i - k]);
            }
            range.add((long) nums[i]);
        }
        return false;
    }

    // TLE, 40000 * 10000
    public boolean containsNearbyAlmostDuplicate_tle(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) { // BUG: []
            return false;
        }
        if (k == 0) { // BUG: 要是在ACM过程, 刷错几次或许就没有耐心和信息完成这题了
            return false;
        }

        long[] neighborDist = new long[nums.length];
        long[] rollingKDist = new long[nums.length];

        neighborDist[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            neighborDist[i] = (long) nums[i] - (long) nums[i - 1]; // BUG: 虽然结果是long, 但中间过程还是可能越界
            if (Math.abs(neighborDist[i]) <= t) {
                return true;
            }
            rollingKDist[i] = neighborDist[i];
        }

        for (int dist = 2; dist <= k; dist++) {
            for (int i = dist; i < nums.length; i++) {
                rollingKDist[i] = rollingKDist[i] + neighborDist[i - dist + 1];
                if (Math.abs(rollingKDist[i]) <= t) {
                    return true;
                }
            }
        }

        return false;
    }
}

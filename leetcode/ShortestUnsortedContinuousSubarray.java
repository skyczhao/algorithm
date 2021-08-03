package leetcode;

/**
 * ShortestUnsortedContinuousSubarray
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 * 581. 最短无序连续子数组
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/onfang-fa-by-oshdyr-u97r/
 *
 * @author tobin
 * @since 2021-08-03
 */
public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        ShortestUnsortedContinuousSubarray sol = new ShortestUnsortedContinuousSubarray();
        System.out.println(sol.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(sol.findUnsortedSubarray(new int[]{1, 2, 3, 4}));
        System.out.println(sol.findUnsortedSubarray(new int[]{1}));
        System.out.println(sol.findUnsortedSubarray(new int[]{4, 3, 2, 1}));
        System.out.println(sol.findUnsortedSubarray(new int[]{1, 3, 2, 3, 3}));
    }

    public int findUnsortedSubarray(int[] nums) {

        int startIdx = 0;
        // move forward
        while (startIdx + 1 < nums.length) {
            if (nums[startIdx + 1] >= nums[startIdx]) { // BUG: =
                startIdx++;
            } else {
                break;
            }
        }
        // move backward
        int next = startIdx + 1;
        while (next < nums.length && startIdx >= 0) {
            if (nums[startIdx] > nums[next]) {
                startIdx--;
            } else {
                next++;
            }
        }

        int endIdx = nums.length - 1;
        // move backward
        while (endIdx - 1 >= 0) {
            if (nums[endIdx - 1] <= nums[endIdx]) { // BUG: =
                endIdx--;
            } else {
                break;
            }
        }
        // move forward
        int back = endIdx - 1;
        while (back >= 0 && endIdx < nums.length) {
            if (nums[endIdx] < nums[back]) {
                endIdx++;
            } else {
                back--;
            }
        }

        if (endIdx - 1 <= startIdx + 1) {
            return 0;
        }
        return endIdx - 1 - (startIdx + 1) + 1;
    }
}

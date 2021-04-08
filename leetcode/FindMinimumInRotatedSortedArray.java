package leetcode;

/**
 * FindMinimumInRotatedSortedArray
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * 153. 寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-sou-suo-fen-zhi-by-oshdyr-54gm/
 *
 * @author tobin
 * @since 2021-04-08
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray sol = new FindMinimumInRotatedSortedArray();

        System.out.println(sol.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(sol.findMin(new int[]{0}));
        System.out.println(sol.findMin(new int[]{0, 1}));
        System.out.println(sol.findMin(new int[]{2, 1}));
    }

    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }

        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (end - start == 1) {
                if (nums[start] < nums[end]) {
                    return nums[start];
                } else {
                    return nums[end];
                }
            }
            int mid = (start + end) / 2;
            if (nums[mid] > nums[0]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }
}

package leetcode;

/**
 * find_minimum_in_rotated_sorted_array
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * 
 * 主: 二分搜索
 * 辅: 模拟(脑海剪枝...)
 *
 * @since 2020-07-22
 */
public class find_minimum_in_rotated_sorted_array {
    public static void main(String[] args) {
//        int[] arr = {4, 5, 6, 7, 0, 1, 2};
//        int[] arr = {3, 4, 5, 1, 2};
//        int[] arr = {2, 1};
        int[] arr = {3, 3, 1, 3};
//        int[] arr = {1, 1};

        System.out.println(findMin(arr));
    }

    static public int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;
            if (nums[start] > nums[mid]) {
                // in left
                start += 1;
                end = mid;
            } else if (nums[mid] > nums[end]) {
                // in right
                start = mid + 1;
            } else if (nums[start] < nums[mid] || nums[mid] < nums[end]) {
                // in left
                end = mid;
            } else if (start == mid) {
                if (nums[end] < nums[mid]) {
                    return nums[end];
                } else {
                    return nums[mid];
                }
            } else {
                int tmp = mid - 1;
                while (tmp > start) {
                    if (nums[tmp] == nums[mid]) {
                        tmp--;
                    } else if (nums[tmp] > nums[mid]) {
                        return nums[mid];
                    } else {
                        break;
                    }
                }

                if (nums[tmp] < nums[mid]) {
                    // in left
                    end = tmp;
                } else {
                    // in right
                    start = mid;
                }
            }
        }

        if (nums[end] < nums[start]) {
            return nums[end];
        } else {
            return nums[start];
        }
    }
}

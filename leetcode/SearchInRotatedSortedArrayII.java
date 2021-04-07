package leetcode;

/**
 * SearchInRotatedSortedArrayII
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * 81. 搜索旋转排序数组 II
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/qu-zhong-hou-er-fen-sou-suo-by-oshdyr-zr7b/
 *
 * @author tobin
 * @since 2021-04-07
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {

        SearchInRotatedSortedArrayII sol = new SearchInRotatedSortedArrayII();

        System.out.println(sol.search(new int[]{1, 0, 1, 1, 1}, 0));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 1));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 2));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 4));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 5));
        System.out.println(sol.search(new int[]{2, 5, 6, 6, 6, 6, 0, 0, 1, 2}, 6));
        System.out.println(sol.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 7));
        System.out.println(sol.search(new int[]{1}, 1));
        System.out.println(sol.search(new int[]{1, 2, 3, 0, 1, 1}, 3));
    }

    public boolean search(int[] nums, int target) {
        int lastValue = nums[0] - 1;
        int gap = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == lastValue) {
                gap++;
            }
            lastValue = nums[i];
            nums[i - gap] = nums[i];
        }
        int realSize = nums.length - gap;
        if (realSize > 1 && nums[realSize - 1] == nums[0]) { // BUG: [1]
            realSize--;
        }

        int begin = 0;
        int end = realSize - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (target == nums[mid]) {
                return true;
            }

            if (end - begin <= 1) {
                if (target == nums[begin] || target == nums[end]) {
                    return true;
                }
                return false;
            }
            if (nums[0] < nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[realSize - 1]) {
                    begin = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

}

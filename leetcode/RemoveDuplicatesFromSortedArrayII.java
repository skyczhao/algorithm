package leetcode;

/**
 * RemoveDuplicatesFromSortedArrayII
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * 80. 删除有序数组中的重复项 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/yuan-di-fu-gai-by-oshdyr-i7o1/
 *
 * @since 2021-04-06
 */
public class RemoveDuplicatesFromSortedArrayII {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArrayII sol = new RemoveDuplicatesFromSortedArrayII();

        int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int res = sol.removeDuplicates(arr);
        for (int i = 0; i < res; i++) {
            System.out.println(arr[i] + ", ");
        }
        System.out.println();

        int[] arr2 = {1, 1, 1, 2, 2, 3};
        int res2 = sol.removeDuplicates(arr2);
        for (int i = 0; i < res2; i++) {
            System.out.println(arr2[i] + ", ");
        }
        System.out.println();
    }

    public int removeDuplicates(int[] nums) {

        int gap = 0;
        int last = nums[0] - 1;
        int lastTimes = 0;
        for (int next = 0; next < nums.length; next++) {
            if (nums[next] == last) {
                lastTimes++;
                if (lastTimes > 2) {
                    gap++;
                }
            } else {
                last = nums[next];
                lastTimes = 1;
            }

            if (gap > 0) {
                nums[next - gap] = nums[next];
            }
        }
        return nums.length - gap;
    }
}

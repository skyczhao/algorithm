package leetcode;

/**
 * NextPermutation
 * https://leetcode-cn.com/problems/next-permutation/
 * 31. 下一个排列
 * 关键: 结尾倒序子序列
 *
 * @since 2020-11-10
 */
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation sol = new NextPermutation();

        int[] a = new int[]{1, 2, 3};
        sol.nextPermutation(a);
        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int[] b = new int[]{3, 2, 1};
        sol.nextPermutation(b);
        for (int i : b) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int[] c = new int[]{1, 2, 4, 3, 5};
        sol.nextPermutation(c);
        for (int i : c) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int[] d = new int[]{1, 1, 5};
        sol.nextPermutation(d);
        for (int i : d) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public void nextPermutation(int[] nums) {
        int inv_first_idx = nums.length - 1;
        // get inv first idx
        while (inv_first_idx > 0) {
            int next = inv_first_idx - 1;
            if (nums[next] < nums[inv_first_idx]) {
                break;
            }
            inv_first_idx = next;
        }

        // swap
        if (inv_first_idx > 0) {
            int swap_idx = inv_first_idx - 1;
            int to_swap_idx = nums.length - 1;
            while (to_swap_idx > swap_idx) {
                if (nums[to_swap_idx] > nums[swap_idx]) {
                    break;
                }
                to_swap_idx--;
            }

            int tmp = nums[swap_idx];
            nums[swap_idx] = nums[to_swap_idx];
            nums[to_swap_idx] = tmp;
        }

        // resort inv sub seq
        // just inv seq
        int positive_idx = inv_first_idx;
        int negative_idx = nums.length - 1;
        int mid_idx = (positive_idx + negative_idx) / 2;
        while (positive_idx <= mid_idx) {
            int tmp = nums[positive_idx];
            nums[positive_idx] = nums[negative_idx];
            nums[negative_idx] = tmp;

            positive_idx++;
            negative_idx--;
        }
    }
}

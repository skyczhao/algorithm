package leetcode;

/**
 * Pattern132
 * https://leetcode-cn.com/problems/132-pattern/
 * 456. 132 模式
 * https://leetcode-cn.com/problems/132-pattern/solution/mei-ju-by-oshdyr-07go/
 *
 * @author tobin
 * @since 2021-03-24
 */
public class Pattern132 {
    public static void main(String[] args) {
        Pattern132 sol = new Pattern132();
        System.out.println(sol.find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(sol.find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(sol.find132pattern(new int[]{-1, 3, 2, 0}));
        System.out.println(sol.find132pattern(new int[]{1, 0, 1, -4, -3}));
    }

    public boolean find132pattern(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            for (int j = i - 1; j >= 0; j--) {
                if (flag) {
                    if (nums[j] > nums[i]) {
                        flag = false;
                    }
                } else if (nums[j] < nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 理解错题意, 3必须是第二大的
     *
     * @param nums
     * @return
     */
    public boolean find132pattern_err(int[] nums) {
        int[] incrLength = new int[nums.length];
        incrLength[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int maxIncrLength = 0;
                if (nums[i] > nums[j]) {
                    int tmpIncrLength = incrLength[j] + 1;
                    if (tmpIncrLength > maxIncrLength) {
                        maxIncrLength = tmpIncrLength;
                    }
                } else if (nums[i] < nums[j]) {
                    if (incrLength[j] >= 2) {
                        return true;
                    }
                    if (1 > maxIncrLength) {
                        maxIncrLength = 1;
                    }
                } else {
                    if (incrLength[j] > maxIncrLength) {
                        maxIncrLength = incrLength[j];
                    }
                }
                incrLength[i] = maxIncrLength;
            }
        }
        return false;
    }
}

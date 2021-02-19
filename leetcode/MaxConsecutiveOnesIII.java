package leetcode;

/**
 * MaxConsecutiveOnesIII
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii
 * 1004. 最大连续1的个数 III
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/solution/hua-dong-chuang-kou-by-oshdyr-cwlc/
 *
 * @author tobin
 * @since 2021-02-19
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {

        MaxConsecutiveOnesIII sol = new MaxConsecutiveOnesIII();

        System.out.println(sol.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
        System.out.println(sol.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    public int longestOnes(int[] A, int K) {
        int longest = 0;
        int left = K;
        int startIdx = 0;
        for (int endIdx = 0; endIdx < A.length; endIdx++) {
            if (A[endIdx] == 0) {
                if (left > 0) {
                    left--;
                } else {
                    if (A[startIdx] > 0) {
                        endIdx--;
                    }
                    startIdx++;
                }
            }
            int curr = endIdx - startIdx + 1;
            if (curr > longest) {
                longest = curr;
            }
        }
        return longest;
    }
}

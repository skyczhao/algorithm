package leetcode;

/**
 * MinimumNumberKConsecutiveBitFlips
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/
 * 995. K 连续位的最小翻转次数
 * https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/hua-dong-chuang-kou-tan-xin-by-oshdyr-cyf4/
 * 题解提供的解释:
 * * 由于对同一个子数组执行两次翻转操作不会改变该子数组，所以对每个长度为 KK 的子数组，应至多执行一次翻转操作。
 * * 对于若干个 KK 位翻转操作，改变先后顺序并不影响最终翻转的结果。
 * * 不妨从 A[0] 开始考虑，若 A[0]=0，则必定要翻转从位置 0 开始的子数组；若 A[0]=1，则不翻转从位置 0 开始的子数组。
 *
 * @since 2021-02-18
 */
public class MinimumNumberKConsecutiveBitFlips {

    public int minKBitFlips(int[] A, int K) {

        int times = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (i + K <= A.length) {
                    for (int j = i; j < i + K; j++) {
                        A[j] = 1 - A[j];
                    }
                    times++;
                } else {
                    return -1;
                }
            }
        }

        return times;
    }

    public static void main(String[] args) {
        MinimumNumberKConsecutiveBitFlips sol = new MinimumNumberKConsecutiveBitFlips();

        System.out.println(sol.minKBitFlips(new int[]{0, 1, 0}, 1));
        System.out.println(sol.minKBitFlips(new int[]{1, 1, 0}, 2));
        System.out.println(sol.minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }
}

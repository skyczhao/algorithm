package leetcode;

/**
 * MonotoneIncreasingDigits
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 * 738. 单调递增的数字
 *
 * @author tobin
 * @since 2020-12-15
 */
public class MonotoneIncreasingDigits {
    public static void main(String[] args) {
        MonotoneIncreasingDigits sol = new MonotoneIncreasingDigits();
        System.out.println(sol.monotoneIncreasingDigits(10));
        System.out.println(sol.monotoneIncreasingDigits(1234));
        System.out.println(sol.monotoneIncreasingDigits(4321));
        System.out.println(sol.monotoneIncreasingDigits(120));
        System.out.println(sol.monotoneIncreasingDigits(332));
    }

    public int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            return N;
        }

        int[] digits = new int[10];
        int tmp = N;
        int idx = 0;
        while (tmp > 0) {
            digits[idx] = tmp % 10;
            tmp /= 10;
            idx++;
        }
        int size = idx;

        boolean lostRelate = false;
        for (idx = size - 1; idx >= 0; idx--) {
            if (lostRelate) {
                digits[idx] = 9;
            } else if (idx + 1 >= size) {
                // first num
            } else {
                // borrow from higher digits
                boolean mustBorrow = false;
                while (idx + 1 < size) {
                    // 当前位置不小于上一个位置, 取值即可
                    if (!mustBorrow && digits[idx] >= digits[idx + 1]) {
                        // available, use origin num
                        break;
                    }
                    mustBorrow = false;

                    // 当前位置小于上一个位置, 需要向上一个位置借1
                    digits[idx] = 9;
                    if (digits[idx + 1] > 0) {
                        // 上一个位置-1
                        digits[idx + 1]--;
                    } else {
                        // 0, 上一个位置需要再向上两个位置借1
                        mustBorrow = true;
                    }

                    // 后退下标, 迭代判断
                    idx++;
                    // once borrow, left are 9s
                    lostRelate = true;
                }
            }
        }

        int result = 0;
        for (idx = size - 1; idx >= 0; idx--) {
            result = result * 10 + digits[idx];
        }
        return result;

    }

}

package leetcode;

import java.util.Stack;

/**
 * NumberOfDigitOne
 * https://leetcode-cn.com/problems/number-of-digit-one/
 * 233. 数字 1 的个数
 * https://leetcode-cn.com/problems/number-of-digit-one/solution/lei-ji-ji-suan-ye-you-dong-tai-gui-hua-d-wd71/
 * 转移函数比较隐秘, 而且容易出错
 *
 * @author tobin
 * @since 2021-08-13
 */
public class NumberOfDigitOne {

    public static void main(String[] args) {

        NumberOfDigitOne sol = new NumberOfDigitOne();
//        System.out.println(sol.countDigitOne(13));
        System.out.println(sol.countDigitOne(10));
        System.out.println(sol.countDigitOne(13));
        System.out.println(sol.countDigitOne(100));
        System.out.println(sol.countDigitOne(113));
        System.out.println(sol.countDigitOne(1000));
        System.out.println(sol.countDigitOne(1113));
        System.out.println(sol.countDigitOne(10000));
    }

    public int countDigitOne(int n) {
        int[] baseCounts = new int[9];
        int base = 1;
        for (int i = 0; i < 9; i++) {
            if (i >= 1) {
                baseCounts[i] = base + 10 * baseCounts[i - 1];
            } else {
                baseCounts[i] = base;
            }
            base *= 10;
        }

        int totalOnes = 0;

        int tmp = n;
        int idx = 0;
        int tail = 0;
        int accBase = 1;
        while (tmp > 0) {
            int value = tmp % 10;

            int currOnes = 0;
            // 每个阶的1
            if (idx > 0) {
                currOnes = value * baseCounts[idx - 1];
            }
            // 前缀1
            if (value > 1) {
                currOnes += accBase;
            }

            // 尾数处理
            if (value == 1) {
                currOnes += (1 + tail);
            }
            currOnes += totalOnes;

            // 即结果
            totalOnes = currOnes;

            tmp /= 10;
            idx++;
            tail = value * accBase + tail;
            accBase *= 10;
        }

        return totalOnes;
    }
}

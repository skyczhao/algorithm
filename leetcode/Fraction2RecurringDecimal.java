package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Fraction2RecurringDecimal
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 * 166. 分数到小数
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/suan-zhu-mo-ni-ji-suan-by-oshdyr-r87r/
 *
 * @author tobin
 * @since 2021-10-03
 */
public class Fraction2RecurringDecimal {
    public static void main(String[] args) {
        Fraction2RecurringDecimal sol = new Fraction2RecurringDecimal();
//        System.out.println(sol.fractionToDecimal(1, 2));
//        System.out.println(sol.fractionToDecimal(1, 4));
        System.out.println(sol.fractionToDecimal(2, 3));
        System.out.println(sol.fractionToDecimal(20, 3));
        System.out.println(sol.fractionToDecimal(2000, 3));
        System.out.println(sol.fractionToDecimal(1, 7));
        System.out.println(sol.fractionToDecimal(1, 70));
        System.out.println(sol.fractionToDecimal(1, 33));
        System.out.println(sol.fractionToDecimal(1, 20));
        System.out.println(sol.fractionToDecimal(-1, 20));
        System.out.println(sol.fractionToDecimal(-1, -20));
        System.out.println(sol.fractionToDecimal(0, -20));
        System.out.println(sol.fractionToDecimal(-1, -2147483648));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) { // BUG 1
            return "0";
        }

        int flag = 1;
        long y = numerator;
        if (numerator < 0) {
            y = -1 * y; // -2^31? // BUG 2
            flag = -1 * flag;
        }
        long x = denominator;
        if (denominator < 0) {
            x = -1 * x; // BUG 2
            flag = -1 * flag;
        }

        Map<Long, Integer> firstPos = new HashMap<>();

        StringBuilder resultSB = new StringBuilder();
        boolean isDigit = false;
        boolean isPatched = false;
        while (y > 0) {
            if (y >= x) {
                long v = y / x;
                y = y % x;
                resultSB.append(v);
                isPatched = false;
            } else {
                if (!isDigit) {
                    if (resultSB.length() < 1) {
                        resultSB.append(0);
                    }
                    resultSB.append('.');
                    isDigit = true;
                } else if (isPatched) {
                    resultSB.append('0');
                }

                y *= 10;
                isPatched = true;

                if (firstPos.containsKey(y)) {
                    break;
                } else {
                    firstPos.put(y, resultSB.length());
                }
            }
        }
        if (firstPos.containsKey(y)) {
            resultSB.insert(firstPos.get(y), "(");
            resultSB.append(')');
        }
        if (flag < 0) {
            resultSB.insert(0, '-');
        }

        return resultSB.toString();
    }
}

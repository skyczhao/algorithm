package com.huaweicloud.oj;

import java.util.Scanner;

/**
 * abproblemplus_sub
 * @since 2020-04-22
 */
public class abproblemplus_sub {
    /**
     * 考察: 1. 大数减法; 2. 大小判断包括3种情况, >/=/<;
     *       3. 数字位的遍历顺序根据需要而有所不同
     *       4. 只有小于情况才借位, 不要借错了
     *       5. 问题不难, 考验细心与耐心
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                System.out.println();
            }
            String a = in.next();
            String b = in.next();

            System.out.println("Case " + (i + 1) + ":");
            System.out.println(a + " - " + b + " = " + big_sub(a, b));
        }
    }

    public static String big_sub(String left, String right) {
        boolean isPositive = true;
        if (left.length() < right.length()) {
            isPositive = false;
        } else if (left.length() == right.length()) {
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) < right.charAt(i)) {
                    isPositive = false;
                    break;
                } else if (left.charAt(i) > right.charAt(i)) {
                    break;
                }
            }
        }

        String bigger = left;
        String smaller = right;
        if (!isPositive) {
            bigger = right;
            smaller = left;
        }

        StringBuilder res = new StringBuilder();
        int idx = 0;
        int borrow = 0;
        for (; idx < smaller.length(); idx++) {
            int a = bigger.charAt(bigger.length() - 1 - idx) - '0';
            int b = smaller.charAt(smaller.length() - 1 - idx) - '0';

            if (a >= b + borrow) {
                res.append(a - b - borrow);
                borrow = 0;
            } else {
                res.append(a + 10 - b - borrow);
                borrow = 1;
            }
        }
        for (; idx < bigger.length(); idx++) {
            int a = bigger.charAt(bigger.length() - 1 - idx) - '0';

            if (a >= borrow) {
                res.append(a - borrow);
                borrow = 0;
            } else {
                res.append(a + 10 - borrow);
                borrow = 1;
            }
        }

        int mark = res.length();
        for (; mark > 1; mark--) {
            if (res.charAt(mark - 1) != '0') {
                break;
            }
        }
        return (isPositive ? "" : "-") + res.reverse().substring(res.length() - mark);
    }
}

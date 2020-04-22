package com.huaweicloud.oj;

import java.util.Scanner;

/**
 * abproblemplus
 * @since 2020-04-10
 */
public class abproblemplus {

    /**
     * 考察: 大数加法
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        for (int i = 0; i < size; i++) {
            String a = in.next();
            String b = in.next();

            System.out.println(String.format("Case %d:", i + 1));
            System.out.println(String.format("%s + %s = %s", a, b, bigadd(a, b)));
            System.out.println();
        }
    }

    private static String bigadd(String right, String left) {
        int r_len = right.length();
        int l_len = left.length();
        int length = Math.max(r_len, l_len);

        int r_value = 0;
        int l_value = 0;
        StringBuilder result = new StringBuilder();
        int inc = 0;
        for (int i = 0; i < length; i++) {

            r_value = 0;
            if (r_len > i) {
                r_value = right.charAt(r_len - 1 - i) - '0';
            }

            l_value = 0;
            if (l_len > i) {
                l_value = left.charAt(l_len - 1 - i) - '0';
            }

            int total = inc + r_value + l_value;
            inc = total / 10;

//            System.out.println(r_value + ", " + l_value);
            result.append(String.valueOf(total % 10));
        }
        if (inc > 0) {
            result.append(String.valueOf(inc));
        }

        return result.reverse().toString();
    }
}

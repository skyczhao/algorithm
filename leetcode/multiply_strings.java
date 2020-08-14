package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * multiply_strings
 * https://leetcode-cn.com/problems/multiply-strings/
 *
 * @since 2020-08-13
 */
public class multiply_strings {
    public static void main(String[] args) {
        multiply_strings sol = new multiply_strings();
        System.out.println(sol.multiply("0", "0"));
        System.out.println(sol.multiply("2", "3"));
        System.out.println(sol.multiply("999999", "99"));
        System.out.println(sol.multiply("1000001", "4"));
    }

    public String multiply(String num1, String num2) {
        List<Integer> a_num = toIntList(num1);
        List<Integer> b_num = toIntList(num2);

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < b_num.size(); i++) {
            int b_digit = b_num.get(i);
            int inc = 0;

            int j = 0;
            for (j = 0; j < a_num.size(); j++) {
                int a_digit = a_num.get(j);
                int res_digit = 0;
                if (i + j < result.size()) {
                    res_digit = result.get(i + j);
                } else {
                    result.add(0);
                }

                int t_sum = a_digit * b_digit + inc + res_digit;
                inc = t_sum / 10000;
                result.set(i + j, t_sum % 10000);
            }

            while (inc > 0) {
                int res_digit = 0;
                if (i + j < result.size()) {
                    res_digit = result.get(i + j);
                } else {
                    result.add(0);
                }

                int t_sum = res_digit + inc;
                inc = t_sum / 10000;
                result.set(i + j, t_sum % 10000);
                j++;
            }
        }

        boolean flag = false;
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (int idx = result.size() - 1; idx >= 0; idx--) {
            int value = result.get(idx);
            if (value != 0) {
                flag = true;
            }
            if (flag) {
                if (first || value > 1000) {
                    sb.append(String.format("%d", value));
                    first = false;
                } else {
                    sb.append(String.format("%04d", value));
                }
            }
        }
        if (!flag) {
            sb.append(0);
        }
        return sb.toString();
    }

    public List<Integer> toIntList(String num) {
        List<Integer> result = new ArrayList<>();
        for (int end = num.length(); end > 0; end -= 4) {
            int start = end - 4;
            if (start < 0) {
                start = 0;
            }
            result.add(Integer.parseInt(num.substring(start, end)));
        }

        return result;
    }
}

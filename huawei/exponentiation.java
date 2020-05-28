package huawei;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

/**
 * exponentiation
 * @since 2020-04-18
 */
public class exponentiation {
    /**
     * 考察: 1. 大数乘法
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String r = in.next();
            int n = in.nextInt();

            int digitSize = 0;
            StringBuilder realSb = new StringBuilder();
            for (int i = 0; i < r.length(); i++) {
                if (r.charAt(i) == '.') {
                    digitSize = r.length() - 1 - i;
                } else {
                    realSb.append(r.charAt(i));
                }
            }

            String real = realSb.toString();
            String result = "1";
            for (int i = n; i > 0; i--) {
                result = big_multi(result, real);
            }

            digitSize *= n;
            String intPart = result.substring(0, result.length() - digitSize);
            String intPartClean = "";
            for (int i = 0; i < intPart.length(); i++) {
                if (intPart.charAt(i) == '0') {
                    continue;
                }

                intPartClean = intPart.substring(i);
                break;
            }
            String floatPart = result.substring(result.length() - digitSize);
            String floatPartClean = "";
            for (int i = floatPart.length(); i > 0; i--) {
                if (floatPart.charAt(i - 1) == '0') {
                    continue;
                }

                floatPartClean = floatPart.substring(0, i);
                break;
            }

            System.out.println(intPartClean + "." + floatPartClean);
        }
    }

    private static String big_multi(String right, String left) {
        String result = "0";
        StringBuilder base = new StringBuilder();
        for (int i = right.length(); i > 0; i--) {
            result = big_add(
                    result,
                    big_multi(left, right.charAt(i - 1) - '0') + base
            );
            base.append("0");
        }

        return result;
    }

    private static String big_multi(String right, int left) {
        String result = "0";
        StringBuilder base = new StringBuilder();
        for (int i = right.length(); i > 0; i--) {
            int curr = right.charAt(i - 1) - '0';

            result = big_add(result, curr * left + base.toString());
            base.append("0");
        }

        return result;
    }

    private static String big_add(String right, String left) {
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

            result.append(String.valueOf(total % 10));
        }
        if (inc > 0) {
            result.append(String.valueOf(inc));
        }

        return result.reverse().toString();
    }
}

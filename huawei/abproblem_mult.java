package huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * abproblem_mult
 *
 * @since 2020-07-21
 */
public class abproblem_mult {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String a = in.next();
            String b = in.next();

            int[] arr_a = toIntArray(a);
            int[] arr_b = toIntArray(b);

            List<Integer> result = new ArrayList<>();
            for (int base = 0; base < arr_a.length; base++) {
                int inc = 0;
                int resIdx = base;
                for (int curr = 0; curr < arr_b.length; curr++, resIdx++) {
                    int res = 0;
                    if (resIdx < result.size()) {
                        res = result.get(resIdx);
                    } else {
                        result.add(0);
                    }

                    int currTotal = arr_a[base] * arr_b[curr] + res + inc;
                    inc = currTotal / 10000;
                    result.set(resIdx, currTotal % 10000);
                }

                if (inc > 0) {
                    if (resIdx < result.size()) {
                        result.set(resIdx, inc);
                    } else {
                        result.add(inc);
                    }
                }
            }

            boolean startFlag = true;
            for (int i = result.size(); i > 0; i--) {
                if (startFlag && result.get(i - 1) == 0) { // BUG 1
                    continue;
                }
                if (startFlag) {
                    System.out.print(result.get(i - 1));
                } else {
                    System.out.print(String.format("%04d", result.get(i - 1))); // BUG 3, try 99*99, 999*999, 9999*9999
                }
                startFlag = false;
            }
            if (startFlag) { // BUG 2, try 0*1, 0*99
                System.out.println(0);
            } else {
                System.out.println();
            }
        }

        in.close();
    }

    private static int[] toIntArray(String num) {
        int size = num.length() / 4 + 1;
        int[] result = new int[size];

        int i = 0;
        for (int idx = num.length(); idx > 0; idx-=4) {
            if (idx - 4 >= 0) {
                result[i++] = Integer.parseInt(num.substring(idx - 4, idx));
            } else {
                result[i++] = Integer.parseInt(num.substring(0, idx));
            }
        }

        return result;
    }
}

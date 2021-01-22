package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Add2ArrayFormOfInt
 * https://leetcode-cn.com/problems/add-to-array-form-of-integer/
 * 989. 数组形式的整数加法
 * https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/mo-ni-da-shu-cao-zuo-by-oshdyr-zml5/
 *
 * @since 2021-01-22
 */
public class Add2ArrayFormOfInt {
    public static void main(String[] args) {
        Add2ArrayFormOfInt sol = new Add2ArrayFormOfInt();
//        List<Integer> res = sol.addToArrayForm(new int[]{9, 9, 8, 9}, 33);
        List<Integer> res = sol.addToArrayForm(new int[]{2, 1, 5}, 806);
//        List<Integer> res = sol.addToArrayForm(new int[]{}, 806);
        for (Integer value : res) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new LinkedList<>();
        for (int v : A) {
            result.add(v);
        }

        int left = K;
        int inc = 0;
        for (int idx = result.size() - 1; idx >= 0; idx--) {
            int sum = left % 10 + inc + result.get(idx);
            left /= 10;
            result.set(idx, sum % 10);
            inc = sum / 10;
        }

        while (left > 0 || inc > 0) {
            int sum = left % 10 + inc;
            left /= 10;
            result.add(0, sum % 10);
            inc = sum / 10;
        }

        return result;
    }
}

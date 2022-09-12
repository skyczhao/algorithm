package leetcode;

import java.util.*;

/**
 * SpecialArrayWithXElementsGreaterThanOrEqualX
 * https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/solution/shu-xue-dabai-by-oshdyr-503b/
 * 1608. 特殊数组的特征值
 * https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
 *
 * @author tobin
 * @since 2022-09-12
 */
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public static void main(String[] args) {
        SpecialArrayWithXElementsGreaterThanOrEqualX sol = new SpecialArrayWithXElementsGreaterThanOrEqualX();

        System.out.println(sol.specialArray(new int[]{3, 5}));
        System.out.println(sol.specialArray(new int[]{0, 0}));
        System.out.println(sol.specialArray(new int[]{0, 4, 3, 0, 4}));
        System.out.println(sol.specialArray(new int[]{3, 6, 7, 7, 0}));
    }

    public int specialArray(int[] nums) {
        int size = nums.length;
        int[] ops = Arrays.copyOf(nums, size);
        Arrays.sort(ops);

        for (int x = size, idx = 0; x > 0; x--, idx++) {
            if (x <= ops[idx]) {
                if (idx > 0) {
                    // 中间位置判断前后
                    if (x > ops[idx - 1]) {
                        return x;
                    }
                } else {
                    // 第一位
                    return x;
                }
            }
        }
        return -1;
    }
}

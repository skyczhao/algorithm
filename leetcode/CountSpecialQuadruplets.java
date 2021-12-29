package leetcode;

import java.util.Arrays;

/**
 * CountSpecialQuadruplets
 * https://leetcode-cn.com/problems/count-special-quadruplets/
 * 1995. 统计特殊四元组
 * https://leetcode-cn.com/problems/count-special-quadruplets/solution/mo-ni-cao-zuo-ji-ke-jie-ti-by-oshdyr-1fmg/
 *
 * @author tobin
 * @since 2021-12-29
 */
public class CountSpecialQuadruplets {
    public static void main(String[] args) {
        CountSpecialQuadruplets sol = new CountSpecialQuadruplets();
        System.out.println(sol.countQuadruplets(new int[]{1, 1, 1, 3, 5}));
        System.out.println(sol.countQuadruplets(new int[]{2, 16, 9, 27, 3, 39}));
        System.out.println(sol.countQuadruplets(new int[]{28, 8, 49, 85, 37, 90, 20, 8}));
    }

    public int countQuadruplets(int[] nums) {
        if (nums == null || nums.length < 4) {
            return 0;
        }
//        Arrays.sort(nums); // bug 1: 以为没排序 // bug 2: 理解错题意了

        int result = 0;
        for (int a = 0; a < nums.length; a++) {
            for (int b = a + 1; b < nums.length; b++) {
//                if (nums[b] < nums[a]) { // bug 2: 理解错题意了
//                    continue;
//                }
                int ab = nums[a] + nums[b];
                for (int c = b + 1; c < nums.length; c++) {
//                    if (nums[c] < nums[b]) { // bug 2: 理解错题意了
//                        continue;
//                    }
                    int abc = ab + nums[c];
                    for (int d = c + 1; d < nums.length; d++) {
                        if (abc == nums[d]) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }
}

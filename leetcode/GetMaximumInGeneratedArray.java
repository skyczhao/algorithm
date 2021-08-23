package leetcode;

/**
 * GetMaximumInGeneratedArray
 * https://leetcode-cn.com/problems/get-maximum-in-generated-array/
 * 1646. 获取生成数组中的最大值
 * https://leetcode-cn.com/problems/get-maximum-in-generated-array/solution/mo-ni-ji-suan-sui-jian-dan-dan-cai-keng-riej4/
 *
 * @author tobin
 * @since 2021-08-23
 */
public class GetMaximumInGeneratedArray {
    public static void main(String[] args) {
        GetMaximumInGeneratedArray sol = new GetMaximumInGeneratedArray();
        System.out.println(sol.getMaximumGenerated(7));
        System.out.println(sol.getMaximumGenerated(100));
        System.out.println(sol.getMaximumGenerated(2));
        System.out.println(sol.getMaximumGenerated(3));
        System.out.println(sol.getMaximumGenerated(0));
    }

    public int getMaximumGenerated(int n) {
        if (n < 1) { // bug: e...
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;

        int max = 1;
        for (int i = 2; i <= n; i++) {
            int odd = i % 2;
            int idx = i / 2;
            if (odd > 0) {
                nums[i] = nums[idx] + nums[idx + 1];
            } else {
                nums[i] = nums[idx];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}

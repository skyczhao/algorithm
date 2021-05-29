package leetcode;

/**
 * TotalHammingDistance
 * https://leetcode-cn.com/problems/total-hamming-distance/
 * 477. 汉明距离总和
 * https://leetcode-cn.com/problems/total-hamming-distance/solution/jie-zhu-pai-lie-jie-ti-by-oshdyr-2t34/
 *
 * @since 2021-05-28
 */
public class TotalHammingDistance {

    public static void main(String[] args) {
        TotalHammingDistance sol = new TotalHammingDistance();

        System.out.println(sol.totalHammingDistance(new int[]{4, 14, 2}));
    }

    public int totalHammingDistance(int[] nums) {

        int[] counts = new int[32];
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            int tmp = nums[i];
            for (int j = 0; j < 32; j++) {
                counts[j] += (tmp & 1);
                tmp >>= 1;
            }
        }
        int total = 0;
        for (int i = 0; i < 32; i++) {
            total += (counts[i] * (length - counts[i]));
        }

        return total;
    }

    public int totalHammingDistance2(int[] nums) {
        int length = nums.length;
        int total = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                total += hammingDistance(nums[i], nums[j]);
            }
        }
        return total;
    }

    public int hammingDistance(int a, int b) {
        int tmp = a ^ b;
        int size = 0;
        while (tmp != 0) {
            tmp = tmp & (tmp - 1);
            size += 1;
        }
        return size;
    }
}

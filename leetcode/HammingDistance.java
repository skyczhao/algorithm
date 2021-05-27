package leetcode;

/**
 * HammingDistance
 * https://leetcode-cn.com/problems/hamming-distance/
 * 461. 汉明距离
 * https://leetcode-cn.com/problems/hamming-distance/solution/yi-ming-ju-chi-by-leetcode-solution-u1w7/
 * 可以学习下神奇的算法: Brian Kernighan 算法
 *
 * @since 2021-05-27
 */
public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance sol = new HammingDistance();
        System.out.println(sol.hammingDistance(1, 4));
        System.out.println(sol.hammingDistance(1, 1));
        System.out.println(sol.hammingDistance(1, 2));
        System.out.println(sol.hammingDistance(1, 3));
        System.out.println(sol.hammingDistance(1, -3));

    }

    public int hammingDistance(int x, int y) {
        int res = x ^ y;
        int size = 0;
        for (int i = 0; i < 32; i++) {
            size += (res & 1);
            res = res >> 1;
        }
        return size;
    }
}

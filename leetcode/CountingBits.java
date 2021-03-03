package leetcode;

/**
 * CountingBits
 * https://leetcode-cn.com/problems/counting-bits
 * 338. 比特位计数
 * https://leetcode-cn.com/problems/counting-bits/solution/dong-tai-gui-hua-zhuan-yi-han-shu-she-ji-2dlf/
 *
 * @since 2021-03-03
 */
public class CountingBits {
//    输入: 2
//    输出: [0,1,1]
//
//    输入: 5
//    输出: [0,1,1,2,1,2]

    public static void main(String[] args) {
        CountingBits sol = new CountingBits();

        int[] res = sol.countBits(20);
        for (int value : res) {
            System.out.print(value + ", ");
        }
        System.out.println();

    }

    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        bits[0] = 0;
        for (int i = 0; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }

        return bits;
    }
}

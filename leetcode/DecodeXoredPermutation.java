package leetcode;

/**
 * DecodeXoredPermutation
 * https://leetcode-cn.com/problems/decode-xored-permutation/solution/
 * 1734. 解码异或后的排列
 * https://leetcode-cn.com/problems/decode-xored-permutation/solution/li-jie-liao-ti-yi-hou-jiu-suan-shi-yi-da-92lj/
 * 注意: 读懂题目条件再做题
 *
 * @author tobin
 * @since 2021-05-11
 */
public class DecodeXoredPermutation {
    public static void main(String[] args) {
        DecodeXoredPermutation sol = new DecodeXoredPermutation();

        for (int i : sol.decode(new int[]{6, 5, 4, 6})) {
            System.out.print(i + ", ");
        }
        System.out.println();
        for (int i : sol.decode(new int[]{3, 1})) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;

        // all xor sum
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total = total ^ i;
        }

        // get first
        int a = total;
        for (int i = 1; i < encoded.length; i += 2) {
            a = a ^ encoded[i];
        }

        int[] result = new int[n];
        // get others
        int curr = a;
        for (int i = 0; i < encoded.length; i++) {
            result[i] = curr;
            curr = curr ^ encoded[i];
        }
        result[encoded.length] = curr;

        return result;
    }
}

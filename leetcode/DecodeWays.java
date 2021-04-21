package leetcode;

/**
 * DecodeWays
 * https://leetcode-cn.com/problems/decode-ways/
 * 91. 解码方法
 * https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-fei-bo-na-qi-by-oshdyr-zq0u/
 *
 * @since 2021-04-21
 */
public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays sol = new DecodeWays();
        System.out.println(sol.numDecodings("1"));
        System.out.println(sol.numDecodings("226"));
        System.out.println(sol.numDecodings("2206"));
        System.out.println(sol.numDecodings("0"));
        System.out.println(sol.numDecodings("06"));
        System.out.println(sol.numDecodings("1044"));
    }

    public int numDecodings(String s) {
        int[] counts = new int[s.length()];

        int lastDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            int currDigit = s.charAt(i) - '0';

            int oneCount = 0;
            if (currDigit > 0 && currDigit < 10) {
                if (i > 0) {
                    oneCount = counts[i - 1];
                } else {
                    oneCount = 1;
                }
            }

            int twoCount = 0;
            int value = lastDigit * 10 + currDigit;
            if (lastDigit > 0 && value > 0 && value < 27 && i > 0) {
                if (i > 1) {
                    twoCount = counts[i - 2];
                } else {
                    twoCount = 1;
                }
            }

            lastDigit = currDigit;
            counts[i] = oneCount + twoCount;
        }
        return counts[s.length() - 1];
    }
}

package leetcode;

/**
 * IntegerToRoman
 * https://leetcode-cn.com/problems/integer-to-roman/
 * 12. 整数转罗马数字
 * https://leetcode-cn.com/problems/integer-to-roman/solution/cong-da-dao-xiao-guo-lu-bing-cun-chu-jie-0yeh/
 * @author tobin
 * @since 2021-05-14
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        IntegerToRoman sol = new IntegerToRoman();

        System.out.println(sol.intToRoman(3));
        System.out.println(sol.intToRoman(4));
        System.out.println(sol.intToRoman(9));
        System.out.println(sol.intToRoman(58));
        System.out.println(sol.intToRoman(1994));
    }

    private static final int[] ranges = {
            1000, 900, 500, 400,
            100, 90, 50, 40,
            10, 9, 5, 4,
            1};
    private static final String[] symbols = {
            "M", "CM", "D", "CD",
            "C", "XC", "L", "XL",
            "X", "IX", "V", "IV",
            "I"};

    public String intToRoman(int num) {
        int value = num;
        int baseIdx = 0;
        StringBuilder resultBuilder = new StringBuilder();
        while (value > 0) {
            if (value >= ranges[baseIdx]) {
                value -= ranges[baseIdx];
                resultBuilder.append(symbols[baseIdx]);
            } else {
                baseIdx++;
            }
        }
        return resultBuilder.toString();
    }
}

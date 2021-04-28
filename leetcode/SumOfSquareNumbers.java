package leetcode;

/**
 * SumOfSquareNumbers
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 * 633. 平方数之和
 * https://leetcode-cn.com/problems/sum-of-square-numbers/solution/shuang-zhi-zhen-by-oshdyr-5rbm/
 *
 * @since 2021-04-28
 */
public class SumOfSquareNumbers {
    public static void main(String[] args) {
        SumOfSquareNumbers sol = new SumOfSquareNumbers();
        System.out.println(sol.judgeSquareSum(5));
        System.out.println(sol.judgeSquareSum(4));
        System.out.println(sol.judgeSquareSum(3));
        System.out.println(sol.judgeSquareSum(2));
        System.out.println(sol.judgeSquareSum(1));
        System.out.println(sol.judgeSquareSum(10));
        System.out.println(sol.judgeSquareSum(11));
        System.out.println(sol.judgeSquareSum(12));
        System.out.println(sol.judgeSquareSum(290000000));
    }

    public boolean judgeSquareSum(int c) {
        int cRoot = (int) Math.sqrt(c);
        if (cRoot * cRoot == c) {
            return true;
        }

        int a = 1;
        int b = (int) Math.sqrt(c) + 1;
        while (a <= b) {
            int sum = a * a + b * b;
            if (sum > c) {
                b--;
            } else if (sum < c) {
                a++;
            } else {
                return true;
            }
        }
        return false;
    }
}

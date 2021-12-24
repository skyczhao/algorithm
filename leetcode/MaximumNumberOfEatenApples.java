package leetcode;

/**
 * MaximumNumberOfEatenApples
 * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
 * 1705. 吃苹果的最大数目
 * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/solution/hua-dong-ri-qi-ji-shu-by-oshdyr-4r32/
 *
 * @author tobin
 * @since 2021-12-24
 */
public class MaximumNumberOfEatenApples {
    public static void main(String[] args) {

        MaximumNumberOfEatenApples sol = new MaximumNumberOfEatenApples();

//        System.out.println(sol.eatenApples(
//                new int[]{3, 0, 0, 0, 0, 2},
//                new int[]{3, 0, 0, 0, 0, 2}));
//        System.out.println(sol.eatenApples(
//                new int[]{1, 2, 3, 5, 2},
//                new int[]{3, 2, 1, 4, 2}));
        System.out.println(sol.eatenApples(
                new int[]{0, 19, 19, 19, 11, 14, 33, 0, 28, 7, 0, 28, 7, 0, 21, 16, 0, 22, 0, 13, 8, 0, 19, 0, 0, 2, 26, 2, 22, 0, 8, 0, 0, 27, 19, 16, 24, 0, 20, 26, 20, 7, 0, 0, 29, 0, 0, 16, 19, 0, 0, 0, 29, 30, 17, 0, 23, 0, 0, 26, 24, 13, 3, 0, 21, 0, 18, 0},
                new int[]{0, 5, 1, 16, 7, 10, 54, 0, 40, 2, 0, 23, 4, 0, 20, 18, 0, 40, 0, 22, 8, 0, 35, 0, 0, 3, 24, 1, 8, 0, 10, 0, 0, 2, 38, 8, 4, 0, 36, 33, 14, 9, 0, 0, 56, 0, 0, 21, 27, 0, 0, 0, 14, 20, 18, 0, 42, 0, 0, 44, 3, 8, 3, 0, 10, 0, 27, 0}));
    }

    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        if (n == 0 || n != days.length) {
            return 0;
        }

        int[] daysWithApples = new int[4 * 10000 + 1]; // bug: 苹果数+下标超过了2*10^4
        int total = 0;
        int max_n = n;
        for (int i = 0; i < n; i++) {
            int days_end = i + days[i];
            if (days_end > max_n) {
                max_n = days_end;
            }
            if (apples[i] > 0) { // bug: 首位为0
                daysWithApples[days_end - 1] += apples[i];
            }
            for (int j = i; j < max_n; j++) { // opti: 改用最大日期
                if (daysWithApples[j] > 0) {
                    daysWithApples[j]--;
                    total++;
                    break;
                }
            }
        }
        for (int i = n; i < max_n; i++) {
            for (int j = i; j < max_n; j++) { // opti: 改用最大日期
                if (daysWithApples[j] > 0) {
                    daysWithApples[j]--;
                    total++;
                    break;
                }
            }
        }
        return total;
    }
}

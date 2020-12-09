package leetcode;

/**
 * UniquePaths
 * https://leetcode-cn.com/problems/unique-paths/
 * 62. 不同路径
 *
 * @author tobin
 * @since 2020-12-09
 */
public class UniquePaths {
    public static void main(String[] args) {

        UniquePaths sol = new UniquePaths();
        System.out.println(sol.uniquePaths(3, 7));
        System.out.println(sol.uniquePaths(3, 2));
        System.out.println(sol.uniquePaths(7, 3));
        System.out.println(sol.uniquePaths(3, 3));
        System.out.println(sol.uniquePaths(1, 2));
        System.out.println(sol.uniquePaths(1, 1)); // 1,1也要走1步...
        System.out.println(sol.uniquePaths(10, 10));
    }

    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            // 1,1也要走1步...
            return 1;
        }

        int totalSteps = m + n - 2;
        if (totalSteps < 1) {
            return 0;
        }

        int changeTimes = m > n ? m - 1 : n - 1;
        if (changeTimes == 0) {
            return totalSteps;
        }

        // 求组合数
        double up = 1; // 分子, 中间数越界
//        long down = 1; // 分母
        while (changeTimes > 0) {
            up *= totalSteps;
            up /= changeTimes;
//            down *= changeTimes;
            totalSteps--;
            changeTimes--;
        }
        return (int) Math.round(up);
    }
}

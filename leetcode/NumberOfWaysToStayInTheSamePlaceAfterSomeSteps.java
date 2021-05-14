package leetcode;

/**
 * NumberOfWaysToStayInTheSamePlaceAfterSomeSteps
 * https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
 * 1269. 停在原地的方案数
 * https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/solution/dong-tai-gui-hua-ji-ben-cai-liao-neng-ca-0oi4/
 *
 * @author tobin
 * @since 2021-05-13
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        NumberOfWaysToStayInTheSamePlaceAfterSomeSteps sol = new NumberOfWaysToStayInTheSamePlaceAfterSomeSteps();
        System.out.println(sol.numWays(3, 2));
        System.out.println(sol.numWays(4, 2)); // error
        System.out.println(sol.numWays(27, 7)); // out of bound
        System.out.println(sol.numWays(430, 148488)); // out of memory
    }

    private static final int BOUND = 1000000007;

    public int numWays(int steps, int arrLen) {
        int maxSteps = Integer.min(steps, arrLen);
        int[][] mat = new int[steps + 1][maxSteps]; // BUG 3: 数组的边界只要到达最大步数即可;

        mat[0][0] = 1;
        for (int j = 1; j < maxSteps; j++) { // BUG 3: 数组的边界只要到达最大步数即可;
            mat[0][j] = 0;
        }

        for (int i = 1; i <= steps; i++) { // WARN: 步伐的边界
            for (int j = 0; j < maxSteps; j++) { // BUG: 数组的边界; BUG 3: 数组的边界只要到达最大步数即可;
                // 通过上一步 停一步 到达该位置的数量
                int stopSize = 0;
                if (i > 0) {
                    stopSize = mat[i - 1][j]; // stop
                }

                // 通过上一步 向右一步 到达该位置的数量
                int rightSize = 0;
                if (i > 0 && j > 0) {
                    rightSize = mat[i - 1][j - 1]; // to right
                }

                // 通过上一步 向左一步 到达该位置的数量
                int leftSize = 0;
                if (i > 0 && j + 1 < maxSteps) { // BUG 2: 限制最远到达的位置
                    leftSize = mat[i - 1][j + 1]; // to left
                }

                mat[i][j] = ((stopSize + rightSize) % BOUND + leftSize) % BOUND;
            }
        }

        return mat[steps][0];
    }
}

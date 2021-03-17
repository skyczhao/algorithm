package leetcode;

/**
 * DistinctSubsequences
 * https://leetcode-cn.com/problems/distinct-subsequences/
 * 115. 不同的子序列
 * https://leetcode-cn.com/problems/distinct-subsequences/solution/dong-tai-gui-hua-jie-ti-by-oshdyr-52r7/
 *
 * @since 2021-03-17
 */
public class DistinctSubsequences {

    public static void main(String[] args) {

        DistinctSubsequences sol = new DistinctSubsequences();
        System.out.println(sol.numDistinct("rabbbit", "rabbit"));
        System.out.println(sol.numDistinct("babgbag", "bag"));
    }


    public int numDistinct(String s, String t) {

        int row = t.length();
        int col = s.length();

        int[][] mat = new int[row][col];
        for (int y = 0; y < col; y++) {
            char c = s.charAt(y);
            for (int x = 0; x < row; x++) {
                char v = t.charAt(x);
                if (c == v) {
                    int total = 0;
                    if (x > 0) {
                        for (int j = 0; j < y; j++) {
                            total += mat[x - 1][j];
                        }
                    } else {
                        total = 1;
                    }
                    mat[x][y] = total;
                } else {
                    mat[x][y] = 0;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < col; i++) {
            res += mat[row - 1][i];
        }
        return res;
    }
}

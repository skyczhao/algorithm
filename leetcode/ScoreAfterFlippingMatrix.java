package leetcode;

/**
 * ScoreAfterFlippingMatrix
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/
 * 861. 翻转矩阵后的得分
 *
 * @author tobin
 * @since 2020-12-07
 */
public class ScoreAfterFlippingMatrix {

    public static void main(String[] args) {
        ScoreAfterFlippingMatrix sol = new ScoreAfterFlippingMatrix();
        int ret = sol.matrixScore(new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}});
        System.out.println("max: " + ret);
    }

    // 贪心
    public int matrixScore(int[][] A) {
        if (A == null) {
            return 0;
        }

        int rowNum = A.length;
        int colNum = A[0].length;
        boolean[][] bA = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                bA[i][j] = (A[i][j] == 1);
            }
        }

        for (int i = 0; i < rowNum; i++) {
            if (!bA[i][0]) {
                for (int j = 0; j < colNum; j++) {
                    bA[i][j] = !bA[i][j];
                }
            }
        }
        int base = 1;
        int sum = 0;
        int tmp = 0;
        for (int j = colNum - 1; j >= 0; j--) {
            tmp = 0;
            for (int i = 0; i < rowNum; i++) {
                if (bA[i][j]) {
                    tmp++;
                }
            }
            sum += ((rowNum - tmp > tmp ? rowNum - tmp : tmp) * base);
            base *= 2;
        }

        return sum;
    }
}

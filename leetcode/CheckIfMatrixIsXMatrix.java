package leetcode;

/**
 * CheckIfMatrixIsXMatrix
 * https://leetcode.cn/problems/check-if-matrix-is-x-matrix/
 * 2319. 判断矩阵是否是一个 X 矩阵
 * https://leetcode.cn/problems/check-if-matrix-is-x-matrix/solutions/2085606/bao-li-bian-li-sou-suo-by-oshdyr-v3on/
 *
 * @author tobin
 * @since 2023-01-31
 */
public class CheckIfMatrixIsXMatrix {
    public static void main(String[] args) {
        CheckIfMatrixIsXMatrix sol = new CheckIfMatrixIsXMatrix();

        int[][] grid = new int[][]{{2, 0, 0, 1}, {0, 3, 1, 0}, {0, 5, 2, 0}, {4, 0, 0, 2}};
        System.out.println(sol.checkXMatrix(grid));
        int[][] grid2 = new int[][]{{5, 7, 0}, {0, 3, 1}, {0, 5, 0}};
        System.out.println(sol.checkXMatrix(grid2));
    }

    public boolean checkXMatrix(int[][] grid) {
        int size = grid.length;
        if (size < 1) {
            return false;
        }
        if (size != grid[0].length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

package leetcode;

/**
 * SpiralMatrixII
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 * 59. 螺旋矩阵 II
 * https://leetcode-cn.com/problems/spiral-matrix-ii/solution/mo-ni-bian-jie-pan-duan-by-oshdyr-0des/
 *
 * @since 2021-03-16
 */
public class SpiralMatrixII {
    public static void main(String[] args) {
        SpiralMatrixII sol = new SpiralMatrixII();

        int[][] res = sol.generateMatrix(4);
        for (int[] row : res) {
            for (int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }

        int[][] res2 = sol.generateMatrix(1);
        for (int[] row : res2) {
            for (int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }

        int[][] res3 = sol.generateMatrix(3);
        for (int[] row : res3) {
            for (int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }

        int[][] res4 = sol.generateMatrix(2);
        for (int[] row : res4) {
            for (int value : row) {
                System.out.print(value + ", ");
            }
            System.out.println();
        }

    }

    public int[][] generateMatrix(int n) {

        int[][] res = new int[n][n];
        int value = 1;

        int x = 0;
        int y = 0;
        int pad_right = 1;
        int pad_down = 1;
        int pad_left = 1;
        int pad_up = 2;
        while (true) {
            if (y >= n - pad_right) {
                break;
            }
            for (;y < n - pad_right; y++) {
                res[x][y] = value++;
            }
            pad_right++;

            if (x >= n - pad_down) {
                break;
            }
            for (; x < n - pad_down; x++) {
                res[x][y] = value++;
            }
            pad_down++;

            if (y < pad_left) {
                break;
            }
            for (; y >= pad_left; y--) {
                res[x][y] = value++;
            }
            pad_left++;

            if (x < pad_up) {
                break;
            }
            for (; x >= pad_up; x--) {
                res[x][y] = value++;
            }
            pad_up++;
        }
        res[x][y] = value;

        return res;
    }
}

package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * SpiralMatrix
 * https://leetcode-cn.com/problems/spiral-matrix/
 * 54. 螺旋矩阵
 * https://leetcode-cn.com/problems/spiral-matrix/solution/mo-ni-cao-zuo-chu-li-te-shu-qing-kuang-b-dmw1/
 *
 * @since 2021-03-15
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix sol = new SpiralMatrix();

        List<Integer> res = sol.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}}
        );
        for (Integer value : res) {
            System.out.print(value + ", ");
        }
        System.out.println();


        List<Integer> res2 = sol.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {13, 14, 15, 16}}
        );
        for (Integer value : res2) {
            System.out.print(value + ", ");
        }
        System.out.println();

        List<Integer> res3 = sol.spiralOrder(new int[][]{
                {2},
                {3}}
        );
        for (Integer value : res3) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        int row = matrix.length;
        int i = 0;
        int col = matrix[0].length;
        int j = 0;

        boolean flag = true;
        int pad_right = 1;
        int pad_down = 1;
        int pad_left = 1;
        int pad_up = 2;
        List<Integer> result = new LinkedList<>();
        while (true) {
            flag = true;
            for (; j < col - pad_right; j++) {
                flag = false;
                result.add(matrix[i][j]);
            }
            pad_right++;
            if (flag) {
                break;
            }

            flag = true;
            for (; i < row - pad_down; i++) {
                flag = false;
                result.add(matrix[i][j]);
            }
            pad_down++;
            if (flag) {
                break;
            }

            flag = true;
            for (; j >= pad_left; j--) {
                flag = false;
                result.add(matrix[i][j]);
            }
            pad_left++;
            if (flag) {
                break;
            }

            flag = true;
            for (; i >= pad_up; i--) {
                flag = false;
                result.add(matrix[i][j]);
            }
            pad_up++;
            if (flag) {
                break;
            }
        }
        if (col == 1) {
            for (; i < row; i++) {
                result.add(matrix[i][j]);
            }
        } else {
            result.add(matrix[i][j]);
        }

        return result;
    }
}

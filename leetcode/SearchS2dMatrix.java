package leetcode;

/**
 * SearchS2dMatrix
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * 74. 搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix/solution/zi-dai-fen-zhi-sou-suo-by-oshdyr-oddq/
 *
 * @since 2021-03-30
 */
public class SearchS2dMatrix {

    public static void main(String[] args) {
        SearchS2dMatrix sol = new SearchS2dMatrix();
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(sol.searchMatrix(matrix, 3));
        System.out.println(sol.searchMatrix(matrix, 0));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int lastX = -1;
        for (int x = 0; x < matrix.length; x++) {
            if (target < matrix[x][0]) {
                // find in row lastX
                if (lastX >= 0) {
                    for (int value : matrix[lastX]) {
                        if (value == target) {
                            return true;
                        }
                    }
                }
                break;
            }

            lastX = x;
        }
        // BUG: [[1]]失败
        // find in row lastX
        if (lastX >= 0) {
            for (int value : matrix[lastX]) {
                if (value == target) {
                    return true;
                }
            }
        }

        return false;
    }
}

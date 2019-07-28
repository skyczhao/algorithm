#include <iostream>
#include <vector>

using namespace std;

// 执行用时 :0 ms, 在所有 C++ 提交中击败了100.00%的用户
// 内存消耗 :8.6 MB, 在所有 C++ 提交中击败了63.60%的用户

void output_result(vector<int> result) {
    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << ", ";
    }
    cout << endl;
}

class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> result = vector<int>();

        int row = matrix.size();
        // Tips 3: empty array!
        if (row == 0) {
            return result;
        }
        int col = matrix[0].size();

        // Tips 4: 偶数size就不用+1
        int pad_x = col / 2;
        if (col % 2 > 0) {
            pad_x++;
        }
        int pad_y = row / 2;
        if (row % 2 > 0) {
            pad_x++;
        }

        // cout << pad_x << endl;
        // cout << pad_y << endl;

        int x = 0, y = 0;
        bool flag = false;
        while (x < pad_x && y < pad_y) {

            // 第一行
            for (int i = x; i < col - x; i++) {
                result.push_back(matrix[y][i]);
            }
            // output_result(result);

            // 最后一列
            for (int i = y + 1; i < row - y; i++) {
                result.push_back(matrix[i][col - x - 1]);
            }
            // output_result(result);

            // 最后一行
            // Tips 2: 不重复遍历
            if (row - y - 1 != y) {
                for (int i = col - x - 1 - 1; i >= x; i--) {
                    result.push_back(matrix[row - y - 1][i]);
                }
            }
            // output_result(result);

            // 第一列
            // Tips 2: 不重复遍历
            if (col - x - 1 != x) {
                // Tips 1: 非i >= y, 因为第一位已经被使用
                for (int i = row - y - 1 - 1; i > y; i--) {
                    result.push_back(matrix[i][x]);
                }
            }
            // output_result(result);

            x++, y++;
            flag = true;
        }

        // Tips 4: 后补输出
        // Tips 5: 避免2 x 3情况
        if (x < pad_x && y < row - 1) {
            for (int i = x; i < col - x; i++) {
                result.push_back(matrix[y][i]);
            }
        }
        // Tips 5: 避免3 x 2情况
        if (y < pad_y && x < col - 1) {
            for (int i = y; i < row - y; i++) {
                result.push_back(matrix[i][x]);
            }
        }

        // Tips 6: 针对只有一行, 一列的情况
        if (result.empty()) {
            for (int i = 0; i < col; i++) {
                result.push_back(matrix[0][i]);
            }
        }
        if (result.empty()) {
            for (int i = 0; i < row; i++) {
                result.push_back(matrix[i][0]);
            }
        }

        return result;
    }
};

int main() {
    Solution s = Solution();

    // 3 x 3
    int row1[] = {1, 2, 3};
    int row2[] = {4, 5, 6};
    // int row3[] = {7, 8, 9};

    vector<int> line1 = vector<int>(row1, row1 + 3);
    vector<int> line2 = vector<int>(row2, row2 + 3);
    // vector<int> line3 = vector<int>(row3, row3 + 3);

    vector<vector<int>> matrix = vector<vector<int>>();
    matrix.push_back(line1);
    matrix.push_back(line2);
    // matrix.push_back(line3);

    vector<int> result = s.spiralOrder(matrix);

    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << ", ";
    }
    cout << endl;

    // 1 * 8
    int row4[] = {1, 2, 3, 4, 5, 7, 8, 9};
    vector<int> line4 = vector<int>(row4, row4 + 8);
    vector<vector<int>> matrix2 = vector<vector<int>>();
    matrix2.push_back(line4);

    vector<int> result2 = s.spiralOrder(matrix2);

    for (int i = 0; i < result2.size(); i++) {
        cout << result2[i] << ", ";
    }
    cout << endl;

    return 0;
}
#include <iostream>
#include <vector>
using namespace std;

void print_matrix(vector<vector<int>> matrix) {
    for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix[i].size(); j++) {
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int row_size = matrix.size();
        if (row_size < 1) return;
        int col_size = matrix[0].size();
        if (row_size != col_size) return;

        int times = row_size / 2;
        for (int step = 0; step < times; step++) { // row
            for (int i = step; i < row_size - step - 1; i++) { // col, 最后一位不用转了, 已经转好了
                // cout << i << "_" << step << endl;
                int temp = matrix[step][i];
                matrix[step][i] = matrix[row_size - 1 - i][step];
                matrix[row_size - 1 - i][step] = matrix[row_size - 1 - step][row_size - 1 - i];
                matrix[row_size - 1 - step][row_size - 1 - i] = matrix[i][row_size - 1 - step];
                matrix[i][row_size - 1 - step] = temp;
            }
        }
    }
};

int main() {
    int row_1[] = {5, 1, 9,11};
    vector<int> vec_1(row_1, row_1 + 4);

    int row_2[] = {2, 4, 8,10};
    vector<int> vec_2(row_2, row_2 + 4);

    int row_3[] = {13, 3, 6, 7};
    vector<int> vec_3(row_3, row_3 + 4);

    int row_4[] = {15,14,12,16};
    vector<int> vec_4(row_4, row_4 + 4);

    vector<vector<int>> matrix = vector<vector<int>>();
    matrix.push_back(vec_1);
    matrix.push_back(vec_2);
    matrix.push_back(vec_3);
    matrix.push_back(vec_4);

    print_matrix(matrix);
    Solution s = Solution();
    s.rotate(matrix);
    cout << "=====" << endl;
    print_matrix(matrix);

}

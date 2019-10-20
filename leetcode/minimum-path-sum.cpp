#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :12 ms, 在所有 cpp 提交中击败了74.28%的用户
// 内存消耗 :10.5 MB, 在所有 cpp 提交中击败了92.40%的用户
class Solution {
public:
    /**
     * dijkstra
     * rolling array
     */
    int minPathSum(vector<vector<int>>& grid) {

        int length = grid[0].size();
        int front_pos = 0;
        int up_pos = 0;

        vector<int> result = vector<int>();
        // 单独处理第一行
        for (int i = 0; i < length; i++) {
            front_pos = 0;
            if (i - 1 >= 0) {
                front_pos = result[i - 1];
            }
            result.push_back(grid[0][i] + front_pos);
        }

        // 后续行
        for (int i = 1; i < grid.size(); i++) {
            for (int j = 0; j < length; j++) {
                // 前
                front_pos = INT_MAX;
                if (j - 1 >= 0) {
                    front_pos = result[j - 1]; 
                }
                // 上
                up_pos = result[j]; 

                if (front_pos < up_pos) {
                    result[j] = front_pos + grid[i][j];
                } else {
                    result[j] = up_pos + grid[i][j];
                }

                // cout << result[j] << ", ";
            }
            // cout << endl;
        }
        return result[length - 1];
    }
};

int main() {
    Solution s = Solution();

    vector<vector<int>> mat = vector<vector<int>>();

    int row1[] = {1, 3, 1};
    int row2[] = {1, 5, 1};
    int row3[] = {4, 2, 1};

    mat.push_back(vector<int>(row1, row1 + 3));
    mat.push_back(vector<int>(row2, row2 + 3));
    mat.push_back(vector<int>(row3, row3 + 3));

    cout << s.minPathSum(mat) << endl;
}
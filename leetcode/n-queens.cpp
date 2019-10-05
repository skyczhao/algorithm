#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :8 ms, 在所有 C++ 提交中击败了95.16%的用户
// 内存消耗 :10.6 MB, 在所有 C++ 提交中击败了47.07%的用户
class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> result = vector<vector<string>>();
        vector<int> pos_y = vector<int>();

        dfs(result, pos_y, 0, n);

        return result;
    }

    /**
     * search result
     */
    void dfs(vector<vector<string>> &result, vector<int> &pos_y, int level, const int &size) {
        // find the end
        if (level == size) {
            // for check
            // for (int i = 0; i < pos_y.size(); i++) {
            //     cout << pos_y[i] << ",";
            // }
            // cout << endl;

            // gen result
            vector<string> mat = vector<string>();
            for (int x = 0; x < size; x++) {
                string row = "";
                for (int y = 0; y < size; y++) {
                    if (y != pos_y[x]) {
                        row.append(".");
                    } else {
                        row.append("Q");
                    }
                }
                mat.push_back(row);
            }
            result.push_back(mat);
            return;
        }

        // dfs search / recursive search
        for (int i = 0; i < size; i++) {
            if (isNotValid(pos_y, level, i)) {
                continue;
            }

            pos_y.push_back(i);
            dfs(result, pos_y, level + 1, size);
            pos_y.pop_back();
        }
    }

    /**
     * judge wether new idx is valid
     * by using calculation
     * 1. x == ~x || y == ~y
     * 2. abs(x - ~x) == abs(y - ~x)
     */
    bool isNotValid(vector<int> &pos_y, int level, int idx) {

        int y = 0;
        for (int x = 0; x < pos_y.size(); x++) {
            y = pos_y[x];

            if (y == idx) {
                return true;
            }
            if (abs(x - level) == abs(y - idx)) {
                return true;
            }
        }
        return false;
    }
};

int main() {
    Solution s = Solution();

    vector<vector<string>> result = s.solveNQueens(4);

    for (int i = 0; i < result.size(); i++) {
        for (int j = 0; j < result[i].size(); j++) {
            cout << result[i][j] << endl;
        }
        cout << "---" << endl;
    }
    return 0;
}
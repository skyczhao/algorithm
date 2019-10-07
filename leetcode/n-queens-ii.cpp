#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :4 ms, 在所有 C++ 提交中击败了92.78%的用户
// 内存消耗 :8.2 MB, 在所有 C++ 提交中击败了72.24%的用户
class Solution {
public:
    int totalNQueens(int n) {
        vector<int> pos = vector<int>();
        int total = 0;
        dfs(pos, 0, total, n);

        return total;
    }

    /**
     * 搜索加剪枝
     */
    void dfs(vector<int> &pos, const int level, int &total, const int &size) {

        // 停止条件
        if (level >= size) {
            total += 1;
            return;
        }

        // 剪枝搜索
        for (int i = 0; i < size; i++) {
            // 不合法位置跳过
            if (notValid(pos, i, level)) {
                continue;
            }

            // 合法位置递归
            pos.push_back(i);
            dfs(pos, level + 1, total, size);
            pos.pop_back();
        }
    }

    /**
     * 不合法的位置
     * 1. 斜线上: abs(dist x)相等abs(dist y)
     * 2. 直线上: x相等或y相等
     */
    bool notValid(vector<int> &pos, int idx, int level) {
        for (int x = 0; x < pos.size(); x++) {
            int y = pos[x];
            if (y == idx) {
                return true;
            }
            if (abs(y - idx) == abs(x - level)) {
                return true;
            }
        }

        return false;
    }
};

int main() {
    Solution s = Solution();

    cout << s.totalNQueens(8) << endl;
    cout << s.totalNQueens(4) << endl;
    return 0;
}
#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    // candidates是正数, 而且是整数, 而且互不相等
    // 0既不是正数也不是负数
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<int>> result = vector<vector<int>>();

        // 初始化
        stack<vector<int>> canResult = stack<vector<int>>();
        // 为了做去重, 存index, 而不是存值
        for (int i = 0; i < candidates.size(); i++) {
            vector<int> init = vector<int>();
            init.push_back(i);
            canResult.push(init);
        }

        // DFS with stack
        while (!canResult.empty()) {
            vector<int> tmp = canResult.top();
            canResult.pop();

            // 求和
            int tmpSum = 0;
            int lastIndex = 0;
            for (int m = 0; m < tmp.size(); m++) {
                lastIndex = tmp[m];
                tmpSum += candidates[lastIndex];
            }

            // 减枝
            if (tmpSum == target) {
                vector<int> line = vector<int>();
                for (int m = 0; m < tmp.size(); m++) {
                    line.push_back(candidates[tmp[m]]);
                }   
                result.push_back(line);
            } else if (tmpSum < target) {
                // 保证index是递增的
                for (int i = lastIndex; i < candidates.size(); i++) {
                    int nextSum = tmpSum + candidates[i];
                    // OPT 1: 提前剪枝, 减少空间使用
                    if (nextSum <= target) {
                        vector<int> next = vector<int>(tmp);
                        next.push_back(i);
                        canResult.push(next);
                    } 
                }
            }

            // cout << canResult.size() << endl;
        }

        return result;
    }
};

int main() {
    int can[] = {2, 3, 6, 7};
    vector<int> canVec(can, can + 4);

    Solution s = Solution();
    vector<vector<int>> result = s.combinationSum(canVec, 7);
    for (int i = 0; i < result.size(); i++) {
        vector<int> line = result[i];
        for (int j = 0; j < line.size(); j++) {
            cout << line[j] << " ";
        }
        cout << endl;
    }
}
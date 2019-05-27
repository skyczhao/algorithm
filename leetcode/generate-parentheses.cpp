#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result = vector<string>();
        dfs("", 0, 2 * n, result);
        return result;
    }

    void dfs(string str, int total, int left, vector<string> &result) {

        if (left == 0) {
            result.push_back(str);
        } else if (left > 0) {
            if (left - total > 0) { // 这里的条件思考要绕一绕
                dfs(str + "(", total + 1, left - 1, result);
            }
            if (total > 0) {
                dfs(str + ")", total - 1, left - 1, result);
            }
        }
    }
};

int main() {
    Solution s = Solution();
    vector<string> vs = s.generateParenthesis(5);
    for (int i = 0; i < vs.size(); i++) {
        cout << vs[i] << endl;
    }
}
#include <iostream>
#include <stack>
using namespace std;

// 执行用时 : 4 ms, 在所有 C++ 提交中击败了 97.72% 的用户
// 内存消耗 : 9.6 MB, 在所有 C++ 提交中击败了 79.79% 的用户
class Solution {
public:
    int longestValidParentheses(string s) {
        int size = s.length();

        bool *flags = new bool[size + 1]; // add one for customize end
        stack<int> leftQuoteIdx = stack<int>();

        // pair quotes
        for (int i = 0; i < size; i++) {
            flags[i] = true;
            switch (s[i]) {
                case '(':
                    leftQuoteIdx.push(i);
                    break;
                case ')':
                    if (!leftQuoteIdx.empty()) {
                        leftQuoteIdx.pop();
                    } else {
                        // mark as fail
                        flags[i] = false;
                    }
                    break;
                default:
                    continue;
            }
        }
        flags[size] = false;

        // clear left_quote_idx & marked
        while (!leftQuoteIdx.empty()) {
            flags[leftQuoteIdx.top()] = false;
            leftQuoteIdx.pop();
        }

        int max = 0; // BUG 1: empty str
        int last_idx = -1; // BUG 2: add one for customize start
        for (int i = 0; i < size + 1; i++) { // add one for customize end
            if (!flags[i]) {
                int current = i - last_idx - 1;
                if (current > max) {
                    max = current;
                }
                last_idx = i;
            }
        }

        return max;
    }
};

int main() {

    Solution s = Solution();
    cout << s.longestValidParentheses("(()") << endl;
    cout << s.longestValidParentheses("()") << endl;
    cout << s.longestValidParentheses("(()") << endl;
    cout << s.longestValidParentheses(")()())(()") << endl;
    cout << s.longestValidParentheses("") << endl;

    return 0;
}
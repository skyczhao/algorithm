#include <iostream>
using namespace std;

// 执行用时 :4 ms, 在所有 C++ 提交中击败了85.74%的用户
// 内存消耗 :8.6 MB, 在所有 C++ 提交中击败了91.87%的用户
class Solution {
public:
    int lengthOfLastWord(string s) {
        int size = s.length();

        int result = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (s[i] == ' ') {
                if (result > 0) {
                    break;
                } else { // BUG 1: tailing space
                    continue;
                }
            }
            result += 1;
        }

        return result;
    }
};

int main() {
    Solution s = Solution();
    cout << s.lengthOfLastWord("") << endl;
    cout << s.lengthOfLastWord("Hello World") << endl;
    cout << s.lengthOfLastWord("Hello ") << endl;
    return 0;
}
#include <iostream>
using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        if (s.size() < 1) {
            return s;
        }
        // 坑2: 边界条件, 只有1的时候内循环死循环
        if (numRows == 1) {
            return s;
        }

        string result = "";

        int length = s.size();
        int step = numRows - 1;
        for (int i = 0; i < numRows; i++) {
            for (int n = i; n < length; n += 2 * step) {
                // 坑1: 找上一个位置会漏
                // int zIdx = n - 2 * i; // 上一个位置
                // if (i > 0 && i < step && zIdx > 0) {
                //     result += s[zIdx];
                // }

                result += s[n]; // base

                // 找下一个位置
                int zIdx = n + 2 * step - 2 * i; // 下一个位置
                if (i > 0 && i < step && zIdx < length) {
                    result += s[zIdx];
                }
            }
        }

        return result;
    }
};

int main() {
    Solution s = Solution();
    cout << s.convert("LEETCODEISHIRING", 3) << endl;
    cout << s.convert("LEETCODEISHIRING", 4) << endl;
    cout << s.convert("A", 1) << endl;

    return 0;
}
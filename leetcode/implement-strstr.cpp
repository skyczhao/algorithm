#include <iostream>
using namespace std;

class Solution {
public:
    int strStr(string haystack, string needle) {
        if (needle.size() == 0) {
            return 0;
        }

        int farest = haystack.size() - needle.size() + 1;
        for (int i = 0; i < farest; i++) {
            bool flag = true;
            for (int j = 0; j < needle.size(); j++) {
                if (haystack[i + j] != needle[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
};

int main() {
    Solution s = Solution();
    cout << s.strStr("hello", "ll") << endl;
    cout << s.strStr("aaaaaaa", "bba") << endl;
    cout << s.strStr("qwewqa", "") << endl;
    return 0;
}
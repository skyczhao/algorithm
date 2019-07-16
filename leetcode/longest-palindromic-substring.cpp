#include <iostream>
using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        int length = s.size();

        int begin = 0;
        int end = 0;
        string tmp = "";

        string result = "";
        for (int mid = 0; mid < length; mid++) {

            begin = mid - 1;
            end = mid + 1;
            while (begin >= 0 && end < length) {
                if (s[begin] != s[end]) {
                    break;
                }

                begin--;
                end++;

            }
            
            // EXP 1: bab
            if (begin < 0) {
                int dist = 0 - begin;
                begin += dist;
                end -= dist;
            }
            if (s[begin] != s[end]) {
                begin++;
                end--;
            }
            tmp = s.substr(begin, end - begin + 1);
            if (tmp.size() > result.size()) {
                result = tmp;
            }

            begin = mid;
            end = mid + 1;
            while (begin >= 0 && end < length) {
                if (s[begin] != s[end]) {
                    break;
                }

                begin--;
                end++;

            }
            
            // exp 2: bb
            if (begin < 0) {
                int dist = 0 - begin;
                begin += dist;
                end -= dist;
            }
            if (s[begin] != s[end]) {
                begin++;
                end--;
            }
            tmp = s.substr(begin, end - begin + 1);
            if (tmp.size() > result.size()) {
                result = tmp;
            }

            // ERROR 1: "ababababa" -> "ababababa" not "abababa"
            // if (result.size() > length - mid - 1) {
            //     break;
            // }
        }

        return result;
        
    }
};

int main() {
    Solution s = Solution();
    cout << s.longestPalindrome("babad") << endl;
    cout << s.longestPalindrome("cbbd") << endl;
    cout << s.longestPalindrome("bb") << endl;

    return 0;
}
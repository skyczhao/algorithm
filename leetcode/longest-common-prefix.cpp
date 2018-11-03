#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.size() < 1) return "";

        int length = INT_MAX;
        for (int i = 0; i < strs.size(); i++) {
            if (strs[i].size() < length) {
                length = strs[i].size();
            }
        }

        string x = "";
        char current;
        bool flag = true;
        for (int i = 0; i < length; i++) {
            current = strs[0][i];
            flag = true;
            for (int j = 1; j < strs.size(); j++) {
                if (current != strs[j][i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                x = x + current;
            } else {
                break;
            }
        }
        return x;
    }
};

int main() {
    Solution a = Solution();
    vector<string> vstr = vector<string>();
    vstr.push_back("flower");
    vstr.push_back("flow");
    vstr.push_back("flight");

    cout << a.longestCommonPrefix(vstr) << endl;
    return 0;
}
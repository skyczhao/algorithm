#include <iostream>
using namespace std;

class Solution {
  public:
    string countAndSay(int n) {
        string result = "1";
        for (int i = 1; i < n; i++) {
            result = countNext(result);
        }
        return result;
    }

    string countNext(string x) {
        int len = x.size();
        if (len < 1) {
            return "";
        }

        string result = ""; // string 效率低
        char last = x[0];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (x[i] == last) {
                count++;
            } else {
                result += (count + '0');
                result += last;

                last = x[i];
                count = 1;
            }
        }
        result += (count + '0');
        result += last;

        return result;
    }
};

int main()
{
    Solution s = Solution();
    cout << s.countAndSay(1) << endl;
    cout << s.countAndSay(2) << endl;
    cout << s.countAndSay(3) << endl;
    cout << s.countAndSay(4) << endl;
    cout << s.countAndSay(5) << endl;
    cout << s.countAndSay(6) << endl;
}
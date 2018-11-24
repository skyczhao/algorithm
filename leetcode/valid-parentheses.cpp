#include <iostream>
using namespace std;

bool is_pair(char left, char right)
{
    if (left == '(' && right == ')') return true;
    if (left == '[' && right == ']') return true;
    if (left == '{' && right == '}') return true;
    return false;
}

class Solution
{
  public:
    bool isValid(string s)
    {
        char str[s.size() + 1];
        memset(str, 0, (s.size() + 1) * sizeof(char));
        
        int str_size = 0;
        for (int i = 0; i < s.size(); i++) {
            if (str_size > 0) {
                if (is_pair(str[str_size - 1], s[i])) {
                    str_size--;
                    continue;
                }
            }

            str[str_size++] = s[i];
            // cout << str << endl;
        }

        return str_size == 0;
    }
};

int main()
{
    Solution s = Solution();
    cout << s.isValid("{[]}") << endl;
    cout << s.isValid("([)]") << endl;
    cout << s.isValid("()[]{}") << endl;
    cout << s.isValid("(]") << endl;
    cout << s.isValid("()") << endl;
    return 0;
}
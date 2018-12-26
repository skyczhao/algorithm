#include <iostream>
#include <stack>
using namespace std;

class Solution
{
  public:
    int calculate(string s)
    {
        stack<int> values;
        stack<char> ops;
        for (int i = 0; i < s.size(); i++)
        {
            if (s[i] == ' ') continue; 

            if (s[i] == '-' || s[i] == '+' || s[i] == '/' || s[i] == '*') {
                while (!ops.empty()) {
                    char op = ops.top();
                    if (op == '/' || op == '*') {
                        int right = values.top();
                        values.pop();
                        int left = values.top();
                        values.pop();

                        if (op == '/') {
                            values.push(left / right);
                        } else {
                            values.push(left * right);
                        }
                    } else {
                        break;
                    }

                    ops.pop();
                }

                ops.push(s[i]);

            // } else if (s[i] == '/' || s[i] == '*') {
            //     ops.push(s[i]);
            // E1: avoid divide zero
            } else {
                int current = s[i] - '0';

                if (values.size() <= ops.size()) {
                    values.push(current);
                } else {
                    int top = values.top();
                    values.pop();
                    values.push(top * 10 + current);
                }
            }

        }

        // E3: avoid tailing / or *
        while (!ops.empty()) {
            char op = ops.top();
            if (op == '/' || op == '*') {
                int right = values.top();
                values.pop();
                int left = values.top();
                values.pop();

                if (op == '/') {
                    values.push(left / right);
                } else {
                    values.push(left * right);
                }
            } else {
                break;
            }

            ops.pop();
        }

        int result = 0;
        while (!ops.empty()) {
            int right = values.top(); // E2: so trick, solve operate from left to right
            values.pop();

            char op = ops.top();
            ops.pop();

            if (op == '+') {
                result = result - right;
            } else if (op == '-') {
                result = result + right;
            }
        }
        int last = values.top();

        return last - result;
    }
};

int main()
{
    Solution s = Solution();

    cout << s.calculate("3+2*2") << endl;
    cout << s.calculate(" 3/2 ") << endl;
    cout << s.calculate(" 3+5 / 2 ") << endl;
    cout << s.calculate("100000000/1/2/3/4/5/6/7/8/9/10") << endl;
    cout << s.calculate("1-1+1") << endl;
    return 0;
}
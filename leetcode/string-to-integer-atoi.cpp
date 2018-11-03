#include <iostream>
using namespace std;

class Solution
{
  public:
    int myAtoi(string str)
    {
        // skip space
        int idx = 0;
        while (idx < str.size())
        {
            char current = str[idx];
            if (current == ' ')
            {
                idx++;
                continue;
            }
            if (!((current >= '0' && current <= '9') || current == '-' || current == '+'))
            {
                return 0;
            }

            break;
        }

        // judge sign
        int sign = 1;
        while (idx < str.size())
        {
            char current = str[idx];
            // cout << str << " " << current << " " << sign << endl;
            // 想太多, 不要求支持多个正负号
            if (current == '-')
            {
                sign = sign * -1;
                idx++;
                // continue;
            }
            if (current == '+')
            {
                idx++;
                // continue;
            }

            break;
        }

        // calculate value
        int result = 0;
        while (idx < str.size())
        {
            char current = str[idx];
            if (!(current >= '0' && current <= '9'))
            {
                break;
            }

            int old = result;
            result = result * 10 + current - '0';
            if (result / 10 != old) {
                if (sign < 0) {
                    return 1<<31;
                } else {
                    return INT_MAX;
                }
            }
            idx++;
        }

        return sign * result;
    }
};

int main()
{

    Solution a = Solution();
    cout << a.myAtoi("42") << endl;
    cout << a.myAtoi("-42") << endl;
    cout << a.myAtoi("+-2") << endl;
    cout << a.myAtoi("4193 with words") << endl;
    cout << a.myAtoi("words and 987") << endl;
    cout << a.myAtoi("-91283472332") << endl;
    cout << a.myAtoi("91283472332") << endl;

    return 0;
}
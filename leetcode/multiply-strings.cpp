#include <iostream>
using namespace std;

// INIT
// 执行用时 :52 ms, 在所有 C++ 提交中击败了15.87%的用户
// 内存消耗 :28 MB, 在所有 C++ 提交中击败了7.35%的用户

// OPT 1:
// 执行用时 :32 ms, 在所有 C++ 提交中击败了21.45%的用户
// 内存消耗 :15.6 MB, 在所有 C++ 提交中击败了11.96%的用户
class Solution {
public:
    string multiply(string num1, string num2) {
        // 选定长度最长的数字
        string left = num1;
        string right = num2;
        if (num2.length() > num1.length()) {
            left = num2;
            right = num1;
        }

        string result = "0";
        string sum = "";

        // 逐位计算
        // OPT 1: 不使用int来计数, 直接用字符串0
        string base = ""; // 需要进位乘10的个数
        for (int idx = right.length() -1 ; idx >= 0; idx--) {
            sum = multiply(left, right[idx] - '0');
            sum = base + sum;

            result = add(result, sum);
            base += "0";
        }

        // 倒转返回
        reverse(result.begin(), result.end());
        return result;
    }

    /**
     * 大数相加: 传入的数据是倒转存储的
     */
    string add(string a, string b) {
        // cout << a << " + " << b << endl;
        // 选定长度最长的数字
        string left = a;
        string right = b;
        int size = a.length();
        if (b.length() > size) {
            left = b;
            right = a;
            size = b.length();
        }

        string result = "";

        // 逐位计算
        int sum = 0;
        int res = 0; // 余数
        int inc = 0; // 进位
        for (int idx = 0; idx < size; idx++) {
            if (idx < right.length()) {
                sum = inc + (left[idx] - '0') + (right[idx] - '0');
            } else {
                sum = inc + (left[idx] - '0');
            }

            res = sum % 10;
            inc = sum / 10;

            result.push_back(res + '0');
        }
        if (inc > 0) {
            result.push_back(inc + '0');
        }

        // 去除头部0
        for (int idx = result.length() - 1; idx > 0; idx--) {
            if (result[idx] == '0') {
                result.pop_back();
            } else {
                break;
            }
        }

        // 为了效率, 中间结果不倒转, // 倒转返回
        // reverse(result.begin(), result.end());
        return result;
    }

    /**
     * 个位数乘大数
     */
    string multiply(string num, int digit) {
        // cout << num << " x " << digit << endl;

        if (digit > 9 || digit < 0) {
            return "ERROR";
        }

        int size = num.length();

        string result = "";

        // 逐位计算
        int sum = 0;
        int res = 0; // 余数
        int inc = 0; // 进位
        for (int idx = size - 1; idx >= 0; idx--) {
            sum = (num[idx] - '0') * digit + inc;
            res = sum % 10;
            inc = sum / 10;

            result.push_back(res + '0');
        }
        if (inc > 0) {
            result.push_back(inc + '0');
        }

        // 去除头部0
        for (int idx = result.length() - 1; idx > 0; idx--) {
            if (result[idx] == '0') {
                result.pop_back();
            } else {
                break;
            }
            
        }

        // 为了效率, 中间结果不倒转, // 倒转返回
        // reverse(result.begin(), result.end());
        return result;
    }
};

int main() {
    Solution s = Solution();
    cout << s.multiply("123", "456") << endl;

    cout << s.multiply("999", 9) << endl;
    cout << s.multiply("0", 0) << endl;

    cout << s.add("9", "999") << endl;
    cout << s.add("99999999", "999") << endl;
    cout << s.add("0", "999") << endl;


    return 0;
}
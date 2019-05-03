#include <iostream>
using namespace std;

class Solution {
public:
    int divide(int dividend, int divisor) {
        // 多种边界条件处理
        if (divisor == 0) {
            return INT_MAX;
        }
        // TIPS: 避免INT_MIN除INT_MIN
        if (dividend == divisor) {
            return 1;
        }
        // TIPS: INT_MIN转为正数会越界
        if (divisor == -1 && dividend == INT_MIN) {
            return INT_MAX;
        }
        // TIPS: 避免被除数为INT_MIN时候越界
        if (divisor == 1) {
            return dividend;
        }
        // TIPS: 除数INT_MIN左移会越界
        if (divisor == INT_MIN) {
            return 0;
        }

        bool flag = true;
        int times = 0;
        // TIPS: 特殊处理, 避免被除数越界
        if (dividend == INT_MIN) {
            if (divisor < 0) {
                dividend -= divisor;
            } else {
                dividend += divisor;
            }

            times = 1;
        }
        // 转为统一正值计算
        // Tips: 负数不可以右移
        if (dividend > 0 && divisor < 0) {
            divisor = 0 - divisor;
            flag = false;
        } else if (dividend < 0 && divisor > 0) {
            dividend = 0 - dividend;
            flag = false;
        } else if (dividend < 0 && divisor < 0) {
            dividend = 0 - dividend;
            divisor = 0 - divisor;
        } 

        // 计算最大接近值及其进位数
        // TIPS: 直接减会超时
        // TIPS: 二进制移位有利于后续迭代, 避免使用pow计算
        int accumulator = 0;
        int temp_divisor = divisor << 1;
        while (temp_divisor <= dividend && temp_divisor > 0) {
            accumulator++;
            temp_divisor = temp_divisor << 1;
        }

        // 递减取代除法
        int curr_divisor = divisor << accumulator;
        int left = dividend - curr_divisor;
        while (left >= 0) {
            times += (1 << accumulator);
            while (left - curr_divisor < 0 && accumulator > 0) {
                accumulator--;
                curr_divisor = divisor << accumulator;
            }
            left -= curr_divisor;
        }

        if (flag) {
            return times;
        }
        return 0 - times;
    }
};

int main() {

    Solution s = Solution();
    cout << s.divide(10, 3) << endl;
    cout << s.divide(7, -3) << endl;
    cout << s.divide(INT_MIN, -1) << endl;
    cout << s.divide(1, 2) << endl;
    cout << s.divide(2147483647, 3) << endl;

    return 0;
}
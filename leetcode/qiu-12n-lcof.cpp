#include <iostream>

using namespace std;

/**
 * 奇技淫巧题
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 * 一般方法的关键: 找出跳出方式
 * 花里胡哨: 俄罗斯农民乘法
 * 
 * java可供利用(try...catch), 但超时
 * int[] a = new int[]{0};
 * try {
 *     return a[n];
 * } catch (Exception e) {
 *     return n + sumNums(n - 1);
 * }
 * 
 * C++:
 * 1. 注意符号计算优先级
 * 2. &&的结果是true/false, 转int就是1/0
 */
class Solution {
public:
    int sumNums(int n) {
        int res = n;
        n && (res += sumNums(n - 1));
        return res;
    }
};

int main() {
    Solution s = Solution();
    cout << s.sumNums(12) << endl;
    cout << s.sumNums(20) << endl;
    cout << s.sumNums(14) << endl;
    cout << s.sumNums(9) << endl;
    cout << s.sumNums(3) << endl;

    return 0;
}
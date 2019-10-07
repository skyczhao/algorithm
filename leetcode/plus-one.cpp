#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :4 ms, 在所有 C++ 提交中击败了87.97%的用户
// 内存消耗 :8.5 MB, 在所有 C++ 提交中击败了39.94%的用户
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        vector<int> result = vector<int>();

        int total = 0;
        int inc = 1;
        int left = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            total = digits[i] + inc;
            
            left = total % 10;
            result.push_back(left);
            inc = total / 10;
        }
        if (inc > 0) {
            result.push_back(inc);
        }
        reverse(result.begin(), result.end());
        return result;
    }
};

void printVec(vector<int> v) {
    for (int i = 0; i < v.size(); i++) {
        cout << v[i] << ", ";
    }
    cout << endl;
}
int main() {
    Solution s = Solution();

    int a_1[] = {1, 2, 3};
    vector<int> v_1 = vector<int>(a_1, a_1 + 3);
    vector<int> r_1 = s.plusOne(v_1);
    printVec(r_1);

    int a_2[] = {9, 9, 9};
    vector<int> v_2 = vector<int>(a_2, a_2 + 3);
    vector<int> r_2 = s.plusOne(v_2);
    printVec(r_2);

    return 0;
}
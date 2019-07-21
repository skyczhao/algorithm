#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :12 ms, 在所有 C++ 提交中击败了73.34%的用户
// 内存消耗 :9 MB, 在所有 C++ 提交中击败了98.74%的用户
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int size = nums.size();

        int sum = nums[0];
        int total = sum;
        for (int i = 1; i < size; i++) {
            if (sum + nums[i] >= nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            if (sum > total) {
                total = sum;
            }
        }

        return total;
    }
};

int main() {
    int a[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    vector<int> vec = vector<int>(a, a + 9);

    Solution s = Solution();
    cout << s.maxSubArray(vec) << endl;
    return 0;
}
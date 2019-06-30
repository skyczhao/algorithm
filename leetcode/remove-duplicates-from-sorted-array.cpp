#include <iostream>
#include <vector>
using namespace std;

// 去重向前压缩
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        // 处理空的情况
        if (nums.empty()) return 0;

        // 还可以通过C++语法压缩代码长度
        int size = nums.size();
        int lastValue = nums[0] - 1;
        int realIdx = -1;
        for (int i = 0; i < size; i++) {
            if (nums[i] != lastValue) {
                realIdx++;
                nums[realIdx] = nums[i];
            }
            lastValue = nums[i];
        }

        return realIdx + 1;
    }
};

// 删除法... 有点慢
// 执行用时 : 260 ms, 在所有 C++ 提交中击败了17.72% 的用户
// 内存消耗 : 9.8 MB, 在所有 C++ 提交中击败了91.93% 的用户
class Solution2 {
public:
    int removeDuplicates(vector<int>& nums) {
        // 处理空的情况
        if (nums.empty()) return 0;

        vector<int>::iterator it = nums.begin();
        
        int lastValue = nums[0] - 1;
        while (it != nums.end()) {
            if (*it == lastValue) {
                lastValue = *it;
                it = nums.erase(it);
            } else {
                lastValue = *it;
                it++;
            }
        }

        return nums.size();
    }
};

int main() {
    int a1[] = {1, 1, 2};
    vector<int> vi1 = vector<int>(a1, a1 + 3);

    int a2[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    vector<int> vi2 = vector<int>(a2, a2 + 10);

    Solution s = Solution();
    cout << s.removeDuplicates(vi1) << endl;
    cout << s.removeDuplicates(vi2) << endl;
    return 0;
}
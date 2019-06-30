#include <iostream>
#include <vector>
using namespace std;

// 收敛式搜索
// 执行用时 : 12 ms, 在所有 C++ 提交中击败了90.79% 的用户
// 内存消耗 : 8.7 MB, 在所有 C++ 提交中击败了78.50% 的用户
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        // OPT 2: 排序
        sort(nums.begin(), nums.end());
        
        int size = nums.size();
        int sum = 0;
        
        // useless
        if (size < 3) {
            if (size > 1) {
                sum += nums[1];
            }
            if (size > 0) {
                sum += nums[0] ;
            } else {
                sum = target;
            }
            return sum;
        }
        sum = nums[0] + nums[1] + nums[2];
        
        int dist = 0;
        for (int i = 0; i < size; i++) {
            int left = target - nums[i];

            // OPT 2: 双索引夹击
            int m = i + 1;
            int n = size - 1;
            while (m < n) {
                dist = left - nums[m] - nums[n];
                if (abs(target - sum) > abs(dist)) {
                    sum = target - dist;
                }

                // 因为排了序，所以可以这么做
                if (dist > 0) {
                    m++;
                } else {
                    n--;
                }
            }
            
        }

        return sum;
    }
};

// 暴力搜索
// 执行用时 : 32 ms, 在所有 C++ 提交中击败了17.29% 的用户
// 内存消耗 : 8.7 MB, 在所有 C++ 提交中击败了80.95% 的用户
class Solution2 {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int size = nums.size();
        int sum = 0;
        
        if (size < 3) {
            if (size > 1) {
                sum += nums[1];
            }
            if (size > 0) {
                sum += nums[0] ;
            } else {
                sum = target;
            }
            return sum;
        }
        sum = nums[0] + nums[1] + nums[2];
        
        int inner_sum = 0;
        int tmp_sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                inner_sum = nums[i] + nums[j];
                for (int m = j + 1; m < size; m++) {
                    tmp_sum = inner_sum + nums[m];
                    if (abs(target - sum) > abs(target - tmp_sum)) {
                        sum = tmp_sum;
                    }
                    // OPT 1: break in time
                    if (sum == target) break;
                }
                if (sum == target) break;
            }
            if (sum == target) break;
        }

        return sum;
    }
};

int main() {
    int num_arr[] = {-1, 2, 1, -4};
    vector<int> input = vector<int>(num_arr, num_arr + 4);
    
    Solution s = Solution();
    cout << s.threeSumClosest(input, 1) << endl;
    return 0;
}
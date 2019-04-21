#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {

        int start = 0;
        int end = nums.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid == start) {
                break;
            }
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        } else {
            return start + 1;
        }
    }
};


int main() {
    Solution s = Solution();
    int arr1[] = {1, 3, 5, 6};
    vector<int> v1 = vector<int>(arr1, arr1 + 4);

    cout << s.searchInsert(v1, 5) << endl;
    cout << s.searchInsert(v1, 2) << endl;
    cout << s.searchInsert(v1, 7) << endl;
    cout << s.searchInsert(v1, 0) << endl;
    return 0;
}
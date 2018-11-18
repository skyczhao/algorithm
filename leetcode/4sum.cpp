#include <iostream>
#include <vector>
using namespace std;

void print_vector(vector<int> arr)
{
    cout << "array: ";
    for (int j = 0; j < arr.size(); j++)
    {
        cout << arr[j] << " ";
    }
    cout << endl;
}

void swap_num(vector<int> &arr, int left, int right) {
    int tmp = arr[left];
    arr[left] = arr[right];
    arr[right] = tmp;
}

void quicksort(vector<int> &arr, int begin, int end) {
    // print_vector(arr);
    // cout << "(" << begin << ", " << end << ")" << endl;
    if (begin >= end) return;

    int move_forward = begin;
    int move_backward = end;
    int ref = begin;
    // 排序边界条件1
    while (move_forward <= move_backward) {
        // cout << "(" << move_forward << ", " << move_backward << ")" << endl;

        // 注意ref值的一直移动, 否则处理不了0, -1, 0的问题
        if (arr[move_forward] <= arr[ref]) {
            swap_num(arr, ref, move_forward);
            ref = move_forward;
            move_forward++;
            continue;
        }
        
        if (arr[move_backward] <= arr[ref]) {
            swap_num(arr, move_forward, move_backward);
        }
        move_backward--;
    }

    // 必须排除ref值, 否则相等的情况会死循环, 无法处理0, 0的问题
    quicksort(arr, begin, ref - 1);
    quicksort(arr, ref + 1, end);

}

class Solution
{
  public:
  
    vector<vector<int>> fourSum(vector<int> &nums, int target) {
        quicksort(nums, 0, nums.size() - 1);
        // print_vector(nums);

        vector<vector<int>> result = vector<vector<int>>();
        for (int i = 0; i < nums.size(); i++) {
            // 跳过重复的!!!
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = nums.size() - 1; j >= i; j--) {
                // 跳过重复的!!!
                if (j < nums.size() - 1 && nums[j] == nums[j + 1]) continue;

                int new_target = target - nums[i] - nums[j];
                if (new_target > nums[j] * 2) continue;
                if (new_target < nums[i] * 2) continue;

                int m = i + 1;
                int n = j - 1;

                // cout << i << ", " << j << endl;
                while (m < n) {
                    // 跳过重复的!!!
                    if (m > i + 1 && nums[m] == nums[m - 1]) {m++; continue;}
                    if (n < j - 1 && nums[n] == nums[n + 1]) {n--; continue;}
                    
                    int s = nums[m] + nums[n];
                    // cout << s << ", " << new_target << " |-| " << m << ", " << n << endl;
                    if (s < new_target) {
                        m++;
                        continue;
                    }
                    // cout << s << ", " << new_target << " |-| " << m << ", " << n << endl;
                    if (s > new_target) {
                        n--;
                        continue;
                    }
                    // cout << s << ", " << new_target << " |-| " << m << ", " << n << endl;

                    int true_arr[] = {nums[i], nums[m], nums[n], nums[j]};
                    result.push_back(vector<int>(true_arr, true_arr + 4));

                    m++;
                    n--;
                }
            }
        }

        return result;
    }
};

int main()
{
    // int arr[] = {1, 0, -1, 0, -2, 2};
    // int arr[] = {2, 1, 0, -1};
    int arr[] = {-5, 5, 4, -3, 0, 0, 4, -2};
    // vector<int> nums = vector<int>(arr, arr + 6);
    // vector<int> nums = vector<int>(arr, arr + 4);
    vector<int> nums = vector<int>(arr, arr + 8);

    // calculate
    Solution s = Solution();
    vector<vector<int>> result = s.fourSum(nums, 0);
    // vector<vector<int>> result = s.fourSum(nums, 2);


    // print result
    for (int i = 0; i < result.size(); i++)
    {
        vector<int> vec = result[i];
        print_vector(vec);
    }
    cout << endl;
    return 0;
}
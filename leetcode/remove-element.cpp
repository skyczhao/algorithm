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

class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int size = 0;
        int lastAvi = nums.size() - 1;
        for (int i = nums.size() - 1; i >= 0; i--) {
            if (nums[i] == val) {
                // 不删除, 用交换代替
                swap_num(nums, i, lastAvi);
                lastAvi--;
            } else {
                size++;
            }
        }
        return size;
    }
};

int main() {
    int arr[] = {0,1,2,2,3,0,4,2};
    vector<int> vec = vector<int>(arr, arr + 8);
    int ret = Solution().removeElement(vec, 2);
    cout << ret << endl;
    print_vector(vec);
    return 0;
}
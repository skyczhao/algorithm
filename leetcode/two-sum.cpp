
#include <iostream>
#include <vector>

using namespace std;

vector<int> twoSum(vector<int> &nums, int target)
{
    vector<int> x;
    for (int i = 0; i < nums.size(); i++) {
        for (int j = i + 1; j < nums.size(); j++) {
            // if (nums[i] != nums[j]) {
                if (target == nums[i] + nums[j]) {
                    x.push_back(i);
                    x.push_back(j);
                    return x;
                }
            // }
        }
    }
    return x;
}

int main()
{
    // int a[5] = {1, 2, 3, 4, 5};
    // vector<int> vi(a, a + 5);

    int a[2] = {3, 3};
    vector<int> vi(a, a + 2);

    vector<int> x = twoSum(vi, 6);
    for (int i = 0; i < x.size(); i++) {
        cout << x[i] << " ";
    }
    cout << endl;
    return 0;
}

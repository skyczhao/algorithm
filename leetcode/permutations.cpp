#include <iostream>
#include <vector>
using namespace std;

class Solution_fail {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        int size = nums.size();
        vector<vector<int>> result = vector<vector<int>>();
        if (size == 1) {
            result.push_back(nums);
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        continue;
                    }

                    // generate result
                    vector<int> line = vector<int>();
                    line.push_back(nums[i]);
                    line.push_back(nums[j]);
                    for (int m = 0; m < size; m++) {
                        if (m == i || m == j) {
                            continue;
                        }
                        line.push_back(nums[m]);
                    }
                    result.push_back(line);
                }
            }
        }
        return result;
    }
};

class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> result = vector<vector<int>>();
        if (nums.size() < 2) {
            result.push_back(nums);
        } else {
            for (int i = 0; i < nums.size(); i++) {
                vector<int> inner_nums = vector<int>();
                for (int j = 0; j < nums.size(); j++) {
                    if (i != j) {
                        inner_nums.push_back(nums[j]);
                    }
                }

                vector<vector<int>> inner_results = permute(inner_nums);
                for (int x = 0; x < inner_results.size(); x++) {
                    inner_results[x].insert(inner_results[x].begin(), nums[i]);
                    result.push_back(inner_results[x]);
                }
            }
        }
        return result;
    }
};

int main() {
    Solution s = Solution();

    int a[] = {1, 2, 3, 4};
    vector<int> vi(a, a + 4);
    vector<vector<int>> ret = s.permute(vi);
    for (int x = 0; x < ret.size(); x++) {
        for (int y = 0; y < ret[x].size(); y++) {
            cout << ret[x][y] << ", ";
        }
        cout << "" << endl;
    }
    return 0;
}
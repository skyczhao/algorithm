#include <iostream>
#include <vector>
using namespace std;

// 执行用时 :24 ms, 在所有 C++ 提交中击败了91.36%的用户
// 内存消耗 :12.2 MB, 在所有 C++ 提交中击败了5.08%的用户

// 执行用时 :20 ms, 在所有 C++ 提交中击败了97.81%的用户
// 内存消耗 :12.1 MB, 在所有 C++ 提交中击败了5.08%的用户

class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        // vector<vector<int>> result = vector<vector<int>>();

        int real_shape = intervals.size();
        int old_shape = 0;
        while (old_shape != real_shape) {
            old_shape = real_shape;

            bool flag = false;
            for (int idx = 0; idx < real_shape; idx++) {
                for (int next = idx + 1; next < real_shape; next++) {
                    flag = false;
                    if (intervals[idx][0] < intervals[next][0]) {
                        if (intervals[idx][1] >= intervals[next][0]) {
                            flag = true;

                            // swap y
                            if (intervals[next][1] > intervals[idx][1]) {
                                intervals[idx][1] = intervals[next][1];
                            }
                        }
                    } else {
                        if (intervals[next][1] >= intervals[idx][0]) {
                            flag = true;

                            // swap x, y
                            intervals[idx][0] = intervals[next][0];
                            if (intervals[next][1] > intervals[idx][1]) {
                                intervals[idx][1] = intervals[next][1];
                            }
                        }
                    }

                    if (flag) {
                        // finish post-swap
                        if (next != real_shape - 1) {
                            intervals[next][0] = intervals[real_shape - 1][0];
                            intervals[next][1] = intervals[real_shape - 1][1];
                            
                        }

                        // ERR 1: (2 -> 1)
                        next--;
                        real_shape--;
                    }
                }
            }

        }

        // for (int idx = 0; idx < real_shape; idx++) {
        //     result.push_back(intervals[idx]);
        // }

        // OPT 1: don't restore value
        int total_size = intervals.size();
        for (int idx = real_shape; idx < total_size; idx++) {
            intervals.pop_back();
        }

        return intervals;
    }
};


void printIntervals(vector<vector<int>> intervals) {
    for (int i = 0; i < intervals.size(); i++) {
        cout << "(";
        for (int j = 0; j < 2; j++) {
            cout << intervals[i][j] << ",";
        }
        cout << "),";
    }
    cout << endl;
}

int main() {
    vector<int> a1 = vector<int>();
    a1.push_back(1);
    a1.push_back(3);

    vector<int> a2 = vector<int>();
    a2.push_back(2);
    a2.push_back(6);

    vector<int> a3 = vector<int>();
    a3.push_back(8);
    a3.push_back(10);

    vector<int> a4 = vector<int>();
    a4.push_back(15);
    a4.push_back(18);

    vector<vector<int>> intervals = vector<vector<int>>();
    intervals.push_back(a1);
    intervals.push_back(a2);
    intervals.push_back(a3);
    intervals.push_back(a4);

    printIntervals(intervals);

    Solution s = Solution();
    vector<vector<int>> result = s.merge(intervals);

    printIntervals(result);

    return 0;
}
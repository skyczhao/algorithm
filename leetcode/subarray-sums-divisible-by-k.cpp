#include <iostream>
#include <vector>
using namespace std;

/**
 * 数学题, 转化为排列组合
 * https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 */
class Solution1 {
public:
    int subarraysDivByK(vector<int>& A, int K) {
        int length = A.size();
        int times = 0;
        for (int i = 0; i < length; i++) {
            int subTotal = 0;
            for (int j = i; j < length; j++) {
                subTotal += A[j];

                if (subTotal % K == 0) {
                    times++;
                }
            }
        }
        return times;
    }
};

class Solution {
public:
    int subarraysDivByK(vector<int>& A, int K) {
        int length = A.size();

        int *interSum = new int[length + 1];
        // accumulate
        interSum[0] = 0;
        for (int i = 1; i <= length; i++) {
            interSum[i] = interSum[i - 1] + A[i - 1];
        }
        // divide left
        for (int i = 0; i <= length; i++) {
            interSum[i] = interSum[i] % K;
            if (interSum[i] < 0) {
                interSum[i] += K;
            }
        }
        // count same
        int *count = new int[K];
        for (int i = 0; i < K; i++) {
            count[i] = 0;
        }
        for (int i = 0; i <= length; i++) {
            count[interSum[i]]++;
        }

        // combination
        int times = 0;
        for (int i = 0; i < K; i++) {
            if (count[i] > 0) {
                times += (count[i] * (count[i] - 1) / 2);
            }
        }

        delete [] count;
        delete [] interSum;
        return times;
    }
};

int main() {
    int arr[] = {4,5,0,-2,-3,1};
    vector<int> vec = vector<int>(arr, arr + 6);
    int k = 5;

    Solution s = Solution();
    cout <<  s.subarraysDivByK(vec, k) << endl;
    return 0;
}
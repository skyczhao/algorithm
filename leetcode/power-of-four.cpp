#include <iostream>

using namespace std;

class Solution {
public:
    bool isPowerOfFour(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        if (num % 2 != 0) return false;

        int left = num;
        while (left > 1) {
            if (left % 4 > 0) return false;

            left = left / 4;
        }
        return true;
    }
};

int main() {
    Solution x;
    cout << x.isPowerOfFour(2) << endl;
    cout << x.isPowerOfFour(3) << endl;
    cout << x.isPowerOfFour(4) << endl;
    cout << x.isPowerOfFour(5) << endl;
    return 0;
}





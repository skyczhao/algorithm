#include <iostream>
using namespace std;

class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int digit = 1;
        long base = 10; // bug: over int
        while (x >= base) {
            digit ++;
            base *= 10;
        }

        int times = digit / 2;
        base /= 10;
        int left = 0;
        int right = 0;
        // cout << digit << "-" << times << endl;
        for (int i = 0; i < times; i++) {
            // cout << x << " : " << base << endl;
            left = x / base; // keep the head as left
            right = x % 10;  // keep the last as right

            if (left != right) {
                return false;
            }
            x = x % base;    // remove head
            x = x / 10;      // remove tail

            base /= 100;
        }

        return true;
    }
};

int main() {
    Solution s = Solution();

    cout << s.isPalindrome(1) << endl;
    cout << s.isPalindrome(12) << endl;
    cout << s.isPalindrome(121) << endl;
    cout << s.isPalindrome(12321) << endl;
    cout << s.isPalindrome(123421) << endl;
    cout << s.isPalindrome(1111111) << endl;

    return 0;
}
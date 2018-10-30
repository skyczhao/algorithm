#include <iostream>

using namespace std;

class Solution {
public:
    int reverse(int x) {
        int sign = 1;
        int origin = x;
        if (x < 0) {
            sign = -1;
            origin = -x;
        }

        int result = 0;
        int old = 0;
        while (origin > 0) {
            int tail = origin % 10;
            origin = origin / 10;

            old = result;
            result = result * 10 + tail;

            // 判断越界的方式?
            if (result / 10 != old) {
                result = 0;
                break;
            }
        }

        return sign * result;
    }
};

int main() {
    Solution a;
    cout << a.reverse(120) << endl;
    cout << a.reverse(-123) << endl;
    cout << a.reverse(1534236469) << endl;
    return 0;
}

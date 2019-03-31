#include <iostream>

using namespace std;

class Solution {
public:
    int romanToInt(string s) {
        int size = s.size();
        if (size < 1) return 0;

        int sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            int value = 0;
            switch(s[i]){
                case 'I':
                    value = 1;
                    break;
                case 'V':
                    value = 5;

                    // 'IV'
                    if (i - 1 >= 0 && s[i - 1] == 'I') {
                        value = 4;
                        i--;
                    }
                    break;
                case 'X':
                    value = 10;

                    // 'IX'
                    if (i - 1 >= 0 && s[i - 1] == 'I') {
                        value = 9;
                        i--;
                    }
                    break;
                case 'L':
                    value = 50;

                    // 'XL'
                    if (i - 1 >= 0 && s[i - 1] == 'X') {
                        value = 40;
                        i--;
                    }
                    break;
                case 'C':
                    value = 100;

                    // 'XC'
                    if (i - 1 >= 0 && s[i - 1] == 'X') {
                        value = 90;
                        i--;
                    }
                    break;
                case 'D':
                    value = 500;

                    // 'CD'
                    if (i - 1 >= 0 && s[i - 1] == 'C') {
                        value = 400;
                        i--;
                    }
                    break;
                case 'M':
                    value = 1000;

                    // 'CM'
                    if (i - 1 >= 0 && s[i - 1] == 'C') {
                        value = 900;
                        i--;
                    }
                    break;
            }

            sum += value;
            // cout << value << endl;
        }
        return sum;        
    }
};

int main() {
    Solution s = Solution();
    cout << s.romanToInt("III") << endl;
    cout << s.romanToInt("IV") << endl;
    cout << s.romanToInt("IX") << endl;
    cout << s.romanToInt("LVIII") << endl;
    cout << s.romanToInt("MCMXCIV") << endl;
    return 0;
}
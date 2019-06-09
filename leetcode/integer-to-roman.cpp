#include <iostream>
using namespace std;

class Solution {
public:
    string intToRoman(int num) {
        string result = "";

        while (num > 0) {
            // 坑1: 相等的情况
            if (num >= 1000) {
                num -= 1000;
                result += "M";
            } else if (num >= 900) {
                num -= 900;
                // 坑2: 位置写反了
                result += "CM";
            } else if (num >= 500) {
                num -= 500;
                result += "D";
            } else if (num >= 400) {
                num -= 400;
                result += "CD";
            } else if (num >= 100) {
                num -= 100;
                result += "C";
            } else if (num >= 90) {
                num -= 90;
                result += "XC";
            } else if (num >= 50) {
                num -= 50;
                result += "L";
            } else if (num >= 40) {
                num -= 40;
                result += "XL";
            } else if (num >= 10) {
                num -= 10;
                result += "X";
            } else if (num >= 9) {
                num -= 9;
                result += "IX";
            } else if (num >= 5) {
                num -= 5;
                result += "V";
            } else if (num >= 4) {
                num -= 4;
                result += "IV";
            } else {
                num -= 1;
                result += "I";
            }
        }

        return result;
    }
};

int main() {

    Solution s = Solution();
    cout << s.intToRoman(3) << endl;
    cout << s.intToRoman(4) << endl;
    cout << s.intToRoman(500) << endl;
    cout << s.intToRoman(3999) << endl;
    cout << s.intToRoman(999) << endl;
    cout << s.intToRoman(199) << endl;

    return 0;
}
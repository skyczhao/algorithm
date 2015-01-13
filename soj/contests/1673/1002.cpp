
#include <iostream>
using namespace std;

int number[100005];

string int2str(int num)
{
    string result;
    while(num != 0) {
        result.push_back(num % 10 + '0');
        num /= 10;
    }
    return result;
}

string strAdd(string a, string b)
{
    string tmp;
    if (a.size() > b.size()) {
        tmp = a;
        a = b;
        b = tmp;
    }
    int min_length = a.size();
    int max_length = b.size();

    string result;
    int i, sum, inc;
    inc = 0;
    for (i = 0; i < min_length; i++) {
        sum = a[i] + b[i] + inc - 2 * '0';
        result.push_back(sum % 10 + '0');
        inc = sum / 10;
    }
    for (; i < max_length; i++) {
        sum = b[i] + inc - '0';
        result.push_back(sum % 10 + '0');
        inc = sum / 10;
    }
    if (inc != 0)
        result.push_back(inc + '0');

    return result;
}

string strMul(string str, int num)
{
    int length = str.size();
    string result;
    int sum, inc;
    inc = 0;
    for (int i = 0; i < length; i++) {
        sum = (str[i] - '0') * num + inc;
        result.push_back(sum % 10 + '0');
        inc = sum / 10;
    }
    while (inc != 0) {
        result.push_back(inc % 10 + '0');
        inc = inc / 10;
    }
    return result;
}

int main()
{
    int size;
    int i, j, fi, gi;
    cin >> size;
    for (i = 0; i < size; i++) {
        cin >> number[i];
    }

    string sum;
    for (i = 0; i < size; i++) {
        fi = i;
        for (j = i - 1; j >= 0; j--) {
            if (number[j] % number[i] == 0) {
                fi = j;
                break;
            }
        }
        gi = i;
        for (j = i + 1; j < size; j++) {
            if (number[j] % number[i] == 0) {
                gi = j;
                break;
            }
        }
        string mul = strMul(int2str(number[fi]), number[gi]);
        sum = strAdd(sum, mul);
    }
    for (i = sum.size(); i > 0; i--)
        if (sum[i - 1] != '0')
            break;
    if (i > 0) {
        for (; i > 0; i--) {
            cout << sum[i - 1];
        }
        cout << endl;
    } else {
        cout << "0" << endl;
    }
    return 0;
}

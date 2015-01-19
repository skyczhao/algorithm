
#include <iostream>
using namespace std;

int main()
{
    string x, y;
    int i;
    bool flag;

    cin >> x;
    y = x;
    if (x[0] == '-') {
        for (i = 1; i < x.size(); i++)
            y[i] = x[x.size() - i];
    } else {
        for (i = 0; i < x.size(); i++)
            y[i] = x[x.size() - i - 1];
    }

    flag = false;
    for (i = 0; i < y.size(); i++) {
        if (y[i] == '-') {
            cout << y[i];
        } else if(flag) {
            cout << y[i];
        } else if(y[i] != '0') {
            cout << y[i];
            flag = true;
        }
    }
    cout << endl;
    return 0;
}

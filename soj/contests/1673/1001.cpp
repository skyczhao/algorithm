
#include <iostream>
#include <string>
#include <cstring>
using namespace std;

int main()
{
    string a, b;
    int times[26][2];
    int i;

    memset(times, 0, sizeof(times));

    cin >> a >> b;

    if (a.size() == b.size()) {
        for (i = 0; i < a.size(); i++) {
            times[a[i] - 'a'][0]++;
            times[b[i] - 'a'][1]++;
        }

        bool flag = true;
        for (i = 0; i < 26; i++) {
            if (times[i][0] != times[i][1]) {
                flag = false;
                break;
            }
        }
        if (flag)
            cout << "YES" << endl;
        else
            cout << "NO" << endl;
    } else {
        cout << "NO" << endl;
    }
    return 0;
}

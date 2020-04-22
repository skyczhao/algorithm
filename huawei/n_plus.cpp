#include <iostream>
using namespace std;

int main() {

    int n;
    int digit[10002] = {1};
    int size = 0;
    while (cin >> n) {
        for (int i = 0; i < 10000; i++) {
            digit[i] = 0;
        }
        digit[0] = 1;
        size = 1;

        for (int i = 2; i <= n; i++) {
            int next = 0;
            for (int j = 0; j < size; j++) {
                int sum = i * digit[j] + next;

                digit[j] = sum % 10000;
                next = sum / 10000;
            }
            if (next > 0) {
                digit[size] = next;
                size++;
            }
        }

        cout << digit[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            if (digit[i] < 1000) {
                cout << 0;
                if (digit[i] < 100) {
                    cout << 0;
                    if (digit[i] < 10) {
                        cout << 0;
                    }
                }
            }
            cout << digit[i];
        }
        cout << endl;
    }

    return 0;
}
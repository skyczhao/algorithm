
#include <iostream>
using namespace std;

int list[1000005];
int size;

void split(int num, int next)
{
    int i, old_size;
    if(num == 0){
        size++;
        return;
    }

    for(i = 1; i <= num; i *= 2){
        if(next == 0 || i <= list[next - 1]){
            list[next] = i;
            split(num - i, next + 1);
        }
    }
}

int main()
{
    int num;
    while(cin >> num){
        size = 0;
        split(num, 0);
        cout << size << endl;
    }
    return 0;
}

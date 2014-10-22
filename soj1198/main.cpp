
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

bool smaller(string x, string y)
{
    return (x + y < y + x);
}

int main()
{
    int size, n, i, j;
    string str[10];
    string tmp;

    scanf("%d", &size);
    while(size--){
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            cin >> str[i];
        for(i = 0; i < n; i++)
            for(j = i + 1; j < n; j++)
                if(smaller(str[j], str[i])){
                    tmp = str[j];
                    str[j] = str[i];
                    str[i] = tmp;
                }
        for(i = 0; i < n; i++)
            cout << str[i];
        printf("\n");
    }
    return 0;
}



#include <stdio.h>
#include <string.h>

char num[105], sum[105];

void add(const int &length, const int &size)
{
    int i, j, inc, res;
    i = length - 1;
    j = size - 1;
    inc = 0;
    for(; i >= 0; i--, j--){
        res = num[i] - '0' + sum[j] - '0' + inc;
        inc = res / 10;
        sum[j] = res % 10 + '0';
    }
    for(; j >= 0; j--){
        res = sum[j] - '0' + inc;
        inc = res / 10;
        sum[j] = res % 10 + '0';
    }
}

int main()
{
    int i, size, length;
    bool flag;
    size = sizeof(sum);
    for(i = 0; i < size; i++)
        sum[i] = '0';
    while(1){
        scanf(" %s", num);
        length = strlen(num);
        flag = true;
        for(i = 0; i < length; i++)
            if(num[i] != '0'){
                flag = false;
                break;
            }
        if(flag)
            break;
        add(length, size);
    }
    for(i = 0; i < size - 1; i++)
        if(sum[i] != '0')
            break;
    for(; i < size; i++)
        printf("%c", sum[i]);
    printf("\n");
    return 0;
}

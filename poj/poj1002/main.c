
#include <stdio.h>
#include <string.h>

int maps[] = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9};
int res[10000000];

int main()
{
    int size, i, num;
    bool flag;
    char c;
    memset(res, 0, 10000000 * sizeof(int));
    scanf("%d", &size);
    c = getchar();
    for(i = 0; i < size; i++){
        num = 0;
        c = getchar();
        while(c != '\n'){
            if(c != '-'){
                if(c >= '0' && c <= '9')
                    num = num * 10 + c - '0';
                else
                    num = num * 10 + maps[c -'A'];
            }
            c = getchar();
        }
        res[num]++;
    }
    flag = true;
    for(i = 0; i < 10000000; i++){
        if(res[i] > 1){
            printf("%03d-%04d %d\n", i / 10000, i % 10000, res[i]);
            flag = false;
        }
    }
    if(flag)
        printf("No duplicates.\n");
    return 0;
}

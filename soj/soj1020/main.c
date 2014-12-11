
#include <stdio.h>
#include <string.h>
int num[102];
char x[402];

int findleft(int div)
{
    int length = strlen(x);
    int tmp = 0;
    int i;
    for(i = 0; i < length; i++){
        tmp = tmp * 10 + x[i] - '0';
        tmp = tmp % div;
    }
    return tmp;
}

int main()
{
    int mark, size, i;
    scanf("%d", &mark);
    while(mark--){
        scanf("%d", &size);
        for(i = 0; i < size; i++)
            scanf("%d", &num[i]);
        scanf("%s", x);

        printf("(");
        for(i = 0; i < size; i++){
            if(i != 0)
                printf(",");
            printf("%d", findleft(num[i]));
        }
        printf(")\n");
    }
    return 0;
}


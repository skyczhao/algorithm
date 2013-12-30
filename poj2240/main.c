
#include <stdio.h>
#include <string.h>

char name[35][40];
float rates[35][35];
float money[35];

int main()
{
    int mark;
    int size, i, exc, j, k;
    float rate;
    char start[40], end[40];
    int s_i, e_i;
    mark = 0;
    while(scanf("%d", &size) != EOF){
        if(size == 0)
            break;
        mark++;
        for(i = 0; i < size; i++)
            scanf("%s", name[i]);
        scanf("%d", &exc);
        memset(rates, 0, sizeof(rates));
        for(i = 0; i < exc; i++){
            scanf("%s %f %s", start, &rate, end);
            for(j = 0; j < size; j++){
                if(strcmp(name[j], start) == 0)
                    s_i = j;
                if(strcmp(name[j], end) == 0)
                    e_i = j;
            }
            rates[s_i][e_i] = rate;
        }

        memset(money, 0, sizeof(money));
        money[0] = 10;
        for(k = 0; k < size; k++)
            for(i = 0; i < size; i++)
                for(j = 0; j < size; j++)
                    if(rates[i][j] != 0)
                        if(money[i] * rates[i][j] > money[j])
                            money[j] = money[i] * rates[i][j];

        bool flag = false;
        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++)
                if(money[i] * rates[i][j] > money[j]){
                    flag = true;
                    break;
                }
            if(flag)
                break;
        }
        if(flag)
            printf("Case %d: Yes\n", mark);
        else
            printf("Case %d: No\n", mark);
    }
    return 0;
}


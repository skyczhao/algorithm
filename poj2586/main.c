
#include <stdio.h>
int rate[6][2] = {5, 0, 4, 1, 3, 2, 2, 3, 1, 4, 0, 5};
int left[6][2] = {2, 0, 2, 0, 2, 0, 2, 0, 1, 1, 0, 2};
int main()
{
    int s, d, i, sum;
    while(scanf("%d %d", &s, &d) != EOF){
        for(i = 0; i < 6; i++)
            if(rate[i][0] * s < rate[i][1] * d)
                break;
        sum = rate[i][0] * s - rate[i][1] * d;
        sum *= 2;
        sum = sum + left[i][0] * s - left[i][1] * d;
        if(sum > 0)
            printf("%d\n", sum);
        else
            printf("Deficit\n");
    }
    return 0;
}


#include <stdio.h>
#include <string.h>

int main()
{
    int exams, size;
    int i, j, temp;
    int a, b;
    int times[200];
    scanf("%d", &exams);
    while(exams--){
        memset(times, 0, sizeof(times));
        // read input
        scanf("%d", &size);
        for(i = 0; i < size; i++){
            scanf("%d %d", &a, &b);
            if(a > b){
                temp = a;
                a = b;
                b = temp;
            }
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            for(j = a - 1; j < b; j++)
                times[j]++;
        }
        temp = times[0];
        for(i = 0; i < 200; i++)
            if(temp < times[i])
                temp = times[i];
        printf("%d0\n", temp);
    }
    return 0;
}

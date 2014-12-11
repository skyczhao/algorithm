
#include <stdio.h>
#include <string.h>

int num[40][40];
int size;
long long sum[40][40];

int main()
{
    int i, j, length;
    char str[40];
    while(scanf("%d", &size) != EOF){
        if(size == -1)
            break;
        memset(sum, 0, sizeof(sum));
        for(i = 0; i < size; i++){
            scanf("%s", str);
            length = strlen(str);
            for(j = 0; j < length; j++)
                num[i][j] = str[j] - '0';
        }
        sum[0][0] = 1;
        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++){
                if(num[i][j] != 0){
                    if(i + num[i][j] < size)
                        sum[i + num[i][j]][j] += sum[i][j];
                    if(j + num[i][j] < size)
                        sum[i][j + num[i][j]] += sum[i][j];
                }
            }
        }
        printf("%lld\n", sum[size - 1][size - 1]);
    }
    return 0;
}

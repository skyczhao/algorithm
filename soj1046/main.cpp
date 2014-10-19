
#include <stdio.h>
#include <string.h>

int number[302];
int candy[45200][3];
int max[3], idx;

bool islarger(int *x, int *y)
{
    double x_d = x[1] - x[0] + 1;
    double y_d = y[1] - y[0] + 1;

    if(x[2] / x_d > y[2] / y_d)
        return true;
    if(x[2] / x_d < y[2] / y_d)
        return false;

    if(x_d > y_d)
        return true;
    if(x_d < y_d)
        return false;

    if(x[1] < y[1])
        return true;
    else
        return false;
}

int main()
{
    int size, size_i;
    int qua_num, out_num, min_qua_num;
    int i, base, m, accu, x;

    scanf("%d", &size);
    for(size_i = 0; size_i < size; size_i++){
        base = 0;
        x = 0;

        scanf("%d %d %d", &qua_num, &out_num, &min_qua_num);
        for(i = 0; i < qua_num; i++){
            scanf("%d", &number[i]);
            base += number[i];
            if(i >= min_qua_num - 1){
                base -= number[i - min_qua_num + 1];
                accu = base;
                for(m = i - min_qua_num + 1; m >= 0; m--){
                    accu += number[m];
                    candy[x][0] = m;
                    candy[x][1] = i;
                    candy[x][2] = accu;
                    x++;
                }
            }
        }

        printf("Result for run %d:\n", size_i + 1);
        if(x < out_num)
            out_num = x;
        for(i = 0; i < out_num; i++){
            idx = -1;
            max[0] = 0;
            max[1] = 1;
            max[2] = -1;
            for(m = 0; m < x; m++)
                if(islarger(candy[m], max)){
                    idx = m;
                    memcpy(max, candy[m], sizeof(max));
                }
            candy[idx][2] = -1;
            printf("%d-%d\n", max[0] + 1, max[1] + 1);
        }
    }
    return 0;
}


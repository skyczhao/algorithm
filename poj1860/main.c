
#include <stdio.h>
#include <string.h>

struct exchange{
    int a;
    int b;
    float abr;
    float abc;
    float bar;
    float bac;
};
exchange bank[102];

int cent, size, type;
float sum;
float max[102];

int main()
{
    int i, j;
    float next;
    memset(max, 0, 102 * sizeof(float));
    scanf("%d %d %d %f", &cent, &size, &type, &sum);
    for(i = 0; i < size; i++)
        scanf("%d %d %f %f %f %f", &bank[i].a, &bank[i].b, &bank[i].abr, &bank[i].abc, &bank[i].bar, &bank[i].bac);

    max[type] = sum;
    for(i = 0; i < 10000; i++){
        if(max[type] > sum)
            break;
        for(j = 0; j < size; j++){
            next = (max[bank[j].a] - bank[j].abc) * bank[j].abr;
            if(next > max[bank[j].b])
                max[bank[j].b] = next;
            next = (max[bank[j].b] - bank[j].bac) * bank[j].bar;
            if(next > max[bank[j].a])
                max[bank[j].a] = next;

            //for(int jt = 1; jt <= cent; jt++)
            //    printf("%f ", max[jt]);
            //printf("\n");
        }
    }
    if(max[type] > sum)
        printf("YES\n");
    else
        printf("NO\n");
    return 0;
}


#include <stdio.h>
#include <string.h>

int MAX;
int costs[105][105];

int main()
{
    int size, i, cont, j, c, k;
    int min, index, max;
    memset(&MAX, 127, sizeof(int));
    while(scanf("%d", &size) != EOF){
        if(size == 0)
            break;
        memset(costs, 127, sizeof(costs));
        for(i = 0; i < size; i++){
            scanf("%d", &cont);
            while(cont--){
                scanf("%d %d", &j, &c);
                costs[i][j - 1] = c;
            }
            costs[i][i] = 0;
        }

        // floyd
        for(k = 0; k < size; k++)
            for(i = 0; i < size; i++)
                for(j = 0; j < size; j++){
                    if(costs[i][k] == MAX || costs[k][j] == MAX)
                        continue;
                    if(costs[i][k] + costs[k][j] < costs[i][j])
                        costs[i][j] = costs[i][k] + costs[k][j];
                }

        // find
        index = -1;
        min = MAX;
        for(i = 0; i < size; i++){
            max = 0;
            for(j = 0; j < size; j++)
                if(costs[i][j] > max)
                    max = costs[i][j];
            if(max < min){
                min = max;
                index = i;
            }
        }
        if(min != MAX)
            printf("%d %d\n", index + 1, min);
        else
            printf("disjoint\n");
    }
    return 0;
}

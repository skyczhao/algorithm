
#include <stdio.h>

int dist[505][505];
int edge[505];

int main()
{
    int mark, size;
    int i, j;
    int now, next, min, max;
    scanf("%d", &mark);
    while(mark--){
        scanf("%d", &size);
        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++)
                scanf("%d", &dist[i][j]);
            edge[i] = 70000;
        }

        edge[0] = -1;
        now = 0;
        max = -1;
        for(i = 1; i < size; i++){
            min = 70000;
            for(j = 0; j < size; j++){
                if(j != now && edge[j] >= 0){
                    if(dist[now][j] < edge[j])
                        edge[j] = dist[now][j];
                    if(edge[j] < min){
                        min = edge[j];
                        next = j;
                    }
                }
            }
            now = next;
            edge[now] = -1;
            if(max < min)
                max = min;
        }
        printf("%d\n", max);
    }
    return 0;
}

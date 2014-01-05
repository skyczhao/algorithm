
#include <stdio.h>
#include <string.h>

char str[2005][10];
int dist[2005][2005];
int edge[2005];

int cal_dist(const char a[10], const char b[10])
{
    int mark = 0;
    for(int i = 0; i < 7; i++)
        if(a[i] != b[i])
            mark++;
    return mark;
}

int main()
{
    int size, i, j;
    int now, next, min, costs;
    while(scanf("%d", &size) != EOF){
        if(size == 0)
            break;
        for(i = 0; i < size; i++){
            scanf("%s", str[i]);
            for(j = 0; j <= i; j++){
                dist[i][j] = cal_dist(str[i], str[j]);
                dist[j][i] = dist[i][j];
            }
            edge[i] = 10;
        }

        edge[0] = -1;
        now = 0;
        costs = 0;
        for(i = 1; i < size; i++){
            min = 10;
            // for now
            for(j = 0; j < size; j++){
                if(edge[j] >= 0){
                    if(edge[j] > dist[now][j])
                        edge[j] = dist[now][j];
                    if(edge[j] < min){
                        min = edge[j];
                        next = j;
                    }
                }
            }
            costs += min;
            now = next;
            edge[now] = -1;
        }
        printf("The highest possible quality is 1/%d.\n", costs);
    }
    return 0;
}


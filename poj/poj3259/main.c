
#include <stdio.h>
#include <string.h>

struct cost_node{
    int s;
    int e;
    int c;
};

int f_size, p_size, h_size;
cost_node costs[6000];
int dist[505];

bool bellmanford(int source)
{
    int MAX, i, j;
    memset(dist, 1, 505 * sizeof(int));
    memset(&MAX, 1, sizeof(int));
    dist[source] = 0;
    for(i = 0; i < f_size; i++){
        // 松弛
        for(j = 0; j < 2 * p_size + h_size; j++){
            if(dist[costs[j].s] + costs[j].c < dist[costs[j].e])
                dist[costs[j].e] = dist[costs[j].s] + costs[j].c;
        }
    }
    // 检查
    for(i = 0; i < 2 * p_size + h_size; i++)
        if(dist[costs[i].s] + costs[i].c < dist[costs[i].e])
            return false;
    return true;
}

int main()
{
    int size, i;
    bool flag;
    scanf("%d", &size);
    while(size--){
        scanf("%d %d %d", &f_size, &p_size, &h_size);
        for(i = 0; i < p_size; i++){
            scanf("%d %d %d", &costs[i].s, &costs[i].e, &costs[i].c);
            costs[i].s--;
            costs[i].e--;
            costs[p_size + i].s = costs[i].e;
            costs[p_size + i].e = costs[i].s;
            costs[p_size + i].c = costs[i].c;
        }
        for(i = 0; i < h_size; i++){
            scanf("%d %d %d", &costs[2 * p_size + i].s, &costs[2 * p_size + i].e, &costs[2 * p_size + i].c);
            costs[2 * p_size + i].s--;
            costs[2 * p_size + i].e--;
            costs[2 * p_size + i].c = 0 - costs[2 * p_size + i].c;
        }
        //for(i = 0; i < 2 * p_size + h_size; i++)
        //    printf("%d %d %d\n", costs[i].s, costs[i].e, costs[i].c);

        flag = true;
        //for(i = 0; i < f_size; i++)
        if(!bellmanford(0)){
            flag = false;
            //break;
        }
        if(flag)
            printf("NO\n");
        else
            printf("YES\n");
    }
    return 0;
}

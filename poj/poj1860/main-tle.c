
#include <stdio.h>
#include <string.h>
#include <queue>

struct node{
    int type;
    float sum;
    int time;
    node(){
        type = 0;
        sum = 0;
        time = 0;
    }
    node(int t, float s, int ti){
        type = t;
        sum = s;
        time = ti;
    }
};
std::queue<node> path;

struct excp{
    int A;
    int B;
    float ab[2];
    float ba[2];
};
excp exch[102];

float max[102];

int N, M, S;
float V;

int main()
{
    int i;
    bool flag;
    node curr, next;
    memset(max, 0, 102 * sizeof(float));
    scanf("%d %d %d %f", &N, &M, &S, &V);
    for(i = 0; i < M; i++)
        scanf("%d %d %f %f %f %f", &exch[i].A, &exch[i].B, &exch[i].ab[0], &exch[i].ab[1], &exch[i].ba[0], &exch[i].ba[1]);

    flag = false;
    path.push(node(S, V, 0));
    max[S] = V;
    while(!path.empty()){
        curr = path.front();
        path.pop();
        if(curr.type == S && curr.sum > V){
            flag = true;
            break;
        }
        if(curr.time > 10000){
            break;
        }

        for(i = 0; i < M; i++){
            if(exch[i].A == curr.type){
                next.type = exch[i].B;
                next.sum = (curr.sum - exch[i].ab[1]) * exch[i].ab[0];
                next.time = curr.time + 1;
                if(max[next.type] < next.sum)
                    path.push(next);
                continue;
            }
            if(exch[i].B == curr.type){
                next.type = exch[i].A;
                next.sum = (curr.sum - exch[i].ba[1]) * exch[i].ba[0];
                next.time = curr.time + 1;
                if(max[next.type] < next.sum)
                    path.push(next);
                continue;
            }
        }
    }
    if(flag)
        printf("YES\n");
    else
        printf("NO\n");

    return 0;
}


#include <stdio.h>
#include <string.h>

int size, ways, holes;
int costs[505][505];
int worm[505][505];

int path[505][505];
int back[505][505];

int main()
{
    int mark, i, j, m;
    int s, t, c;
    bool flag;
    scanf("%d", &mark);
    while(mark--){
        scanf("%d %d %d", &size, &ways, &holes);
        memset(costs, 1, sizeof(costs));
        memset(path, 1, sizeof(path));
        memset(worm, 0, sizeof(worm));
        memset(back, 0, sizeof(back));
        for(i = 0; i < size; i++){
            path[i][i] = 0;
            back[i][i] = 0;
        }
        for(i = 0; i < ways; i++){
            scanf("%d %d %d", &s, &t, &c);
            s--;
            t--;
            costs[s][t] = c;
            costs[t][s] = c;
        }
        for(i = 0; i < holes; i++){
            scanf("%d %d %d", &s, &t, &c);
            s--;
            t--;
            worm[s][t] = c;
            back[s][t] = c;
        }

        flag = true;
        while(flag){
            flag = false;
            for(i = 0; i < size; i++){
                for(j = 0; j < size; j++){
                    for(m = 0; m < size; m++){
                        if(path[i][j] + costs[j][m] < path[i][m]){
                            flag = true;
                            path[i][m] = path[i][j] + costs[j][m];
                        }
                    }// cols costs
                }// cols
            }// rows
        }

        flag = true;
        while(flag){
            flag = false;
            for(i = 0; i < size; i++){
                for(j = 0; j < size; j++){
                    if(back[i][j] != 0){
                        for(m = 0; m < size; m++){
                            if(worm[j][m] != 0){
                                int temp = back[i][j] + worm[j][m];
                                if(temp < 1000005 && temp > back[i][m]){
                                    flag = true;
                                    back[i][m] = temp;
                                }
                            }
                        }// col costs
                    }
                }// cols
            }// rows
        }

        flag = false;
        for(i = 0; i < size && !flag; i++)
            for(j = 0; j < size && !flag; j++)
                if(path[i][j] < 10005 && back[i][j] > path[i][j])
                    flag = true;
        if(flag)
            printf("YES\n");
        else
            printf("NO\n");
    }
    return 0;
}

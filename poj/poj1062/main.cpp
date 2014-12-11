
#include <iostream>
#include <cstring>
using namespace std;

int MAX;
int MAX_GAP, size;
int value[102];
int level[102];
int costs[102][102];

int res[102];
int flag[102];

void dijkstra(int gap_down, int gap_up)
{
    //cout << gap_down << " " << gap_up << endl;
    int i, j;
    int min, index;
    memset(res, 127, sizeof(res));
    for(i = 0; i < size; i++)
        if(level[i] >= gap_down && level[i] <= gap_up)
            res[i] = value[i];

    memset(flag, 0, sizeof(flag));
    while(1){
        min = MAX;
        index = -1;
        // find
        for(i = 0; i < size; i++){
            if(!flag[i] && res[i] < min){
                min = res[i];
                index = i;
            }
        }
        if(index == -1)
            break;
        // update
        flag[index] = true;
        for(i = 0; i < size; i++)
            if(res[index] + costs[i][index] < res[i])
                if(level[i] >= gap_down && level[i] <= gap_up)
                    res[i] = res[index] + costs[i][index];
        //for(i = 0; i < size; i++)
        //    cout << res[i] << " ";
        //cout << endl;
    }
}

int main()
{
    int i, j, k, son;
    int c, cost;
    int min;
    cin >> MAX_GAP >> size;
    memset(&costs, 127, sizeof(costs));
    memset(&MAX, 127, sizeof(MAX));
    for(i = 0; i < size; i++){
        cin >> value[i] >> level[i] >> son;
        for(j = 0; j < son; j++){
            cin >> c >> cost;
            costs[i][c - 1] = cost;
        }
    }

    min = MAX;
    for(i = 0; i <= MAX_GAP; i++){
        dijkstra(level[0] - MAX_GAP + i, level[0] + i);
        if(min > res[0])
            min = res[0];
    }
    cout << min << endl;
    return 0;
}

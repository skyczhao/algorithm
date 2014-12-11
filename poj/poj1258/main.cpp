
#include <iostream>
using namespace std;

#define MAX 999999999

int dist[105][105];
int cost[105];

int main()
{
    int size, i, j;
    int now, min, next, all;
    while(cin >> size){
        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++)
                cin >> dist[i][j];
            cost[i] = MAX;
        }

        cost[0] = -1;
        now = 0;
        all = 0;
        for(i = 1; i < size; i++){
            min = MAX;
            next = 0;
            for(j = 0; j < size; j++){
                if(dist[now][j] < cost[j])
                    cost[j] = dist[now][j];
                if(cost[j] != -1 && cost[j] < min){
                    min = cost[j];
                    next = j;
                }
            }
            all += min;
            cost[next] = -1;
            now = next;
        }
        cout << all << endl;
    }
    return 0;
}

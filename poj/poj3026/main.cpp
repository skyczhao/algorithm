
#include <iostream>
#include <stdio.h>
#include <queue>
using namespace std;

#define MAX 91000000
#define SIZE 52
#define ALSIZE 102

struct node{
    int x;
    int y;
};

int move[4][2] = {1, 0, -1, 0, 0, 1, 0, -1};
int row, col;
int maze[SIZE][SIZE];
int alsize;
node alien[ALSIZE];
int dist[ALSIZE][ALSIZE];

bool canMove(int i, int j)
{
    if(i >= 0 && i < row)
        if(j >= 0 && j < col)
            if(maze[i][j] > 0)
                return true;
    return false;
}

void dijkstra(int curr)
{
    queue<node> qno;
    node now, next;
    int maze_dist[SIZE][SIZE];
    int i, j;
    for(i = 0; i < row; i++)
        for(j = 0; j < col; j++)
            maze_dist[i][j] = MAX;

    maze_dist[alien[curr].x][alien[curr].y] = 0;
    qno.push(alien[curr]);
    while(!qno.empty()){
        now = qno.front();
        qno.pop();

        for(i = 0; i < 4; i++){
            next.x = now.x + move[i][0];
            next.y = now.y + move[i][1];
            if(canMove(next.x, next.y))
                if(maze_dist[now.x][now.y] + 1 < maze_dist[next.x][next.y]){
                    maze_dist[next.x][next.y] = maze_dist[now.x][now.y] + 1;
                    qno.push(next);
                }
        }
        //for(i = 0; i < row; i++){
        //    for(j = 0; j < col; j++)
        //        if(maze_dist[i][j] < MAX)
        //            cout << maze_dist[i][j] << " ";
        //        else
        //            cout << "X" << " ";
        //    cout << endl;
        //}
        //cout << endl;
    }
    for(i = 0; i < alsize; i++)
        dist[curr][i] = maze_dist[alien[i].x][alien[i].y];
}

int main()
{
    int size;
    int i, j;
    char str[SIZE];
    int cost, curr, min, index;
    int way[ALSIZE];
    cin >> size;
    while(size--){
        // read maze
        cin >> col >> row;
        gets(str);
        alsize = 0;
        for(i = 0; i < row; i++){
            gets(str);
            for(j = 0; j < col; j++){
                if(str[j] == '#')
                    maze[i][j] = 0;
                else if(str[j] == ' ')
                    maze[i][j] = 1;
                else{
                    maze[i][j] = 2;
                    way[alsize] = MAX;
                    alien[alsize].x = i;
                    alien[alsize].y = j;
                    alsize++;
                }
            }
        }

        // calculate dist
        for(i = 0; i < alsize; i++)
            dijkstra(i);

        //for(i = 0; i < alsize; i++){
        //    for(j = 0; j < alsize; j++)
        //        cout << dist[i][j] << " ";
        //    cout << endl;
        //}

        // calculate min cost
        cost = 0;
        way[0] = -1;
        curr = 0;
        for(i = 1; i < alsize; i++){
            min = MAX;
            index = 0;
            for(j = 0; j < alsize; j++){
                if(dist[curr][j] < way[j])
                    way[j] = dist[curr][j];
                if(way[j] != -1 && way[j] < min){
                    min = way[j];
                    index = j;
                }
                //for(int m = 0; m < alsize; m++)
                //    cout << way[m] << " ";
                //cout << endl;
            }
            cost += way[index];
            way[index] = -1;
            curr = index;
        }
        cout << cost << endl;
    }
    return 0;
}


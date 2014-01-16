
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <queue>

struct node{
    int state[4][4];
    int point[20][2];
    int length;
};

std::queue<node*> path;
bool flag[66000];

int getIndex(int state[4][4])
{
    int i, j, base, index;
    base = 1;
    index = 0;
    for(i = 0; i < 4; i++)
        for(j = 0; j < 4; j++){
            index += (state[i][j] * base);
            base *= 2;
        }
    return index;
}

void changeState(int i, int j, int state[4][4])
{
    int x;
    for(x = 0; x < 4; x++)
        state[x][j] = 1 - state[x][j];
    for(x = 0; x < 4; x++)
        state[i][x] = 1 - state[i][x];
    state[i][j] = 1 - state[i][j];
}

int main()
{
    //freopen("input.txt", "r", stdin);

    char str[5];
    int i, j, index;
    node *begin, *curr, *next;
    memset(flag, 0, sizeof(flag));

    begin = (node*)malloc(sizeof(node));
    // get input
    for(i = 0; i < 4; i++){
        gets(str);
        for(j = 0; j < 4; j++){
            if(str[j] == '-')
                begin->state[i][j] = 0;
            else
                begin->state[i][j] = 1;
        }
    }
    begin->length = 0;

    // processing
    flag[getIndex(begin->state)] = true;
    path.push(begin);
    while(!path.empty()){
        curr = path.front();
        index = getIndex(curr->state);
        if(index == 0)
            break;

        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                changeState(i, j, curr->state);

                index = getIndex(curr->state);
                if(!flag[index]){
                    flag[index] = true;
                    next = (node*)malloc(sizeof(node));
                    memcpy(next->state, curr->state, sizeof(next->state));
                    memcpy(next->point, curr->point, sizeof(next->point));
                    next->length = curr->length + 1;
                    next->point[curr->length][0] = i;
                    next->point[curr->length][1] = j;
                    path.push(next);
                }

                changeState(i, j, curr->state);
            }
        }

        path.pop();
        free(curr);
    }
    // output
    if(!path.empty()){
        curr = path.front();
        printf("%d\n", curr->length);
        for(i = 0; i < curr->length; i++)
            printf("%d %d\n", curr->point[i][0] + 1, curr->point[i][1] + 1);

        while(!path.empty()){
            free(path.front());
            path.pop();
        }
    }
    else
        printf("-1\n");

    //fclose(stdin);
    return 0;
}

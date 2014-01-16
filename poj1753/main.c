
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <queue>

struct node
{
    int state[4][4];
    int step;
};

std::queue<node*> path;
int direct[5][2] = {-1, 0, 0, 1, 1, 0, 0, -1, 0, 0};
bool flag[66000];

int getNum(int state[4][4])
{
    int base, i, j, index;
    index = 0;
    base = 1;
    for(i = 0; i < 4; i++){
        for(j = 0; j < 4; j++){
            index += (state[i][j] * base);
            base *= 2;
        }
    }
    return index;
}

void changeState(int i, int j, int state[4][4])
{
    int r, c;
    for(int n = 0; n < 5; n++){
        r = i + direct[n][0];
        c = j + direct[n][1];
        if(r >= 0 && r < 4)
            if(c >= 0 && c < 4)
                state[r][c] = 1 - state[r][c];
    }
}

int main()
{
    //freopen("input.txt", "r", stdin);

    char str[5];
    node *start, *now, *next;
    int i, j;
    memset(flag, 0, sizeof(flag));

    // read
    start = (node*)malloc(sizeof(node));
    for(i = 0; i < 4; i++){
        gets(str);
        for(j = 0; j < 4; j++){
            if(str[j] == 'b')
                start->state[i][j] = 1;
            else
                start->state[i][j] = 0;
        }
    }
    start->step = 0;

    // begin
    flag[getNum(start->state)] = true;
    path.push(start);
    while(!path.empty()){
        // check
        now = path.front();
        int num = getNum(now->state);
        if(num == 0 || num == 65535)
            break;

        //printf("==========\n");
        //for(int m = 0; m < 4; m++){
        //    for(int n = 0; n < 4; n++)
        //        printf("%d ", now->state[m][n]);
        //    printf("\n");
        //}
        //printf("\n");
        // loop
        path.pop();
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                changeState(i, j, now->state);
                int num = getNum(now->state);
                if(!flag[num]){
                    flag[num] = true;
                    next = (node*)malloc(sizeof(node));
                    memcpy(next->state, now->state, sizeof(next->state));
                    next->step = now->step + 1;
                    path.push(next);

                    //for(int m = 0; m < 4; m++){
                    //    for(int n = 0; n < 4; n++)
                    //        printf("%d ", next->state[m][n]);
                    //    printf("\n");
                    //}
                    //printf("\n");
                }
                changeState(i, j, now->state);
            }
        }

        free(now);
    }
    if(!path.empty()){
        printf("%d\n", path.front()->step);
        while(!path.empty()){
            free(path.front());
            path.pop();
        }
    }
    else
        printf("Impossible\n");

    //fclose(stdin);
    return 0;
}

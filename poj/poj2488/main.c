
#include <stdio.h>
#include <string.h>

struct node{
    int row;
    int col;
    int drt;
};

//int direct[8][2] = {1, 2, -1, 2, 1, -2, -1, -2, 2, 1, -2, 1, 2, -1, -2, -1};
//int direct[8][2] = {-2, -1, -2, 1, -1, 2, 1, 2, 2, 1, 2, -1, 1, -2, -1, -2};
int direct[8][2] = {{-1,-2},{1,-2},{-2,-1},{2,-1},{-2,1},{2,1},{-1,2},{1,2}};
bool flag[64][64];
int row, col;
node stacks[64];

bool isend()
{
    for(int i = 0; i < row; i++)
        for(int j = 0; j < col; j++)
            if(!flag[i][j])
                return false;
    return true;
}

int main()
{
    int size, mark;
    int now, i, j;
    scanf("%d", &size);
    for(mark = 0; mark < size; mark++){
        scanf("%d %d", &row, &col);
        memset(flag, 0, 64 * 64 * sizeof(bool));

        for(i = 0; i < row; i++)
            for(j = 0; j < col; j++){
                if(isend())
                    break;
                // initialize
                stacks[0].row = i;
                stacks[0].col = j;
                stacks[0].drt = 0;
                flag[i][j] = true;
                now = 0;
                while(now >= 0){
                    if(isend())
                        break;
                    if(stacks[now].drt < 8){
                        int t_row = stacks[now].row + direct[stacks[now].drt][0];
                        int t_col = stacks[now].col + direct[stacks[now].drt][1];
                        stacks[now].drt++;
                        if(t_row >= 0 && t_row < row)
                            if(t_col >= 0 && t_col < col)
                                if(!flag[t_row][t_col]){
                                    flag[t_row][t_col] = true;
                                    now++;
                                    stacks[now].row = t_row;
                                    stacks[now].col = t_col;
                                    stacks[now].drt = 0;
                                }
                    }
                    else{
                        flag[stacks[now].row][stacks[now].col] = false;
                        now--;
                    }
                }
            }

        printf("Scenario #%d:\n", mark + 1);
        if(now >= 0){
            for(i = 0; i <= now; i++)
                printf("%c%d", stacks[i].col + 'A', stacks[i].row + 1);
            printf("\n");
        }
        else
            printf("impossible\n");
        printf("\n");
    }
    return 0;
}

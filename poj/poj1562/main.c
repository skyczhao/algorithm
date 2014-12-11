
#include <stdio.h>
#include <string.h>

int direct[8][2] = {1, 1, 1, -1, -1, 1, -1, -1, 0, 1, 1, 0, 0, -1, -1, 0};
int row, col;
char grid[104][104];
bool flag[104][104];

void markpocket(int i, int j)
{
    if(flag[i][j])
        return;
    flag[i][j] = true;
    int m, x, y;
    for(m = 0; m < 8; m++){
        x = i + direct[m][0];
        y = j + direct[m][1];
        if(x < 0 || x >= row)
            continue;
        if(y < 0 || y >= col)
            continue;
        if(grid[x][y] == '@')
            markpocket(x, y);
    }
}

int main()
{
    int i, j, num;
    while(scanf("%d %d", &row, &col) != EOF){
        if(row == 0 || col == 0)
            break;
        memset(flag, 0, sizeof(flag));
        for(i = 0; i < row; i++)
            scanf("%s", grid[i]);

        num = 0;
        for(i = 0; i < row; i++)
            for(j = 0; j < col; j++)
                if(grid[i][j] == '@' && !flag[i][j]){
                    num++;
                    markpocket(i, j);
                }
        printf("%d\n", num);
    }
    return 0;
}

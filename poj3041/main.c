
#include <stdio.h>
#include <string.h>

int size, n;
bool grid[502][502];
int next[502];
bool visit[502];

bool dfs(int x)
{
    for(int i = 0; i < size; i++)
        if(grid[x][i] && !visit[i]){
            visit[i] = true;
            if(next[i] == -1 || dfs(next[i])){
                next[i] = x;
                return true;
            }
        }
    return false;
}

int main()
{
    int x, y, i, j;
    memset(grid, 0, sizeof(grid));
    scanf("%d %d", &size, &n);
    for(i = 0; i < n; i++){
        scanf("%d %d", &x, &y);
        grid[x - 1][y - 1] = true;
    }
    for(i = 0; i < size; i++)
        next[i] = -1;

    int num = 0;
    for(i = 0; i < size; i++){
        memset(visit, 0, 502 * sizeof(bool));
        if(dfs(i))
            num++;
    }
    printf("%d\n", num);
    return 0;
}


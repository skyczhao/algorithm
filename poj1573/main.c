
#include <stdio.h>
#include <string.h>

int width, length, poi;
char grid[15][15];
int steps[15][15];

int main()
{
    int i;
    int x, y, path;
    bool flag;
    while(scanf("%d %d %d", &width, &length, &poi) != EOF){
        if(width == 0 && length == 0)
            if(poi == 0)
                break;
        memset(steps, 0, 225 * sizeof(int));
        for(i = 0; i < width; i++)
            scanf("%s", grid[i]);

        path = 0;
        x = 0;
        y = poi - 1;
        flag = false;
        while(steps[x][y] == 0){
            steps[x][y] = ++path;
            switch(grid[x][y]){
                case 'N':
                    x--;
                    break;
                case 'E':
                    y++;
                    break;
                case 'S':
                    x++;
                    break;
                case 'W':
                    y--;
                    break;
            }
            if(x < 0 || x >= width){
                flag = true;
                break;
            }
            if(y < 0 || y >= length){
                flag = true;
                break;
            }
        }
        if(flag)
            printf("%d step(s) to exit\n", path);
        else
            printf("%d step(s) before a loop of %d step(s)\n", steps[x][y] - 1, path - steps[x][y] + 1);
    }
    return 0;
}


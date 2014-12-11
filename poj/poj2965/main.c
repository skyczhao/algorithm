
#include <stdio.h>
#include <string.h>

void changeState(int i, int j, int state[4][4])
{
    int x;
    for(x = 0; x < 4; x++)
        state[i][x] = 1 - state[i][x];
    for(x = 0; x < 4; x++)
        state[x][j] = 1 - state[x][j];
    state[i][j] = 1 - state[i][j];
}

int main()
{
    int state[4][4];
    char str[5];
    int i, j, sum;
    memset(state, 0, sizeof(state));
    for(i = 0; i < 4; i++){
        gets(str);
        for(j = 0; j < 4; j++){
            if(str[j] == '+')
                changeState(i, j, state);
        }
    }
    sum = 0;
    for(i = 0; i < 4; i++)
        for(j = 0; j < 4; j++)
            sum += state[i][j];
    printf("%d\n", sum);
    for(i = 0; i < 4; i++)
        for(j = 0; j < 4; j++)
            if(state[i][j])
                printf("%d %d\n", i + 1, j + 1);
    return 0;
}

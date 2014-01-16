
#include <stdio.h>
#include <string.h>

struct node{
    int num;
    int length;
    int prev;
    int row;
    int col;
};
node path[65540];
bool flag[65540];
int seed[4][4];

int getSeed(int i, int j)
{
    int seed, x;
    seed = 0;
    for(x = 0; x < 4; x++)
        seed |= (1 << (x * 4 + j));
    for(x = 0; x < 4; x++)
        seed |= (1 << (x + i * 4));
    return seed;
}

int main()
{
    int num, i, j;
    int curr, end;
    char str[5];
    // read
    memset(flag, 0, sizeof(flag));
    num = 0;
    for(i = 0; i < 4; i++){
        gets(str);
        for(j = 0; j < 4; j++){
            if(str[j] == '+')
                num |= (1 << (j + i * 4));
            seed[i][j] = getSeed(i, j);
        }
    }
    // ready
    flag[num] = true;
    path[0].num = num;
    path[0].length = 0;
    path[0].prev = -1;
    curr = 0;
    end = 0;
    // process
    while(curr <= end){
        if(path[curr].num == 0)
            break;
        for(i = 0; i < 4; i++){
            for(j = 0; j < 4; j++){
                //seed = getSeed(i, j);
                num = path[curr].num ^ seed[i][j];
                if(!flag[num]){
                    flag[num] = true;
                    end++;
                    path[end].num = num;
                    path[end].length = path[curr].length + 1;
                    path[end].prev = curr;
                    path[end].row = i;
                    path[end].col = j;
                }
            }
        }
        curr++;
    }
    // output
    if(curr <= end){
        printf("%d\n", path[curr].length);
        while(path[curr].prev != -1){
            printf("%d %d\n", path[curr].row + 1, path[curr].col + 1);
            curr = path[curr].prev;
        }
    }
    else
        printf("Impossible\n");
    return 0;
}

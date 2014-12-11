
#include <stdio.h>
#include <string.h>

bool net[205][205];
int t_height, t_width;
int pair[205];
bool visit[205];

bool dfs(int num)
{
    for(int i = 0; i < t_width; i++){
        if(net[num][i] && !visit[i]){
            visit[i] = true;
            if(pair[i] == -1 || dfs(pair[i])){
                pair[i] = num;
                return true;
            }
        }
    }
    return false;
}

int main()
{
    int size, i, j, height, width, temp, all;
    int direct[4][2] = {-1, 0, 1, 0, 0, -1, 0, 1};
    int n_i, n_j;
    bool mat[42][12];
    int index[42][12];
    char str[12];
    scanf("%d", &size);
    while(size--){
        memset(mat, 0, sizeof(mat));
        scanf("%d %d", &height, &width);
        n_i = 0;
        n_j = 0;
        all = 0;
        for(i = 0; i < height; i++){
            scanf("%s", str);
            for(j = 0; j < width; j++){
                if(str[j] == '*'){
                    mat[i][j] = true;
                    all++;
                }
                if((j + i % 2) % 2 == 0){
                    index[i][j] = n_i;
                    n_i++;
                }
                else{
                    index[i][j] = n_j;
                    n_j++;
                }
            }
        }
        t_height = n_i;
        t_width = n_j;

        memset(net, 0, sizeof(net));
        for(i = 0; i < height; i++)
            for(j = i % 2; j < width; j += 2)
                if(mat[i][j])
                    for(int t = 0; t < 4; t++){
                        n_i = i + direct[t][0];
                        n_j = j + direct[t][1];
                        if(n_i >= 0 && n_i < height)
                            if(n_j >= 0 && n_j < width)
                                if(mat[n_i][n_j]){
                                    net[index[i][j]][index[n_i][n_j]] = true;
                                }
                    }

        int num = 0;
        for(i = 0; i < t_width; i++)
            pair[i] = -1;
        for(i = 0; i < t_height; i++){
            memset(visit, 0, 205 * sizeof(bool));
            if(dfs(i))
                num++;
        }
        //printf("%d\n", num);
        //printf("%d\n", all);
        printf("%d\n", all - num);

    }
    return 0;
}


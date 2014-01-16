
#include <stdio.h>
#include <string.h>
#include <queue>

struct node{
    int x;
    int y;
};

int mat[40][40];
bool flag[40][40];
int size;
long long sum[40][40];
std::queue<int> ndex_i;
std::queue<int> ndex_j;

int main()
{
    int i, j, length;
    char str[40];
    while(scanf("%d", &size) != EOF){
        if(size < 0)
            break;
        memset(flag, 0, 1600 * sizeof(bool));
        memset(sum, 0, 1600 * sizeof(long long));
        for(i = 0; i < size; i++){
            scanf("%s", str);
            length = strlen(str);
            for(j = 0; j < length; j++){
                mat[i][j] = str[j] - '0';
            }
        }

        flag[0][0] = true;
        ndex_i.push(0);
        ndex_j.push(0);
        sum[0][0] = 1;
        while(!ndex_i.empty() && !ndex_j.empty()){
            i = ndex_i.front();
            ndex_i.pop();
            j = ndex_j.front();
            ndex_j.pop();
            printf("%d %d\n", i, j);
            if(mat[i][j] == 0)
                continue;

            if(i + mat[i][j] < size){
                sum[i + mat[i][j]][j] += sum[i][j];
                if(!flag[i + mat[i][j]][j]){
                    flag[i + mat[i][j]][j] = true;
                    ndex_i.push(i + mat[i][j]);
                    ndex_j.push(j);
                }
            }
            if(j + mat[i][j] < size){
                sum[i][j + mat[i][j]] += sum[i][j];
                if(!flag[i][j + mat[i][j]]){
                    flag[i][j + mat[i][j]] = true;
                    ndex_i.push(i);
                    ndex_j.push(j + mat[i][j]);
                }
            }
        }
        printf("%lld\n", sum[size - 1][size - 1]);
    }
    return 0;
}

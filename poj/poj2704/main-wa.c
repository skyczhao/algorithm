
#include <stdio.h>
#include <string.h>
#include <queue>

struct node{
    int i;
    int j;
};

int mat[40][40];
long long num[40][40];
bool flag[40][40];
int size;
std::queue<node> way;

int main()
{
    int i, j, temp;
    char str[40];
    node curr, next;
    while(scanf("%d", &size) != EOF){
        if(size == -1)
            break;

        memset(num, 0, sizeof(num));
        memset(flag, 0, sizeof(flag));
        for(i = 0; i < size; i++){
            scanf("%s", str);
            temp = strlen(str);
            for(j = 0; j < temp; j++){
                mat[i][j] = str[j] - '0';
            }
        }

        curr.i = 0;
        curr.j = 0;
        flag[0][0] = true;
        num[0][0] = 1;
        way.push(curr);
        while(!way.empty()){
            curr = way.front();
            way.pop();
            if(mat[curr.i][curr.j] == 0)
                continue;

            temp = curr.i + mat[curr.i][curr.j];
            if(temp < size){
                num[temp][curr.j] += num[curr.i][curr.j];
                if(!flag[temp][curr.j]){
                    flag[temp][curr.j] = true;
                    next.i = temp;
                    next.j = curr.j;
                    way.push(next);
                }
            }
            temp = curr.j + mat[curr.i][curr.j];
            if(temp < size){
                num[curr.i][temp] += num[curr.i][curr.j];
                if(!flag[curr.i][temp]){
                    flag[curr.i][temp] = true;
                    next.i = curr.i;
                    next.j = temp;
                    way.push(next);
                }
            }
        }
        printf("%lld\n", num[size - 1][size - 1]);
    }
    return 0;
}

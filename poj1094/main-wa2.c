
#include <stdio.h>
#include <string.h>

int size;
int prev[30];
int back[30][30];
char res[30];

int trySort()
{
    int tmp[30];
    bool flag[30];
    int i, j, next, error;
    memcpy(tmp, prev, 30 * sizeof(int));
    memset(flag, 0, 30 * sizeof(bool));
    for(i = 0; i < size; i++){
        error = 0;
        for(j = 0; j < size; j++)
            if(!flag[j] && tmp[j] == 0){
                error++;
                next = j;
            }
        if(error == 1){
            res[i] = next + 'A';
            flag[next] = true;
            for(j = 0; j < size; j++)
                tmp[j] -= back[next][j];
        }
        else
            break;
    }
    res[size] = '\0';
    return error;
}

int main()
{
    int line, mark, error;
    bool flag;
    char str[5];
    while(scanf("%d %d", &size, &line) != EOF){
        if(size == 0 || line == 0)
            break;

        memset(prev, 0, 30 * sizeof(int));
        memset(back, 0, 900 * sizeof(int));
        flag = true;
        mark = 0;
        while(line--){
            scanf(" %s", str);
            if(flag){
                mark++;
                int l = str[0] - 'A';
                int r = str[2] - 'A';
                prev[r]++;
                if(back[r][l] > 0){
                    error = 0;
                    flag = false;
                    continue;
                }
                back[l][r]++;

                error = trySort();
                if(error <= 1){
                    flag = false;
                }
            }
        }

        if(error == 1)
            printf("Sorted sequence determined after %d relations: %s.\n", mark, res);
        else if(error < 1)
            printf("Inconsistency found after %d relations.\n", mark);
        else
            printf("Sorted sequence cannot be determined.\n");
    }
    return 0;
}


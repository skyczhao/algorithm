
#include <stdio.h>
#include <string.h>

int size;
int term[30];
int cmp[30][30];
char res[30];

int trySort()
{
    int tmp_term[30];
    bool flag[30];
    bool ambi;
    memcpy(tmp_term, term, 30 * sizeof(int));
    memset(flag, 0, 30 * sizeof(bool));
    int i, j, next, num;
    ambi = false;
    for(i = 0; i < size; i++){
        num = 0;
        for(j = 0; j < size; j++)
            if(!flag[j] && tmp_term[j] == 0){
                num++;
                next = j;
            }
        if(num == 1){
            flag[next] = true;
            res[i] = next + 'A';
            for(j = 0; j < size; j++)
                tmp_term[j] -= cmp[next][j];
        }
        else if(num == 2)
            ambi = true;
    }
    res[size] = 0;
    if(ambi)
        return 2;
    return num;
}

int main()
{
    int n, num;
    int left, right;
    int mark_size;
    bool mark;
    char str[5];
    while(scanf("%d %d", &size, &n) != EOF){
        if(size == 0 && n == 0)
            break;

        // read data
        memset(term, 0, 30 * sizeof(int));
        memset(cmp, 0, 900 * sizeof(int));
        mark = true;
        mark_size = 0;
        num = 0;
        while(n--){
            scanf("%s", str);
            if(mark){
                mark_size++;
                left = str[0] - 'A';
                right = str[2] - 'A';
                term[right]++;
                cmp[left][right]++;

                num = trySort();
                if(num <= 1)
                    mark = false;
            }
        }

        if(num == 1){
            printf("Sorted sequence determined after %d relations: ", mark_size);
            for(n = 0; n < size; n++)
                printf("%c", res[n]);
            printf("\n");
        }
        else if(num < 1)
            printf("Inconsistency found after %d relations.\n", mark_size);
        else
            printf("Sorted sequence cannot be determined.\n");
    }
    return 0;
}


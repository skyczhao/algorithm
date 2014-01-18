
#include <stdio.h>
#include <string.h>

int size;
int prev[30];
int back[30][30];
int asq[30];

int topoSort()
{
    int i, j, curr, times;
    int t_prev[30];
    bool flag[30];
    bool ambig;
    memcpy(t_prev, prev, 30 * sizeof(int));
    memset(flag, 0, 30 * sizeof(bool));

    ambig = false;
    for(i = 0; i < size; i++){
        // find zero
        times = 0;
        for(j = 0; j < size; j++)
            if(t_prev[j] == 0 && !flag[j]){
                curr = j;
                times++;
            }

        // check ambiguity
        if(times > 1)
            ambig = true;
        // check loop
        if(times < 1)
            return 1;

        asq[i] = curr;
        flag[curr] = true;

        // remove curr point
        for(j = 0; j < size; j++){
            t_prev[j] -= back[curr][j];
        }
    }
    if(ambig)
        return 2;
    else
        return 0;
}

int main()
{
    int line, state, i, n_line;
    bool flag;
    char str[5];
    while(scanf("%d %d", &size, &line) != EOF){
        if(size == 0 || line == 0)
            break;

        memset(prev, 0, 30 * sizeof(int));
        memset(back, 0, 900 * sizeof(int));
        flag = true;
        state = -1;
        for(i = 0; i < line; i++){
            scanf(" %s", str);
            if(flag){
                int l = str[0] - 'A';
                int r = str[2] - 'A';
                // loop exist
                if(back[r][l] > 0){
                    state = 1; // mark the state as inconsistency
                    n_line = i + 1;
                    flag = false;
                    continue;
                }
                prev[r]++;
                back[l][r]++;

                // calculate
                state = topoSort();
                if(state <= 1){
                    n_line = i + 1;
                    flag = false;
                }
                //printf("%d %d\n", state, i);
            }
        }

        //for(i = 0; i < size; i++)
        //    printf("%d ", prev[i]);
        //printf("\n");
        if(state == 1)
            printf("Inconsistency found after %d relations.\n", n_line);
        else if(state == 0){
            printf("Sorted sequence determined after %d relations: ", n_line);
            for(i = 0; i < size; i++)
                printf("%c", asq[i] + 'A');
            printf(".\n");
        }
        else
            printf("Sorted sequence cannot be determined.\n");
    }
    return 0;
}



#include <stdio.h>
#include <string.h>

bool used[102];
char str[102][102];
char pair[30];

bool ismatch(char *a, char *b)
{
    int i;
    int leng = strlen(a);
    if(leng != strlen(b))
        return false;
    for(i = 0; i < leng; i++){
        if(a[i] != pair[b[i] - 'A'])
            return false;
    }
    return true;
}

int main()
{
    int size, mark;
    int i, t_i, num;
    char tmp[102];
    bool match;

    pair['A' - 'A'] = 'T';
    pair['T' - 'A'] = 'A';
    pair['C' - 'A'] = 'G';
    pair['G' - 'A'] = 'C';

    scanf("%d", &size);
    while(size--){
        scanf("%d", &mark);
        memset(used, 0, 102 * sizeof(bool));
        t_i = 0;
        num = 0;
        while(mark--){
            scanf("%s", tmp);
            match = false;
            for(i = 0; i < t_i; i++)
                if(!used[i] && ismatch(str[i], tmp)){
                    used[i] = true;
                    match = true;
                    num++;
                    break;
                }
            if(!match){
                strcpy(str[t_i], tmp);
                t_i++;
            }
        }
        printf("%d\n", num);
    }
    return 0;
}



#include <stdio.h>
#include <string.h>
using namespace std;

bool smaller(char *x, char *y)
{
    char x_f[202], y_f[202];
    strcpy(x_f, x);
    strcat(x_f, y);
    strcpy(y_f, y);
    strcat(y_f, x);
    if(strcmp(x_f, y_f) < 0)
        return true;
    else
        return false;
}

int main()
{
    int size, n, i, j;
    char x_f[202], y_f[202];
    char str[10][101];
    char tmp[101];

    scanf("%d", &size);
    while(size--){
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            scanf("%s", str[i]);
        for(i = 0; i < n; i++)
            for(j = i + 1; j < n; j++)
                if(smaller(str[j], str[i])){
                    strcpy(tmp, str[j]);
                    strcpy(str[j], str[i]);
                    strcpy(str[i], tmp);
                }

        for(i = 0; i < n; i++)
            printf("%s", str[i]);
        printf("\n");
    }
    return 0;
}



#include <stdio.h>
#include <string.h>

int main()
{
    char str[258];
    int size, i, res;
    while(1){
        gets(str);
        size = strlen(str);
        if(size == 1 && str[0] == '#')
            break;
        res = 0;
        for(i = 0; i < size; i++){
            if(str[i] != ' '){
                res += ((i + 1) * (str[i] - 'A' + 1));
            }
        }
        printf("%d\n", res);
    }
    return 0;
}

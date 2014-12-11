
#include <stdio.h>
#include <string.h>
int main()
{
    int size, i, n, acc;
    char name[22][14];
    char ip[22][18];
    char pair[12][2][14];
    char temp[2][14];

    scanf("%d", &size);
    while(size > 0){
        acc = 0;
        for(i = 0; i < size; i++){
            scanf("%s %s", name[i], ip[i]);
            for(n = 0; n < i; n++)
                if(strcmp(ip[n], ip[i]) == 0){
                    strcpy(pair[acc][0], name[n]);
                    strcpy(pair[acc][1], name[i]);
                    acc++;
                }
        }

        for(i = 0; i < size / 2; i++){
            for(n = i + 1; n < size / 2; n++){
                if(strcmp(pair[n][0], pair[i][0]) < 0){
                    memcpy(temp, pair[n], sizeof(temp));
                    memcpy(pair[n], pair[i], sizeof(temp));
                    memcpy(pair[i], temp, sizeof(temp));
                }
            }
        }

        for(i = 0; i < size / 2; i++){
            printf("%s is the MaJia of %s\n", pair[i][1], pair[i][0]);
        }
        printf("\n");
        scanf("%d", &size);
    }
    return 0;
}


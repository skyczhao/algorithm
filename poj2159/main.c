#include <stdio.h>
#include <string.h>
#include <algorithm>

/**
 * 关键1, 理解题意,没有说每个字母的偏移量是一样的
 * 关键2, 不超过100,但是刚好是100的话呢?预留多一些空间还是可以的
 */
int main()
{
    char result[103], source[103];
    short int alp_res[26], alp_sou[26];
    int size, i;
    bool flag;

    scanf("%s %s", result, source);
    size = strlen(result);

    memset(alp_res, 0, sizeof(alp_res));
    memset(alp_sou, 0, sizeof(alp_sou));
    for(i = 0; i < size; i++){
        alp_res[result[i] - 'A']++;
        alp_sou[source[i] - 'A']++;
    }

    std::sort(alp_res, alp_res + 26);
    std::sort(alp_sou, alp_sou + 26);
    flag = true;
    for(i = 0;  i < 26; i++)
        if(alp_res[i] != alp_sou[i]){
            flag = false;
            break;
        }

    if(flag)
        printf("YES\n");
    else
        printf("NO\n");
    return 0;
}

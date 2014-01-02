
#include <stdio.h>

char str[2005][10];
int dist[2005][2005];

int cal_dist(const char a[10], const char b[10])
{
    int mark = 0;
    for(int i = 0; i < 7; i++)
        if(a[i] != b[i])
            mark++;
    return mark;
}

int main()
{
    int size, i, j;
    while(scanf("%d", &size) != EOF){
        if(size == 0)
            break;
        for(i = 0; i < size; i++){
            scanf("%s", str[i]);
            for(j = 0; j <= i; j++){
                dist[i][j] = cal_dist(str[i], str[j]);
                dist[j][i] = dist[i][j];
            }
        }

    }
    return 0;
}


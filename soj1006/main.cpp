
#include <stdio.h>
#include <string.h>

char str[102][6];

char fp[122][6];
bool visit[6];
int idx;
void dg(int deep) 
{
    if (deep == 5) {
        fp[idx][deep] = 0;
        memcpy(fp[idx + 1], fp[idx], 6 * sizeof(char));
        idx++;
        return;
    }

    int i;
    for (i = 0; i < 5; i++)
        if (!visit[i]) {
            visit[i] = true;

            fp[idx][deep] = 'A' + i;
            dg(deep + 1);

            visit[i] = false;
        }
}

int cmp(char *a, char *b)
{
    int i, j, sum;
    int mark[6];
    sum = 0;
    for (i = 0; i < 5; i++) {
        for (j = 0; j < 5; j++)
            if (a[j] == b[i])
                break;
        mark[i] = j;
        for (j = i - 1; j >=0; j--)
            if (mark[j] > mark[i])
                sum++;
    }
    return sum;
}

int main()
{
    int size, i, j;
    int num, min_num, min_idx;
    memset(visit, 0, 6 * sizeof(bool));
    idx = 0;
    dg(0);
    while (scanf("%d", &size) != EOF) {
        if (size == 0)
            break;
        for (i = 0; i < size; i++) {
            scanf("%s", str[i]);
        }

        min_num = 10000;
        for (i = 0; i < 120; i++) {
            num = 0;
            for (j = 0; j < size; j++)
                num += cmp(fp[i], str[j]);
            if (num < min_num) {
                min_idx = i;
                min_num = num;
            }
            else if (num == min_num) {
                //if (strcmp(fp[i], fp[min_idx]) < 0) {
                if (i < min_idx) {
                    min_idx = i;
                    min_num = num;
                }
            }
        }
        printf("%s is the median ranking with value %d.\n", fp[min_idx], min_num);
    }
    return 0;
}


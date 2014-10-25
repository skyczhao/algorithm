
#include <stdio.h>
#include <stdlib.h>

int num[1002];
int md[1002][1002];

int dg(int start, int end)
{
    int l_s, r_s;
    if (start > end) 
        return 0;

    if (md[start][end] > -1)
        return md[start][end];

    // left
    if (num[start + 1] >= num[end]) {
        l_s = dg(start + 2, end);
        l_s += (num[start] - num[start + 1]);
    }
    else {
        l_s = dg(start + 1, end - 1);
        l_s += (num[start] - num[end]);
    }
    // right
    if (num[start] >= num[end - 1]) {
        r_s = dg(start + 1, end - 1);
        r_s += (num[end] - num[start]);
    }
    else {
        r_s = dg(start, end - 2);
        r_s += (num[end] - num[end - 1]);
    }

    md[start][end] = (l_s > r_s ? l_s : r_s);
    return md[start][end];
}

int main()
{
    int mark, size, i, j;
    mark = 0;
    while (scanf("%d", &size) != EOF) {
        if (size == 0) 
            break;
        for (i = 0; i < size; i++) 
            scanf("%d", &num[i]);
        for (i = 0; i < size; i++) 
            for (j = 0; j < size; j++) 
                md[i][j] = -1;

        printf("In game %d, the greedy strategy might lose by as many as %d points.\n", ++mark, dg(0, size - 1));
    }
    return 0;
}                                 


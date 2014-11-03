
#include <stdio.h>
#include <string.h>

int main()
{
    char key[12], str[102];
    int kleng, sleng, rows;
    int index[12], i, j, min_j;
    while (scanf("%s", key) != EOF) {
        if (strcmp(key, "THEEND") == 0)
            break;
        scanf("%s", str);

        key[11] = 'Z' + 1;
        kleng = strlen(key);
        sleng = strlen(str);
        memset(index, 0, 12 * sizeof(int));

        for (i = 0; i < kleng; i++) {
            min_j = 11;
            for (j = 0; j < kleng; j++)
                if (index[j] == 0 && key[j] < key[min_j])
                    min_j = j;
            index[min_j] = i + 1;
        }

        rows = sleng / kleng;
        for (i = 0; i < rows; i++)
            for (j = 0; j < kleng; j++)
                printf("%c", str[(index[j] - 1) * rows + i]);
        printf("\n");
    }
    return 0;
}


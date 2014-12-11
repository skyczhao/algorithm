
#include <stdio.h>
#include <string.h>

int main()
{
    int rows, cols, leng;
    int i, j, idx, base;
    char str[202];
    while (scanf("%d", &cols) != EOF && cols > 0) {
        scanf("%s", str);
        leng = strlen(str);
        rows = leng / cols;

        for (i = 0; i < cols; i++) {
            base = -1;
            idx = -1;
            for (j = 0; j < rows; j++) {
                if (j % 2 == 0)
                    idx += 1;
                else
                    idx += (2 * cols - 1);
                base *= -1;
                printf("%c", str[idx + i * base]);
            }
        }
        printf("\n");
    }
    return 0;
}


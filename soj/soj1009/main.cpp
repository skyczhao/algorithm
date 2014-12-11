
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int prime[30];

long long int check(long long int num)
{
    long long int i, mid;
    if (num < 2)
        return 1;
    if (num == 2 || num == 3)
        return 0;
    if (num % 2 == 0)
        return 2;

    mid = (long long int)sqrt(num) + 1;
    for (i = 3; i < mid; i += 2)
        if (num % i == 0)
            return i;
    return 0;
}

void generate()
{
    int i;
    int idx = 1;
    prime[0] = 2;
    for (i = 1; i < 100; i += 2)
        if(check(i) == 0) {
            prime[idx] = i;
            idx++;
        }
}

long long int expo(int e)
{
    long long int num;
    int i;
    for(num = 1, i = 0; i < e; i++)
        num *= 2;
    return num;
}

int main()
{
    char str[4];
    int i, size;
    long long int num, num_2, part;
    bool flag;
    //char *word;

    generate();
    //FILE *fin = fopen("a.in", "r");
    //if (fin != NULL) {
    //    while(fgets(str, 4, fin) != NULL) {
    //        //word = strtok(str, "\n");

    //        size = atoi(str);

        while(scanf("%d", &size) != EOF) {
            for (i = 0; prime[i] <= size && prime[i] < 60; i++) {
                // don't use pow()
                num = expo(prime[i]) - 1;
                num_2 = num;
                flag = false;
                while(1) {
                    if (flag)
                        printf("* ");
                    part = check(num);
                    if (part == 0)
                        break;
                    printf("%lld ", part);
                    num = num / part;
                    flag = true;
                }
                if (flag)
                    printf("%lld = %lld = ( 2 ^ %d ) - 1\n", num, num_2, prime[i]);
            }
        }
    //    }

    //    fclose(fin);
    //} else {
    //    printf("Error!\n");
    //}
    return 0;
}


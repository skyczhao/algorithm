
#include <stdio.h>
int main()
{
    unsigned long long num;
    int i;
    num = 1;
    for(i = 0; i < 64; i++){
        num *= 2;
        num += 1;
        printf("%lld\n", num);
    }
    return 0;
}

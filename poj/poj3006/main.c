
#include <stdio.h>
#include <math.h>

bool isPrime(int &num)
{
    if(num == 2)
        return true;
    if(num < 2)
        return false;
    if(num % 2 == 0)
        return false;
    int mid = (int)sqrt(num) + 1;
    for(int i = 3; i < mid; i+=2)
        if(num % i == 0)
            return false;
    return true;
}

int main()
{
    int a, b, n;
    int res, i;
    while(scanf("%d %d %d", &a, &b, &n) != EOF){
        if(a == 0 && b == 0 && n == 0)
            break;
        res = a;
        i = 0;
        while(1){
            if(isPrime(res)){
                i++;
                if(i >= n)
                    break;
            }
            res += b;
        }
        printf("%d\n", res);
    }
    return 0;
}

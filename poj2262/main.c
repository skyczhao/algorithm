
#include <stdio.h>
#include <math.h>
#include <vector>
#define MAX 1000005
std::vector<int> prime_num;

bool isPrime(int num)
{
    int i;
    if(num <= 1)
        return false;
    if(num == 2)
        return true;
    if(num % 2 == 0)
        return false;
    int mid = (int)sqrt(num) + 1;
    for(i = 3; i < mid; i += 2)
        if(num % i == 0)
            return false;
    return true;
}

int main()
{
    int num, i, mid;
    bool flag;
    // store prime number
    prime_num.clear();
    prime_num.push_back(2);
    for(i = 3; i < MAX; i += 2)
        if(isPrime(i))
            prime_num.push_back(i);
    // get input
    while(scanf("%d", &num) != EOF){
        if(num == 0)
            break;
        flag = true;
        mid = num / 2 + 1;
        //  try to find the result
        for(i = 0; i < mid; i++)
            if(isPrime(num - prime_num[i])){
                flag = false;
                printf("%d = %d + %d\n", num, prime_num[i], num - prime_num[i]);
                break;
            }
        if(flag)
            printf("Goldbach's conjecture is wrong.\n");
    }
    return 0;
}

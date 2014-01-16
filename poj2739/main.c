#include <stdio.h>
#include <math.h>
#include <vector>

// 关键: 理解题意,连续的素数加起来,另外要注意的就是范围是10000,不要看错了
std::vector<int> pn;
bool isPrime(int number)
{
    if(number < 2)
        return false;
    if(number == 2)
        return true;
    if(number % 2 == 0)
        return false;
    for(int i = 3; i < ((int)sqrt(number) + 1); i+=2)
        if(number % i == 0)
            return false;
    return true;
}

int main()
{
    int number, i, j, size;
    int result, temp;
    for(i = 1;  i < 10000; i++)
        if(isPrime(i))
            pn.push_back(i);
    size = pn.size();

    while(scanf("%d", &number) != EOF){
        if(number == 0)
            break;
        result = 0;
        for(i = 0; i < size; i++){
            temp = 0;
            for(j = i; j < size; j++){
                temp += pn[j];
                if(temp < number)
                    continue;
                if(temp > number)
                    break;
                if(temp == number){
                    result++;
                    break;
                }
            }
        }
        printf("%d\n", result);
    }
    return 0;
}

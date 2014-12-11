
#include <stdio.h>
#include <queue>
#include <math.h>
#include <string.h>

struct node{
    int num;
    int deep;
    node(){
        num = 0;
        deep = 0;
    }
    node(const int &n, const int &d){
        num = n;
        deep = d;
    }
};
std::queue<node> noq;

bool isprime(int num)
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
    int size, num, res;
    int temp, next, digit, i;
    node curr;
    bool flag;
    bool visit[10005];
    scanf("%d", &size);
    while(size--){
        memset(visit, 0, 10005 * sizeof(bool));
        scanf("%d %d", &num, &res);

        flag = false;
        noq.push(node(num, 0));
        visit[num] = true;
        while(!noq.empty()){
            curr = noq.front();
            noq.pop();
            if(curr.num == res){
                flag = true;
                break;
            }

            digit = curr.num % 10;
            temp = curr.num - digit;
            for(i = 0; i < 10; i++)
                if(i != digit){
                    next = temp + i;
                    if(next < 1000)
                        continue;
                    if(isprime(next) && !visit[next]){
                        visit[next] = true;
                        noq.push(node(next, curr.deep + 1));
                    }
                }
            digit = curr.num % 100 / 10;
            temp = curr.num - digit * 10;
            for(i = 0; i < 10; i++)
                if(i != digit){
                    next = temp + i * 10;
                    if(next < 1000)
                        continue;
                    if(isprime(next) && !visit[next]){
                        visit[next] = true;
                        noq.push(node(next, curr.deep + 1));
                    }
                }
            digit = curr.num % 1000 / 100;
            temp = curr.num - digit * 100;
            for(i = 0; i < 10; i++)
                if(i != digit){
                    next = temp + i * 100;
                    if(next < 1000)
                        continue;
                    if(isprime(next) && !visit[next]){
                        visit[next] = true;
                        noq.push(node(next, curr.deep + 1));
                    }
                }
            digit = curr.num / 1000;
            temp = curr.num - digit * 1000;
            for(i = 0; i < 10; i++)
                if(i != digit){
                    next = temp + i * 1000;
                    if(next < 1000)
                        continue;
                    if(isprime(next) && !visit[next]){
                        visit[next] = true;
                        noq.push(node(next, curr.deep + 1));
                    }
                }
        }

        if(flag)
            printf("%d\n", curr.deep);
        else
            printf("Impossible\n");
        while(!noq.empty())
            noq.pop();
    }
    return 0;
}

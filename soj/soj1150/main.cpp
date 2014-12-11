
#include <stdio.h>
#include <queue>
#include <list>

using namespace std;

int opera_a(int state)
{
    int up = state / 10000;
    int dw = state % 10000;
    return dw * 10000 + up;
}

int opera_b(int state)
{
    int up = state / 10000;
    int dw = state % 10000;
    up =  up / 10 + (up % 10) * 1000;
    dw =  dw / 10 + (dw % 10) * 1000;
    return up * 10000 + dw;
}

int opera_c(int state)
{
    int one = state / 1000000 % 10;
    int two = state / 100000 % 10;
    int thr = state / 10 % 10;
    int fou = state / 100 % 10;
    int sub = one * 1000000 + two * 100000 + thr * 10 + fou * 100;
    int add = one * 100000 + two * 10 + thr * 100 + fou * 1000000;
    return state - sub + add;
}

struct node{
    int state;
    int deep;
    list<char> opera;
};

queue<node> qn;

int main()
{
    int max_deep, num, tmp, i;
    node first, next;
    bool flag;
    while (scanf("%d", &max_deep) != EOF && max_deep != -1) {
        num = 0;
        flag = false;
        for (i = 0; i < 8; i++) {
            scanf("%d", &tmp);
            num = num * 10 + tmp;
        }

        while(!qn.empty())
            qn.pop();
        first.state = 12348765;
        first.deep = 0;
        first.opera.clear();

        qn.push(first);
        while(!qn.empty()){
            first = qn.front();
            qn.pop();

            if (first.state == num) {
                flag = true;
                printf("%d ", first.deep);
                while(!first.opera.empty()){
                    printf("%c", first.opera.front());
                    first.opera.pop_front();
                }
                printf("\n");
                break;
            }
            if (first.deep == max_deep)
                continue;

            // opera A
            next.state = opera_a(first.state);
            next.deep = first.deep + 1;
            next.opera = first.opera;
            next.opera.push_back('A');
            //printf("%d %d\n", next.deep, next.state);
            qn.push(next);
            // opera B
            next.state = opera_b(first.state);
            next.deep = first.deep + 1;
            next.opera = first.opera;
            next.opera.push_back('B');
            //printf("%d %d\n", next.deep, next.state);
            qn.push(next);
            // opera C
            next.state = opera_c(first.state);
            next.deep = first.deep + 1;
            next.opera = first.opera;
            next.opera.push_back('C');
            //printf("%d %d\n", next.deep, next.state);
            qn.push(next);
        }
        if (!flag)
            printf("-1\n");
    }
    return 0;
}



#include <stdio.h>
#include <queue>
#include <list>
#include <string.h>

using namespace std;

int ktcode(int state)
{
    int digit[8];
    int i, base, num, j, c;
    for (i = 0, base = 1; i < 8; i++, base *= 10)
        digit[i] = state % (base * 10) / base;
    num = 0;
    for (i = 1, base = 1; i < 8; i++) {
        base = base * i;
        c = 0;
        for (j = 0; j < i; j++)
            if (digit[j] < digit[i])
                c++;
        num = num + c * base;
    }
    return num;
}

int opera_a(int state)
{
    int up = state / 10000;
    int dw = state % 10000;
    int up_1 = up / 100;
    int up_2 = up % 100;
    int dw_1 = dw / 100;
    int dw_2 = dw % 100;
    up = up_1 + up_2 * 100;
    dw = dw_1 + dw_2 * 100;
    return up * 10000 + dw;
}

int opera_b(int state)
{
    int up = state / 10000;
    int dw = state % 10000;
    up =  up / 1000 + (up % 1000) * 10;
    dw =  dw / 1000 + (dw % 1000) * 10;
    return up * 10000 + dw;
}

int opera_c(int state)
{
    int one = state / 1000000 % 10;
    int two = state / 100000 % 10;
    int thr = state / 10 % 10;
    int fou = state / 100 % 10;
    int sub = one * 1000000 + two * 100000 + thr * 10 + fou * 100;
    int add = one * 100 + two * 1000000 + thr * 100000 + fou * 10;
    return state - sub + add;
}

struct node{
    int state;
    int deep;
    list<char> opera;
};

queue<node> qn;
bool state_mark[40325];

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
        memset(state_mark, 0, sizeof(state_mark));

        while(!qn.empty())
            qn.pop();
        first.state = 12345678;
        state_mark[ktcode(first.state)] = true;
        first.deep = 0;
        first.opera.clear();

        qn.push(first);
        while(!qn.empty()){
            first = qn.front();
            qn.pop();

            if (first.state == num) {
                flag = true;
                printf("%d", first.deep);
                if(!first.opera.empty())
                    printf(" ");
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
            if (!state_mark[ktcode(next.state)]) {
                state_mark[ktcode(next.state)] = true;
                next.deep = first.deep + 1;
                next.opera = first.opera;
                next.opera.push_back('A');
                //printf("%d %d\n", next.deep, next.state);
                qn.push(next);
            }
            // opera B
            next.state = opera_b(first.state);
            if (!state_mark[ktcode(next.state)]) {
                state_mark[ktcode(next.state)] = true;
                next.deep = first.deep + 1;
                next.opera = first.opera;
                next.opera.push_back('B');
                //printf("%d %d\n", next.deep, next.state);
                qn.push(next);
            }
            // opera C
            next.state = opera_c(first.state);
            if (!state_mark[ktcode(next.state)]) {
                state_mark[ktcode(next.state)] = true;
                next.deep = first.deep + 1;
                next.opera = first.opera;
                next.opera.push_back('C');
                //printf("%d %d\n", next.deep, next.state);
                qn.push(next);
            }
        }
        if (!flag)
            printf("-1\n");
    }
    return 0;
}


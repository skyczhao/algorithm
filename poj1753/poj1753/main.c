
#include <stdio.h>
#include <string.h>
#include <queue>

int seq[4][2] = {0, -1, 0, 1, 1, 0, -1, 0};
int change_seq[16];
bool mark[70000];
int state, nums;
std::queue<int> qit;
std::queue<int> qit_time;

int change(int index)
{
    int num = 0;
    int x = index / 4;
    int y = index % 4;
    int row, col;
    for(int i = 0; i < 4; i++){
        row = x + seq[i][0];
        col = y + seq[i][1];
        if(row < 4 && col < 4)
            if(row >= 0 && col >= 0){
                num += (1 << (15 - row * 4 - col));
            }
    }
    num += (1 << (15 - x * 4 - y));
    return num;
}

int main()
{
    int i, j, next;
    char c, str[5];
    bool flag;
    // initial
    for(i = 0; i < 16; i++)
        change_seq[i] = change(i);
    memset(mark, 0, sizeof(mark));
    state = 0;
    // read state
    for(i = 0; i < 4; i++){
        gets(str);
        for(j = 0; j < 4; j++){
            if(str[j] == 'b')
                state += (1 << (15 - i * 4 - j));
        }
    }
    // BFS
    flag = true;
    nums = 0;
    if(state == 0 || state == 65535)
        flag = false;
    qit.push(state);
    qit_time.push(0);
    mark[state] = 1;
    while(!qit.empty() && flag){
        // get first
        state = qit.front();
        nums = qit_time.front();
        qit.pop();
        qit_time.pop();

        // jump
        if(state == 0 || state == 65535){
            flag = false;
            break;
        }

        // change
        nums++;
        for(i = 0; i < 16; i++){
            next = state ^ change_seq[i];
            if(!mark[next]){
                mark[next] = 1;
                qit.push(next);
                qit_time.push(nums);
            }
        }
    }
    if(flag)
        printf("Impossible\n");
    else
        printf("%d\n", nums);
    return 0;
}


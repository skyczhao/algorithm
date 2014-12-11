
#include <stdio.h>
#include <string.h>

char str[110];
int length;
//bool value[33][6];
bool flag;
bool stack_arr[110];

bool calculate(bool p, bool q, bool r, bool s, bool t)
{
    int i, curr;
    curr = 0;
    for(i = length - 1; i >= 0; i--){
        switch(str[i]){
            case 'p':
                stack_arr[curr] = p;
                curr++;
                break;
            case 'q':
                stack_arr[curr] = q;
                curr++;
                break;
            case 'r':
                stack_arr[curr] = r;
                curr++;
                break;
            case 's':
                stack_arr[curr] = s;
                curr++;
                break;
            case 't':
                stack_arr[curr] = t;
                curr++;
                break;
            case 'K':
                stack_arr[curr - 2] = stack_arr[curr - 1] && stack_arr[curr - 2];
                curr--;
                break;
            case 'A':
                stack_arr[curr - 2] = stack_arr[curr - 1] || stack_arr[curr - 2];
                curr--;
                break;
            case 'N':
                stack_arr[curr - 1] = !stack_arr[curr - 1];
                break;
            case 'C': // !!!
                stack_arr[curr - 2] = !stack_arr[curr - 1] || stack_arr[curr - 2];
                curr--;
                break;
            case 'E':
                stack_arr[curr - 2] = (stack_arr[curr - 1] == stack_arr[curr - 2]);
                curr--;
                break;
        }
    }
    return stack_arr[0];
}

int main()
{
    int i, j;
    //for(i = 0; i < 32; i++)
    //    for(j = 0; j < 5; j++)
    //        value[i][j] = (i & (1 << j)) >> j;
    while(scanf("%s", str) != EOF){
        if(str[0] == '0')
            break;
        length = strlen(str);

        flag = true;
        for(i = 0; i < 32; i++){
            if(!calculate((i & (1 << 0)) >> 0, (i & (1 << 1)) >> 1, (i & (1 << 2)) >> 2, (i & (1 << 3)) >> 3, (i & (1 << 4)) >> 4)){
                flag = false;
                break;
            }
        }

        if(flag)
            printf("tautology\n");
        else
            printf("not\n");
    }
    return 0;
}

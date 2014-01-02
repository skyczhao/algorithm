
#include <iostream>
#include <string.h>
#include <math.h>
using namespace std;

#define DIGITS 1000000000
int matrix[33][1000005];
int size;

int subfunc(int num, int level)
{
    if(level == 1){
        matrix[0][num] = 1;
        return 1;
    }

    int t_level = log((double)level) / log((double)2);
    if(matrix[t_level][num] != 0)
        return matrix[t_level][num];

    int t_sum = 0;
    int t_size = num / level;
    for(int t_i = 0; t_i <= t_size; t_i++){
        int t_num = num - t_i * level;
        if(matrix[t_level - 1][t_num] == 0){
            matrix[t_level - 1][t_num] = subfunc(t_num, level / 2);
            if(t_num % 2 == 0)
                matrix[t_level - 1][t_num + 1] = matrix[t_level - 1][t_num];
            else
                matrix[t_level - 1][t_num - 1] = matrix[t_level - 1][t_num];
        }
        t_sum += matrix[t_level - 1][t_num];
        t_sum %= DIGITS;
    }

    return t_sum;
}

int main()
{
    int num, i;
    memset(matrix, 0, sizeof(matrix));

    while(cin >> num){
        size = 0;
        for(i = 1; i <= num; i *= 2){
            if(i == 1)
                size++;
            else{
                size = size + subfunc(num - i, i);
                size %= DIGITS;
            }
        }
        cout << num << ":" << size << endl;
    }
    return 0;
}


#include <stdio.h>
#include <string.h>
#include <math.h>

char p[105];
char res[105];
int ndex;
char con_nul[105];

char* itoa(int num, char* str, int base)
{
    for(int i = 0; num && i < 10; i++, num /= base)
        str[i] = "0123456789abcdef"[num % base];
    return str;
}

char* stradd(char* res, char* num)
{
    int i, inc, sum, size;
    size = strlen(num);
    inc = 0;
    for(i = 0; i < size; i++){
        sum = res[i] - '0' + num[i] - '0' + inc;
        res[i] = sum % 10 + '0';
        inc = sum / 10;
    }
    size = strlen(res);
    while(inc != 0 && i < size){
        sum = res[i] - '0' + inc;
        res[i] = sum % 10 + '0';
        inc = sum / 10;
        i++;
    }
    return res;
}

char* multen(char* res, int exp)
{
    int i, size;
    size = strlen(res);
    for(i = size - 1; i >= 0; i--)
        res[i + exp] = res[i];
    for(i = 0; i < exp; i++)
        res[i] = '0';
    return res;
}

int cmp()
{
    int i, size_res, size_p;
    for(i = 104; i >= 0; i--)
        if(res[i] > '0')
            break;
    size_res = i + 1;
    size_p = strlen(p);
    if(size_res > size_p)
        return 1;
    if(size_res < size_p)
        return 2;
    for(i = 0; i < size_p; i++){
        if(res[size_res - 1 - i] > p[i])
            return 1;
        if(res[size_res - 1 - i] < p[i])
            return 2;
    }
    return 0;
}

void calculate(int k)
{
    int i, j;
    int tmp_num;
    char tmp_mul[105];
    char tmp_sum[105];
    memcpy(res, con_nul, 105 * sizeof(char));
    itoa(k, res, 10);
    memcpy(tmp_sum, con_nul, 105 * sizeof(char));
    for(i = 1; i < ndex; i++){
        for(j = 0; j < 105; j++){
            tmp_num = k * (res[j] - '0');
            memset(tmp_mul, 0, 105 * sizeof(char));
            itoa(tmp_num, tmp_mul, 10);
            multen(tmp_mul, j);
            stradd(tmp_sum, tmp_mul);
        }
        memcpy(res, tmp_sum, 105 * sizeof(char));
        memcpy(tmp_sum, con_nul, 105 * sizeof(char));
    }
}

int main()
{
    int mid, left, right, res_cmp, i;
    int size;
    for(i = 0; i < 105; i++)
        con_nul[i] = '0';
    while(scanf("%d", &ndex) != EOF){
        scanf("%s", p);
        size = strlen(p);
        size = size / ndex;
        right = pow(10, size + 1);
        left = pow(10, ((size - 1) > 0 ? (size - 1) : 0));
        while(right >= left){
            mid = (left + right) / 2;
            calculate(mid);

            res_cmp = cmp();
            if(res_cmp == 0)
                break;
            if(res_cmp == 1)
                right = mid;
            if(res_cmp == 2)
                left = mid + 1;
        }
        printf("%d\n", mid);
    }
    return 0;
}

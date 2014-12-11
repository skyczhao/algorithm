
#include <stdio.h>

int main()
{
    int size, length;
    int i, j, curr, a, b;
    int num;
    bool quo[45];

    scanf("%d", &size);
    while(size--){
        scanf("%d", &length);
        curr = 0;
        b = 0;
        for(i = 0; i < length; i++){
            a = b;
            scanf("%d", &b);
            a = b - a;
            for(j = 0; j < a; j++)
                quo[curr++] = false;
            quo[curr++] = true;
        }

        length = length << 1;
        for(i = 0; i < length; i++){
            if(quo[i]){
                curr = 1;
                j = i - 1;
                num = 0;
                while(curr > 0){
                    if(!quo[j]){
                        curr--;
                        num++;
                    }
                    else
                        curr++;
                    j--;
                }
                if(i < length - 1)
                    printf("%d ", num);
                else
                    printf("%d\n", num);
            }
        }
    }
    return 0;
}

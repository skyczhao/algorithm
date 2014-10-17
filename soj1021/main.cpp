
#include <stdio.h>
#include <stack>

int array[200008];

int main()
{
    int size, i, x, y;
    std::stack<int> ss;

    scanf("%d", &size);
    while(size > 0){
        while(!ss.empty())
            ss.pop();
        for(i = 0; i < size; i++){
            scanf("%d %d", &x, &y);
            array[x] = y;
            array[y] = x;
        }

        for(i = 1; i <= 2 * size; i++){
            if(!ss.empty() && ss.top() == array[i])
                ss.pop();
            else
                ss.push(i);
        }

        if(ss.empty())
            printf("Yes\n");
        else
            printf("No\n");

        scanf("%d", &size);
    }
    return 0;
}



#include <stdio.h>
#include <math.h>
#include <algorithm>
#include <string.h>

struct node{
    double begin;
    double end;
};
node interval[1005];
bool mark[1005];

bool cmp(node left, node right)
{
    if(left.end < right.end)
        return true;
    if(left.end > right.end)
        return false;
    if(left.begin > right.begin)
        return true;
    return false;
}

int main()
{
    int size, dist;
    double root, curr;
    int index, num;
    int i, j, x, y;
    bool flag;

    index = 0;
    while(scanf("%d %d", &size, &dist) != EOF){
        if(size == 0 && dist == 0)
            break;
        // get
        if(dist >= 0){
            dist *= dist;
            flag = true;
        }
        else
            flag = false;
        for(i = 0; i < size; i++){
            scanf("%d %d", &x, &y);
            root = dist - y * y;
            if(flag && root >= 0){
                root = sqrt(root);
                interval[i].begin = x - root;
                interval[i].end = x + root;
            }
            else
                flag = false;
        }

        // handle
        if(flag){
            num = 0;
            std::sort(interval, interval + size, cmp);
            memset(mark, 0, sizeof(mark));
            // search each one
            for(i = 0; i < size; i++){
                if(!mark[i]){
                    num++;
                    mark[i] = true;
                    curr = interval[i].end;
                    // calculate cover
                    for(j = 0; j < size; j++){
                        if(!mark[j]){
                            if(interval[j].begin <= curr)
                                mark[j] = true;
                        }
                    }
                }
            }
        }
        else
            num = -1;

        // output
        index++;
        printf("Case %d: %d\n", index, num);
    }
    return 0;
}

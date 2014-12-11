
#include <stdio.h>
#include <vector>
using namespace std;

int pairs[2][205];
vector<int> now, left;

bool isCover(int m, int n)
{
    if((pairs[0][m] + 1) / 2 > (pairs[1][n] + 1) / 2)
        return false;
    if((pairs[0][n] + 1) / 2 > (pairs[1][m] + 1) / 2)
        return false;
    return true;
}

int main()
{
    int exams, size;
    int i, temp, sum;
    bool flag;
    vector<int>::iterator vit;
    scanf("%d", &exams);
    while(exams--){
        now.clear();
        left.clear();
        // read input
        scanf("%d", &size);
        for(i = 0; i < size; i++){
            left.push_back(i);
            scanf("%d %d", &pairs[0][i], &pairs[1][i]);
            if(pairs[0][i] > pairs[1][i]){
                temp = pairs[0][i];
                pairs[0][i] = pairs[1][i];
                pairs[1][i] = temp;
            }
        }

        // compute
        sum = 0;
        while(!left.empty()){
            //printf("now:");
            //for(i = 0; i < now.size(); i++)
            //    printf("%d ", now[i]);
            //printf("\n");
            //printf("left:");
            //for(i = 0; i < left.size(); i++)
            //    printf("%d ", left[i]);
            //printf("\n");

            // get the first position
            vit = left.begin();
            now.clear();
            now.push_back(*vit);
            vit = left.erase(vit);
            // try add more
            while(vit != left.end()){
                // judgement
                flag = false;
                //printf("now size:%d\n", now.size());
                for(i = 0; i < now.size(); i++)
                    if(isCover(*vit, now[i])){
                        flag = true;
                        break;
                    }
                if(flag)
                    vit++;
                else{
                    now.push_back(*vit);
                    vit = left.erase(vit);
                }
            }
            sum++;
        }
        printf("%d0\n", sum);
    }
    return 0;
}

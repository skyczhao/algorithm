
#include <stdio.h>
#include <math.h>
#define MAX 9999999

struct point{
    int x;
    int y;
};
point pos[205];
bool flag[205];
float frog[205];

int main()
{
    int size, mark, i;
    int index, t_pow;
    float min, t_sqr;
    mark = 0;
    while(scanf("%d", &size) != EOF){
        mark++;
        if(size <= 0)
            break;
        for(i = 0; i < size; i++){
            scanf("%d %d", &pos[i].x, &pos[i].y);
            flag[i] = true;
            frog[i] = MAX;
        }

        frog[0] = 0;
        while(1){
            min = MAX;
            index = -1;
            for(i = 0; i < size; i++){
                if(flag[i] && frog[i] < min){
                    min = frog[i];
                    index = i;
                }
            }
            //printf("%d %f\n", index, min);
            if(index == -1)
                break;
            if(index == 1)
                break;
            flag[index] = false;

            for(i = 0; i < size; i++){
                t_pow = (pos[index].x - pos[i].x) * (pos[index].x - pos[i].x);
                t_pow += ((pos[index].y - pos[i].y) * (pos[index].y - pos[i].y));
                t_sqr = sqrt(t_pow);
                if(t_sqr < frog[index])
                    t_sqr = frog[index];
                if(t_sqr < frog[i])
                    frog[i] = t_sqr;
            }
        }
        printf("Scenario #%d\n", mark);
        printf("Frog Distance = %.3f\n", frog[1]);
        printf("\n", mark);
    }
    return 0;
}

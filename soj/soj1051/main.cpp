
#include <stdio.h>
const double pi = 3.1415927;
int main()
{
    double dia, tim;
    int rev, mark;
    double dist, mph;
    mark = 0;
    while(1){
        scanf("%lf %d %lf", &dia, &rev, &tim);
        if(rev == 0)
            break;

        dist = pi * dia / 12;
        dist = dist * rev / 5280;
        mph = dist / tim * 60 * 60;
        printf("Trip #%d: %.2f %.2f\n", ++mark, dist, mph);
    }
    return 0;
}


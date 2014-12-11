
//#include <stdio.h>
//#include <math.h>
//
//int main()
//{
//    double x, y;
//    while(scanf("%lf %lf", &x, &y) != EOF)
//        printf("%.0lf\n", pow(y, 1.0 / x));
//    return 0;
//}

#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    double n, p;
    while(cin >> n >> p)
        cout << pow(p, 1.0 / n) << endl;
    return 0;
}

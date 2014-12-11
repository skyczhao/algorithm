#include <stdio.h>
#include <math.h>

#define Exp 2.718281828

// 关键是看清楚题目,已知任意两个求另外一个!
int main()
{
    const double p[] = {0.5555, 6.11, 5417.7530, 273.16, 10.0};
    char type1, type2;
    double num1, num2;
    double T, D, H;
    while(1){
        scanf(" %c", &type1);
        if(type1 == 'E')
            break;
        scanf("%lf", &num1);
        scanf(" %c", &type2);
        scanf("%lf", &num2);
        if(type1 == 'T' && type2 == 'D'){
            T = num1;
            D = num2;
            H = pow(Exp, p[2] * (1 / p[3] - 1 / (p[3] + D)));
            H = p[1] * H - p[4];
            H = T + p[0] * H;
        }
        else if (type1 == 'T' && type2 == 'H'){
            T = num1;
            H = num2;
            D = (H - T) / p[0];
            D = (D + p[4]) / p[1];
            D = log(D);
            D = 1 / p[3] - D / p[2];
            D = 1 / D - p[3];
        }
        else if (type1 == 'D' && type2 == 'H'){
            D = num1;
            H = num2;
            T = pow(Exp, p[2] * (1 / p[3] - 1 / (p[3] + D)));
            T = p[1] * T - p[4];
            T = H - p[0] * T;
        }
        else if (type1 == 'D' && type2 == 'T'){
            T = num2;
            D = num1;
            H = pow(Exp, p[2] * (1 / p[3] - 1 / (p[3] + D)));
            H = p[1] * H - p[4];
            H = T + p[0] * H;
        }
        else if (type1 == 'H' && type2 == 'T'){
            T = num2;
            H = num1;
            D = (H - T) / p[0];
            D = (D + p[4]) / p[1];
            D = log(D);
            D = 1 / p[3] - D / p[2];
            D = 1 / D - p[3];
        }
        else if (type1 == 'H' && type2 == 'D'){
            D = num2;
            H = num1;
            T = pow(Exp, p[2] * (1 / p[3] - 1 / (p[3] + D)));
            T = p[1] * T - p[4];
            T = H - p[0] * T;
        }
        else
            break;
        printf("T %.1f D %.1f H %.1f\n", T, D, H);
    }
    return 0;
}

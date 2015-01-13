
//#include <iostream>
//using namespace std;

#include <stdio.h>

int main()
{
    int size;
    int N, A, B;
    scanf("%d", &size);
    while (size--) {
        scanf("%d %d %d", &N, &A, &B);
        if (N % (A + B) <= A && N % (A + B) > 0)
            printf("Bob\n");
        else
            printf("Alice\n");
    }
    return 0;
}

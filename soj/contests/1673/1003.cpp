
#include <iostream>
#include <iomanip>
#include <cstring>
using namespace std;

double matrix[12][402];
double map[41];

void calMap()
{
    int i = 0;
    while (i < 10)
        map[i++] = 2.0;
    while (i < 15)
        map[i++] = 2.5;
    while (i < 20)
        map[i++] = 3;
    while (i < 25)
        map[i++] = 3.5;
    while (i <= 40)
        map[i++] = 4;
}

int main()
{
    int size;
    int avg, n, all;
    calMap();

    cin >> size;
    while (size--) {
        cin >> avg >> n;
        all = (avg - 60) * n;

        int i, j, score;
        double maximum, minimum;

        memset(matrix, 0, sizeof(matrix));
        // initialize
        for (j = 0; j <= all; j++) {
            if (j <= 40) {
                matrix[0][j] = map[j];
            }
        }
        // each course
        for (i = 1; i < n; i++) {
            // the score can be all!
            for (j = 0; j <= all; j++) {
                // for each possible score
                for (score = 0; score <= 40; score++) {
                    // the left score must greater than current score
                    // and the last score must be aviable
                    if (j >= score && matrix[i - 1][j - score] > 0)
                        // state transform
                        if (matrix[i][j] < matrix[i - 1][j - score] + map[score]) {
                            matrix[i][j] = matrix[i - 1][j - score] + map[score];
                        }
                }
            }
        }
        maximum = matrix[n - 1][all];

        for (i = 0; i < n; i++)
            for (j = 0; j <= all; j++)
                matrix[i][j] = 60;
        // initialize
        for (j = 0; j <= all; j++) {
            if (j <= 40) {
                matrix[0][j] = map[j];
            }
        }
        // each course
        for (i = 1; i < n; i++) {
            // the score can be all!
            for (j = 0; j <= all; j++) {
                // for each possible score
                for (score = 0; score <= 40; score++) {
                    // the left score must greater than current score
                    // and the last score must be aviable
                    if (j >= score && matrix[i - 1][j - score] < 45)
                        // state transform
                        if (matrix[i][j] > matrix[i - 1][j - score] + map[score]) {
                            matrix[i][j] = matrix[i - 1][j - score] + map[score];
                        }
                }
            }
        }
        minimum = matrix[n - 1][all];

        cout << fixed << setprecision(4) << minimum / n << " " << maximum / n << endl;
    }
    return 0;
}

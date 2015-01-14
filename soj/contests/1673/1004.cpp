
#include <iostream>
using namespace std;

int mark[5002][5002];
int volume[5002];
int cost[5002];

int miniOCD(int left, int right) 
{
    if (left >= right)
        return 0;
    if (mark[left][right] != -1)
        return mark[left][right];

    long long int leftSum, rightSum;
    int leftIndex, rightIndex;
    int miniCost, tmpCost;
    int i;

    miniCost = cost[right - left];
    leftIndex = 0;
    leftSum = volume[left];
    rightIndex = 0;
    rightSum = volume[right];
    while (left + leftIndex < right - rightIndex) {
        if (leftSum < rightSum) {
            leftIndex++;
            leftSum += volume[left + leftIndex];
        } else if (leftSum > rightSum) {
            rightIndex++;
            rightSum += volume[right - rightIndex];
        } else {
            tmpCost = miniOCD(left + leftIndex + 1, right - rightIndex - 1);
            tmpCost += cost[leftIndex] + cost[rightIndex];
            if (tmpCost < miniCost) {
                miniCost = tmpCost;
            }

            leftIndex++;
            leftSum += volume[left + leftIndex];
            rightIndex++;
            rightSum += volume[right - rightIndex];
        }
    }
    mark[left][right] = miniCost;
    return miniCost;
}

int main()
{
    int size;
    int i, j, tmp;
    cin >> size;

    for (i = 0; i < size; i++) {
        cin >> volume[i];
    }
    for (i = 0; i < size; i++) {
        cin >> cost[i];
    }
    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            mark[i][j] = -1;
        }
    }
    cout << miniOCD(0, size - 1) << endl;
    return 0;
}


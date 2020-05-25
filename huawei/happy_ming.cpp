#include <iostream>
#include <cstring>
using namespace std;

void GetResult(int *p, int& Get_Result)
{	 
    int total = p[0];
    int size = p[1];

    // 预计算
    int *value = new int[size];
    for (int i = 0; i < size; i++) {
        value[i] = p[i * 2 + 2] * p[i * 2 + 3];
    }
    // DP数组
    int *result = new int[total + 1];
    memset(result, 0, (total + 1) * sizeof(int));

    for (int pick = 0; pick < size; pick++) {
        int priceIdx = pick * 2 + 2;
        // 倒序, 避免被覆盖
        for (int step = total; step > 0; step--) {
            int stepLeft = step - p[priceIdx];
            if (stepLeft < 0) {
                continue;
            }

            // 转移函数
            int tmpResult = result[stepLeft] + value[pick];
            if (tmpResult > result[step]) {
                result[step] = tmpResult;
            }
        }
    }

    //	在这里实现功能
    Get_Result = result[total];

    delete result;
    delete value;
} 


int main() {
    int total, size;
    cin >> total >> size;

    int *mat = new int[size * 2 + 2];
    mat[0] = total;
    mat[1] = size;
    for (int i = 0; i < size; i++) {
        cin >> mat[i * 2 + 2];
        cin >> mat[i * 2 + 3];
    }

    // for (int i = 0; i <= size; i++) {
    //     cout << mat[i * 2] << " " << mat[i * 2 + 1] << endl;
    // }
    int result = 0;
    GetResult(mat, result);
    cout << result << endl;

    delete mat;
    return 0;
    
    // 1000 5 800 2 400 5 300 5 400 3 200 2
    // 3900
}
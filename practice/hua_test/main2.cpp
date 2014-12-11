//#include<stdio.h>
//
//int list[255];
//
//// num: 要分割的原始数字, n: 当前要分割的数字, k: 已经分割出的数量, sum: 已分割出的数字之和
//void split(int num, int n = 0, int k = 0, int sum = 0){
//    // 如果当前要分割的数字未传入，则当前分割的数字即为要分割的原始数字，减少传入参数数量
//    if(n == 0){
//        n =  num;
//    }
//    // 如果已分割的数字之和等于要分割的原始数字，即分割完毕
//    if(sum + n == num){
//        // 将最后一个数字存入数组
//        list[k] = n;
//        // 判断已分割的数字是否后面小于前面，防止重复
//        bool flag = true;
//        // 暂时未想到更好办法去除重复项，每次都要遍历数组，性能较低
//        for(int i = 1; i <= k; ++i){
//            if(list[i] > list[i - 1]){
//                flag = false;
//            }
//        }
//        // 如果数组合法，则输出
//        //if(flag){
//        //    printf("%d", list[0]);
//        //    for(int i = 1; i <= k; ++i){
//        //        printf("+%d", list[i]);
//        //    }
//        //    printf("\n");
//        //}
//    }
//    // 从大到小进行分割
//    for(int i = 1; i < n; ++i){
//        // 分割出的数字存入数组
//        list[k] = n - i;
//        // 分割剩下的数字继续分割
//        split(num, i, k + 1, sum + n - i);
//    }
//}
//
//int main(){
//    int n;
//    while(~scanf("%d", &n)){
//        split(n);
//    }
//    return 0;
//}
//

#include<stdio.h>

int list[255];
int num;

void split(int n, int m = 0){
    int i;
    // 如果分割完毕
    if(n==0){
        num++;
        num %= 1000000000;
    }
    //if(n == 0){
    //    // 遍历输出数组
    //    for(i = 0; i < m; ++i){
    //        printf("%d ", list[i]);
    //    }
    //    printf("\n");
    //    return;
    //}
    // 由大到小进行分割
    for(i = n; i > 0; --i){
        // 如果未分割或当期分割的数字不大于上一个分割的数字
        if(m == 0 || i <= list[m - 1]){
            // 将分割的数字存入数组
            list[m] = i;
            // 分割剩下的数字
            split(n - i, m + 1);
        }
    }
}

int main(){
    int n;
    while(~scanf("%d", &n)){
        num = 0;
        split(n);
        printf("%d\n", num);
    }
    return 0;
}

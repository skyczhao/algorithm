package huawei;

import java.util.Scanner;

/**
 * called_children
 * 借用数学性质实现, 而并非粗暴的模拟:
 * a[i]表示第i个孩子的分数
 * s[i]表示从第一个孩子到第i个孩子（包括第i个）的分数之和
 * 则第j个到第i个的分数的平均值为（s[j]-s[i-1]）/(j-i+1)，即直角坐标系上的（i-1，s[i-1]）.(j,s[j])两点连线的斜率。
 *
 * 所以平均值大于或等于b，就是斜率大于或等于b。
 * 那么判断从第i个人开始选有几种选法，过（i-1，s[i-1]）做一条斜率为b的直线(与y轴交点为y[i-1]),有几个点是在它的上方。
 * 根据直线的性质，将这条直线平移到所判断的点（j,s[j]），与y轴的交点为y[j]，如果y[j]<y[i-1]，则不能选，否则就可以选。
 * 因而就可以通过判断y[]（y[0]=0）这一组数据里，y[i]>=y[j]的对数。
 *
 * TBC: 待实现下面的思路
 * 也可以用总的（n+1）*n/2减去逆序对（不包括相等的情况）。
 *
 * @since 2020-06-03
 */
public class called_children {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int b = in.nextInt();

        int[] array = new int[n];
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
            sum[i + 1] = sum[i] + array[i];
        }
        in.close();

        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int y = b * (j - i + 1) + sum[i];
                if (sum[j + 1] >= y) { // BUG 1: index no correct; BUG 2: bigger or equal;
                    total++;
                }
            }
        }
        System.out.println(total);

    }

    // 5 9
    // 32 4 9 21 10
    // res: 13
}

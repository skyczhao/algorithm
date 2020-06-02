package huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * gpa_add
 * 1. BUG1: 题意没抓清楚, 1-要求计算的是最大的提升值, 而不是最大的结果; 2-把绩点与学分两个变量弄混
 *     - 其实在做之前没能正确手动算出, 就应该知道自己是没理解题意, 而不应该提交去测试
 * 2. BUG2: 输出小数位数需要格式化, 这个点都不肯做就有点自以为是了
 * 
 * DP的小总结
 * 1. 最经典的01背包问题需要记住要求以及解法
 * 2. 结合题意选择合适的遍历条件和转移函数
 * 3. 会有点绕(复杂), 注意备注好各个变量避免混淆
 *
 * @since 2020-06-02
 */
public class gpa_add {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int c = in.nextInt();
        int n = in.nextInt();
        int x = in.nextInt();

        int[] levels = new int[c];
        // 绩点
        double[] grades = new double[c];
        for (int i = 0; i < c; i++) {
            levels[i] = in.nextInt();
            grades[i] = in.nextDouble();
        }

        int[] scores = new int[n];
        // 学分
        double[] points = new double[n];
        double totalPoints = 0;
        for (int i = 0; i < n; i++) {
            scores[i] = in.nextInt();
            points[i] = in.nextDouble();

            totalPoints += points[i];
        }
        in.close();

        double[][] total = new double[n][x + 2];
        // 逐门学科测试
        for (int course = 0; course < n; course++) {
            // 该学科学分
            double point = points[course];

            // 以加分为条件进行遍历
            for (int available = 0; available <= x; available++) {
                double maxTotal = 0;

                if (course < 1) {
                    // 加分后的绩点
                    double newGrade = getGrade(scores[course] + available, levels, grades);
                    // 计算总绩点
                    maxTotal = newGrade * point;
                } else {
                    // 转移方程
                    // 在可用范围内, 加给当前科目不同分数下的最大GPA
                    for (int using = 0; using <= available; using++) {
                        // 加分后的绩点
                        double newGrade = getGrade(scores[course] + using, levels, grades);

                        double tmpTotal = total[course - 1][available - using] + newGrade * point;
                        if (tmpTotal > maxTotal) {
                            maxTotal = tmpTotal;
                        }
                    }
                }

                total[course][available] = maxTotal;
            }

            // 打印结果测试
//            for (int i = 0; i <= x; i++) {
//                System.out.print(total[course][i] + ", ");
//            }
//            System.out.println("===");
        }
        System.out.println(String.format("%.4f", (total[n - 1][x] - total[n - 1][0]) / totalPoints));
    }

    /**
     * 根据成绩求绩点
     *
     * @param score
     * @param levels
     * @param grades
     * @return
     */
    private static double getGrade(int score, int[] levels, double[] grades) {
        double grade = 0;
        for (int i = 0; i < levels.length; i++) {
            if (score >= levels[i]) {
                grade = grades[i];
            } else {
                break;
            }
        }
        return grade;
    }

    // 3 3 2
    // 82 3.3
    // 85 3.7
    // 90 4.0
    // 88 3.5
    // 84 2.0
    // 84 1.0
    // 0.1846
}

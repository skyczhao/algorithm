package huawei;

import java.util.Scanner;

/**
 * fibonacci
 *
 * @since 2020-04-27
 */
public class fibonacci {
    /**
     * 考察: 1. 斐波那契; 2. 状态转移+滚动数组; 3. 整数溢出; 4. 超时;
     *
     * 思路转变: 矩阵类算法题, 超过之前学习过的树/DP/数论/模拟等等、
     * 1. 构建矩阵
     * 2. 快速幂推算, 参考: quick_pow
     * 3. 矩阵快速幂推算: 看起来麻烦而已, 实际计算量是大大降低
     * 4. 矩阵快速幂次数:
     *     4.1 不考虑大数除法, 整数溢出, 就利用长整数
     *     4.2 中间值会超过整数
     * 5. 观察优化!
     *
     * @param args
     */
    public static void main(String[] args) {
        int threshold = 100000007;
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long m = in.nextLong();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        long length = (m - 1) / n;
        int left = Math.toIntExact((m - 1) % n);

        int[] init = new int[n];
        // 本应该init[(j + 1) % n][i], 但因为对称
        init[1 % n] = 1;
        // 本应该init[(j - 1) % n][i], 但因为对称
        init[(n - 1) % n] = 1;

        int[] mat = quick_square_symmetry_matrix_pow(init, length, threshold);

        long result = 0;
        for (int i = 0; i < n; i++) {
            // 乘法越界整数
            result += arr[i] * (long) mat[(i + n - left) % n] % threshold;
            // 加法也可能越界整数
            result %= threshold;
        }
//        print_matrix(mat);

        System.out.println(result % threshold);
    }

    public static void main4(String[] args) {
        int n = 5;

        int[][] init = new int[n][];
        int[] init_quick = new int[n];
        for (int i = 0; i < n; i++) {
            init[i] = new int[n];
            // 本应该init[(j + 1) % n][i], 但因为对称
            init[i][(i + 1) % n] = 1;
            // 本应该init[(j - 1) % n][i], 但因为对称
            init[i][(i + n - 1) % n] = 1;
        }
        init_quick[1 % n] = 1;
        init_quick[(n - 1) % n] = 1;

        int[][] t;
        int[] t_quick;
        t = quick_square_matrix_pow(init, 0, 1000000);
        print_matrix(t);
        System.out.println("----");
        t_quick = quick_square_symmetry_matrix_pow(init_quick, 0, 1000000);
        for (int v : t_quick) {
            System.out.print(v + ", ");
        }
        System.out.println();
        System.out.println();

        t = quick_square_matrix_pow(init, 1, 1000000);
        print_matrix(t);
        System.out.println("----");
        t_quick = quick_square_symmetry_matrix_pow(init_quick, 1, 1000000);
        for (int v : t_quick) {
            System.out.print(v + ", ");
        }
        System.out.println();
        System.out.println();

        t = quick_square_matrix_pow(init, 2, 1000000);
        print_matrix(t);
        System.out.println("----");
        t_quick = quick_square_symmetry_matrix_pow(init_quick, 2, 1000000);
        for (int v : t_quick) {
            System.out.print(v + ", ");
        }
        System.out.println();
        System.out.println();

        t = quick_square_matrix_pow(init, 3, 1000000);
        print_matrix(t);
        System.out.println("----");
        t_quick = quick_square_symmetry_matrix_pow(init_quick, 3, 1000000);
        for (int v : t_quick) {
            System.out.print(v + ", ");
        }
        System.out.println();
        System.out.println();

        t = quick_square_matrix_pow(init, 4, 1000000);
        print_matrix(t);
        System.out.println("----");
        t_quick = quick_square_symmetry_matrix_pow(init_quick, 4, 1000000);
        for (int v : t_quick) {
            System.out.print(v + ", ");
        }
        System.out.println();
        System.out.println();

    }

    public static void main3(String[] args) {
        int[][] mat = {{1, 2}, {3, 4}};
        int[][] right = {{2, 3}, {4, 5}};
        int[][] result = matrix_multi(mat, right, 10);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void main2(String[] args) {
        System.out.println(quick_pow(2, 10, 1000000000));
        System.out.println(quick_pow(3, 6, 1000000000));
        System.out.println(quick_pow(3, 8, 1000000000));
        System.out.println(quick_pow(3, 10, 1000000000));
    }

    private static void print_matrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + ", ");
            }
            System.out.println();
        }
    }

    private static int[] quick_square_symmetry_matrix_pow(int[] x, long n, int threshold) {
        int[] total = new int[x.length];
        int[] base = new int[x.length];

        total[0] = 1;
        for (int i = 0; i < x.length; i++) {
            base[i] = x[i];
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                // 顺序有影响, 但这里没关系
                total = matrix_symmetry_multi(total, base, threshold);
            }

            n = n >> 1;
            base = matrix_symmetry_multi(base, base, threshold);
        }

        return total;
    }

    private static int[][] quick_square_matrix_pow(int[][] x, long n, int threshold) {
        int[][] total = new int[x.length][];
        int[][] base = new int[x.length][];

        for (int i = 0; i < x.length; i++) {
            total[i] = new int[x.length];
            total[i][i] = 1;

            base[i] = new int[x.length];
            for (int j = 0; j < x[i].length; j++) {
                base[i][j] = x[i][j];
            }
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                // 顺序有影响, 但这里没关系
                total = matrix_multi(total, base, threshold);
            }

            n = n >> 1;
            base = matrix_multi(base, base, threshold);
        }

        return total;
    }

    /**
     * 双对称矩阵的乘法的处理
     *
     * @param left
     * @param right
     * @param threshold
     * @return
     */
    private static int[] matrix_symmetry_multi(int[] left, int[] right, int threshold) {
        int length = left.length;

        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i] += left[j] * (long) right[(j - i + length) % length] % threshold;
                result[i] %= threshold;
            }
        }

        return result;
    }

    /**
     * 矩阵乘法
     *
     * @param left
     * @param right
     * @param threshold
     * @return
     */
    private static int[][] matrix_multi(int[][] left, int[][] right, int threshold) {
        int length = left.length;
        int[][] result = new int[length][];

        for (int i = 0; i < length; i++) {
            result[i] = new int[length];

            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    // 乘法越界整数
                    result[i][j] += left[i][k] * (long) right[k][j] % threshold;
                    // 加法也可能越界整数
                    result[i][j] %= threshold;
                }
            }

        }

        return result;
    }

    /**
     * 一般的快速幂
     * https://blog.csdn.net/Harington/article/details/87602682
     *
     * @param x
     * @param n
     * @param threshold
     * @return
     */
    private static int quick_pow(int x, int n, int threshold) {
        int total = 1;

        int base = x;
        while (n > 0) {
            if ((n & 1) == 1) {
                // 1. 乘法, 指数才是加法
                // 2. 乘法, 结果余数等于每次取余数
                total = total * base % threshold;
            }

            // 移位操作, 效率高于除2
            n = n >> 1;
            // 下一位的基底: 相当于base^(2^n)
            base *= base;
        }

        return total;
    }

}

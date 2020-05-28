package huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * difficult arrangements
 *
 * @since 2020-04-27
 */
public class difficult_arrangements {
    /**
     * 考察: 问题转换, 转为常见的排列组合(排列组合忘了? 参考 https://blog.csdn.net/qwb492859377/article/details/50654627)
     * 1. n个球放进m个盒子问题, 转化为的组合问题为"隔板子"
     * 2. 复习数学!
     * 3. 利用转移公式: C(a, b) = C(a-1, b) + C(a-1, b-1)
     * 4. 注意边界为5201314, 越界问题
     * 5. 注意观察到大量重复计算, 使用cache缓存下来
     *
     * @param args
     */
    private static final int BOUNDARY = 5201314;
    private static Map<String, Integer> history = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int m = in.nextInt();
            int n = in.nextInt();
            int k = in.nextInt();

            n = n - m * k;
            // C(n + m - 1, m - 1)
            int a = n + m - 1;
            int b = m - 1;

            // System.out.println(a + ", " + b);
            System.out.println(C(a, b));
        }
    }

    private static int C(int a, int b) {
        if (a < b) {
            return 0;
        }
        if (a == b) {
            return 1;
        }
        if (b == 1) {
            return a % BOUNDARY;
        }

        String key = a + "," + b;
        if (history.containsKey(key)) {
            return history.get(a + "," + b);
        }

        int value = (C(a - 1, b) + C(a - 1, b - 1)) % BOUNDARY;
        // System.out.println(a + "," + b + "," + value);
        history.put(a + "," + b, value);
        return value;
    }
}

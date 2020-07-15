package huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * common_divisor_plus
 *
 * @since 2020-07-15
 */
public class common_divisor_plus {

    static int PRIME_SIZE = 3000;
    static int[] primes = new int[PRIME_SIZE];

    /**
     * 求最大公约数方法
     * 1. 辗转相除法
     * 2. 更相减损术
     * 3. 因式分解法-采用
     * 
     * 思路
     * 1. 借用因式分解躲避大数
     * 2. 利用因式分解逐个计算最大公约数
     * 
     * 其他
     * 数值法证明, 本题最大公约数是2的n次方, 转为求n
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int idx = 0;
        for (int i = 0; i < 20000; i++) {
            if (is_prime(i)) {
                primes[idx++] = i;
            }
        }
        while (idx < PRIME_SIZE) {
            primes[idx++] = 20001;
        }

        while (in.hasNext()) {
            int n = in.nextInt();

            System.out.println(cal_max_divisor(n));
//            Cal(n);
        }

        in.close();
    }

    static boolean is_prime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        int mid = (int) (Math.sqrt(num)) + 1;
        for (int i = 3; i < mid; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static Map<Integer, Integer> factor_divide(int n) {
        Map<Integer, Integer> factors = new HashMap<>();
        while (!is_prime(n) && n > 1) {
            for (int prime : primes) {
                if (prime * prime > n) {
                    break;
                }

                int times = 0;
                while (n % prime == 0) {
                    n /= prime;
                    times++;
                }

                if (times > 0) {
                    map_add(factors, prime, times);
                }
            }
        }
        if (n > 1) {
            map_add(factors, n, 1);
        }
        return factors;
    }

    static void map_add(Map<Integer, Integer> map, int key, int num) {
        int value = 0;
        if (!map.containsKey(key)) {
            map.put(key, 0);
        } else {
            value = map.get(key);
        }

        map.put(key, value + num);
    }

    static int cal_max_divisor(int n) {
        int m = n << 1;

        Map<Integer, Integer> maxDivisor = factor_divide(m);
        Map<Integer, Integer> lastFactors = new HashMap<>(maxDivisor);

        for (int i = 2; i < m; i += 2) {
            int a = (m - i + 1) * (m - i);
            Map<Integer, Integer> aFactors = factor_divide(a);
            for (Map.Entry<Integer, Integer> entry : aFactors.entrySet()) {
                map_add(lastFactors, entry.getKey(), entry.getValue());
            }
            int b = i * (i + 1);
            Map<Integer, Integer> bFactors = factor_divide(b);
            for (Map.Entry<Integer, Integer> entry : bFactors.entrySet()) {
                map_add(lastFactors, entry.getKey(), -1 * entry.getValue());
            }

            for (Map.Entry<Integer, Integer> entry : maxDivisor.entrySet()) {
                if (entry.getValue() > lastFactors.get(entry.getKey())) {
                    maxDivisor.put(entry.getKey(), lastFactors.get(entry.getKey()));
                }
            }
        }

        int result = 1;
        for (Map.Entry<Integer, Integer> entry : maxDivisor.entrySet()) {
            for (Integer i = 0; i < entry.getValue(); i++) {
                result *= entry.getKey();
            }
        }

        return result;
    }

    static void C(int n) {
        int m = n << 1;
        int lastNum = 1;
        for (int i = 0; i < m; i++) {
            lastNum = lastNum * (m - i) / (i + 1);
            if (i % 2 == 0) {
                System.out.print(lastNum + ",");
            }
        }
        System.out.println();
    }

    static void Cal(int n) {
        int m = n << 1;
        int lastNum = m;
        for (int i = 2; i < m; i += 2) {
            lastNum = lastNum * (m - i + 1) / (i) * (m - i) / (i + 1);
            if (i % 2 == 0) {
                System.out.print(lastNum + ",");
            }
        }
        System.out.println();
    }
}

package huawei;

import java.util.*;

/**
 * 求最长素数等差子序列
 * 
 * @since 2020-05-27
 */
public class boring1011 {
    /**
     * 待加速的用例
     * 1 50000
     * 1 1000
     * 100 1000
     * 500 1000
     * 1000 5000
     * 159 1500
     * 5000 10000
     * 1000 23456
     * 23 23456
     * 44400 50000
     * 3000 100000
     * 12345 98478
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();

            List<Integer> primes = new LinkedList<>();
            // TODO: 素数筛
            for (int i = a; i <= b; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }

            // TODO: 加速
            List<Integer> sub = maxArithmeticSubSeq(primes);

            System.out.print(sub.size());
            for (Integer prime : sub) {
                System.out.print(" " + prime);
            }
            System.out.println();
        }
    }

    /**
     * 求最长等差子序列
     * 
     * @param origin
     * @return
     */
    public static List<Integer> maxArithmeticSubSeq(List<Integer> origin) {
        if (origin.size() < 3) {
            return origin;
        }

        Map<Integer, Integer>[] distCount = new Map[origin.size()];
        distCount[0] = new HashMap<>();
        for (int i = 1; i < origin.size(); i++) {
            distCount[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int dist = origin.get(i) - origin.get(j);
                if (distCount[j].containsKey(dist)) {
                    distCount[i].put(dist, distCount[j].get(dist) + 1);
                } else {
                    distCount[i].put(dist, 1);
                }
            }
        }

        int maxCount = -1;
        int maxDist = -1;
        int maxIdx = -1;
        for (int i = 0; i < origin.size(); i++) {
            for (Map.Entry<Integer, Integer> entry : distCount[i].entrySet()) {
                if (entry.getValue() > maxCount) {
                    // 取最长的序列

                    maxIdx = i;
                    maxCount = entry.getValue();
                    maxDist = entry.getKey();
                } else if (entry.getValue() == maxCount &&
                        (origin.get(maxIdx) - maxCount * maxDist) > origin.get(i) - entry.getValue() * entry.getKey()) {
                    // 相等时, 取最小起点的序列
                    maxIdx = i;
                    maxCount = entry.getValue();
                    maxDist = entry.getKey();
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        int value = origin.get(maxIdx);
        for (int i = 0; i <= maxCount; i++) { // 注意size是多一个的
            result.add(0, value - i * maxDist);
        }
        return result;
    }

    /**
     * 求素数
     * 
     * @param value
     * @return
     */
    public static boolean isPrime(int value) {
        if (value < 2) {
            return false;
        }
        if (value == 2) {
            return true;
        }
        if (value % 2 == 0) {
            return false;
        }

        int mid = (int) (Math.sqrt(value) + 1);
        for (int i = 3; i < mid; i += 2) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}

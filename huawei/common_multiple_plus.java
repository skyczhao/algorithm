package huawei;

import java.util.*;

/**
 * common_multiple_plus
 *
 * @since 2020-07-28
 */
public class common_multiple_plus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<Integer> primes = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        Map<Integer, Map<Integer, Integer>> divisors = new HashMap<>();
        for (int i = 0; i <= 100; i++) {
            Map<Integer, Integer> subDivisors = findDivisor(i, primes);
            divisors.put(i, subDivisors);
        }

        while (in.hasNext()) {
            int n = in.nextInt();

            Map<Integer, Integer> allDivisor = new HashMap<>();
            for (int num = 1; num <= n; num++) {
                Map<Integer, Integer> subDivisor = divisors.get(num);
                for (Integer prime : primes) {
                    if (subDivisor.containsKey(prime)) {
                        if (!allDivisor.containsKey(prime) ||
                                subDivisor.get(prime) > allDivisor.get(prime)) {
                            allDivisor.put(prime, subDivisor.get(prime));
                        }
                    }
                }
            }

            List<Integer> result = new LinkedList<>();
            result.add(1);
            for (Map.Entry<Integer, Integer> entry : allDivisor.entrySet()) {
                int multi = 1;
                for (int i = 0; i < entry.getValue(); i++) {
                    multi *= entry.getKey();
                }

                bigMulti(result, multi);
            }

            for (int i = result.size() - 1; i >= 0; i--) {
                if (i != result.size() - 1) {
                    System.out.print(String.format("%04d", result.get(i)));
                } else {
                    System.out.print(String.format("%d", result.get(i)));
                }
            }
            System.out.println();
        }

        in.close();
    }

    private static void bigMulti(List<Integer> result, int multi) {
        int add = 0;
        for (int i = 0; i < result.size(); i++) {
            int value = result.get(i) * multi + add;
            add = value / 10000;
            result.set(i, value % 10000);
        }
        if (add > 0) {
            result.add(add);
        }
    }

    private static Map<Integer, Integer> findDivisor(int num, List<Integer> primes) {
        Map<Integer, Integer> result = new HashMap<>();
        int tmp = num;
        for (Integer prime : primes) {
            if (prime > tmp) {
                break;
            }

            while (tmp % prime == 0) {
                tmp /= prime;

                if (!result.containsKey(prime)) {
                    result.put(prime, 0);
                }
                result.put(prime, result.get(prime) + 1);
            }
            if (tmp == 1) {
                break;
            }
        }
        return result;
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        int mid = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < mid; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

package leetcode;


/**
 * CountPrimes
 * https://leetcode-cn.com/problems/count-primes/
 * 204. 计数质数
 *
 * @since 2020-12-03
 */
public class CountPrimes {
    public static void main(String[] args) {

        CountPrimes sol = new CountPrimes();
        System.out.println(sol.countPrimes(0));
        System.out.println(sol.countPrimes(10));
        System.out.println(sol.countPrimes(20));
        System.out.println(sol.countPrimes(200));
        System.out.println(sol.countPrimes(2000));
        System.out.println(sol.countPrimes(20000));
        System.out.println(sol.countPrimes(200000));
    }


    public int countPrimes(int n) {
        if (n < 2) {
            // keng...
            return 0;
        }
        boolean[] isNotPrime = new boolean[n + 1];

        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int num = 2; num <= n; num++) {
            if (isNotPrime[num]) {
                continue;
            }
            if (isPrime(num)) {
                int multi = 2;
                while (num * multi <= n) {
                    isNotPrime[num * multi] = true;
                    multi++;
                }
            } else {
                isNotPrime[num] = true;
            }
        }

        int size = 0;
        // keng, < n, not include =
        for (int i = 0; i < n; i++) {
            if (!isNotPrime[i]) {
                size++;
            }
        }
        return size;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num < 4) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        int mid = (int) Math.sqrt(num) + 1;
        for (int t = 3; t <= mid; t += 2) {
            if (num % t == 0) {
                return false;
            }
        }
        return true;
    }
}

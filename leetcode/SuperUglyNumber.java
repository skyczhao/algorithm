package leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * SuperUglyNumber
 * https://leetcode-cn.com/problems/super-ugly-number/
 * 313. 超级丑数
 * https://leetcode-cn.com/problems/super-ugly-number/solution/zheng-shu-yue-jie-by-oshdyr-86dg/
 * 参考题解的动态规划解法
 *
 * @author tobin
 * @since 2021-08-09
 */
public class SuperUglyNumber {
    public static void main(String[] args) {
        SuperUglyNumber sol = new SuperUglyNumber();
        System.out.println(sol.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        System.out.println(sol.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
        System.out.println(sol.nthSuperUglyNumber(100000, new int[]{7, 19, 29, 37, 41, 47, 53, 59,
                61, 79, 83, 89, 101, 103, 109, 127, 131, 137, 139,
                157, 167, 179, 181, 199, 211, 229, 233, 239, 241, 251}));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);

        Set<Integer> uniq = new HashSet<>();
        uniq.add(1);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            cnt++;
            uniq.remove(curr);

            if (cnt == n) {
                return curr;
            }

            for (int prime : primes) {
                long longVal = ((long) curr) * prime;
                if (longVal > Integer.MAX_VALUE) {
                    break;
                }

                int val = (int) longVal;
                if (!uniq.contains(val)) {
                    queue.add(val);
                    uniq.add(val);
                }
            }
        }

        return 0;
    }
}

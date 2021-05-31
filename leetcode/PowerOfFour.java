package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * PowerOfFour
 * https://leetcode-cn.com/problems/power-of-four/
 * 342. 4的幂
 * https://leetcode-cn.com/problems/power-of-four/solution/mei-ju-jie-ti-by-oshdyr-d7ad/
 * 题解有O(1)解法: 位判断, 数学特性(mod 3 == 1)
 *
 * @since 2021-05-31
 */
public class PowerOfFour {

    public static void main(String[] args) {
        PowerOfFour sol =  new PowerOfFour();
        System.out.println(sol.isPowerOfFour(1));
        System.out.println(sol.isPowerOfFour(5));
        System.out.println(sol.isPowerOfFour(16));
    }

    public boolean isPowerOfFour(int n) {
        Set<Integer> all = new HashSet<>();
        int base = 1;
        for (int i = 0; i < 16; i++) {
            all.add(base);
            base *= 4;
        }

        return all.contains(n);
    }
}

package leetcode;

/**
 * ClimbingStairs
 *
 * https://leetcode.cn/problems/climbing-stairs/
 * 70. 爬楼梯
 * https://leetcode.cn/problems/climbing-stairs/solution/fang-by-oshdyr-xppb/
 *
 * @author tobin
 * @since 2022-06-06
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs sol = new ClimbingStairs();
        System.out.println(sol.climbStairs(10));
        System.out.println(sol.climbStairs(9));
        System.out.println(sol.climbStairs(5));
        System.out.println(sol.climbStairs(3));
        System.out.println(sol.climbStairs(2));
        System.out.println(sol.climbStairs(1));
    }

    public int climbStairs(int n) {
        int[] lastStair = new int[50];
        lastStair[0] = 0;
        lastStair[1] = 1;
        lastStair[2] = 2;
        for (int i = 3; i < 50; i++) {
            lastStair[i] = lastStair[i - 1] + lastStair[i - 2];
        }

        return lastStair[n];
    }
}

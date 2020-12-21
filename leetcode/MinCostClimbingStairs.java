package leetcode;

/**
 * MinCostClimbingStairs
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 * 746. 使用最小花费爬楼梯
 *
 * @since 2020-12-21
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        MinCostClimbingStairs sol = new MinCostClimbingStairs();
        System.out.println(sol.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(sol.minCostClimbingStairs(new int[]{0, 0, 0, 1}));
        System.out.println(sol.minCostClimbingStairs(new int[]{0, 1, 2, 2}));
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] steps = new int[cost.length];
        for (int i = 0; i < cost.length; i++) {
            int lastStep = 0;
            if (i - 1 >= 0) {
                lastStep = steps[i - 1];
            }
            if (i - 2 < 0) {
                lastStep = 0;
            } else if (steps[i - 2] < lastStep) {
                lastStep = steps[i - 2];
            }
            steps[i] = lastStep + cost[i];
        }

        int total = 0;
        if (cost.length - 1 >= 0) {
            total = steps[cost.length - 1];
        }
        if (cost.length - 2 < 0) {
            total = 0;
        } else if (steps[cost.length - 2] < total) {
            total = steps[cost.length - 2];
        }

        return total;
    }

    public int minCostClimbingStairs_fail(int[] cost) {
        int total = 0;
        int idx = -1;
        while (idx + 1 < cost.length && idx + 2 < cost.length) {
            if (idx + 2 < cost.length &&
                    total + cost[idx + 2] <= total + cost[idx + 1]) {
                total += cost[idx + 2];
                idx += 2;
            } else {
                total += cost[idx + 1];
                idx += 1;
            }
        }
        return total;
    }
}

package leetcode;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * ClosestDessertCost
 * https://leetcode.cn/problems/closest-dessert-cost/
 * 1774. 最接近目标价格的甜点成本
 * https://leetcode.cn/problems/closest-dessert-cost/solutions/2006349/mo-ni-jie-ti-by-oshdyr-zewf/
 *
 * @author tobin
 * @since 2022-12-04
 */
public class ClosestDessertCost {

    public static void main(String[] args) {
        ClosestDessertCost sol = new ClosestDessertCost();
        System.out.println(sol.closestCost(
                new int[]{1, 7},
                new int[]{3, 4},
                10
        ));
        System.out.println(sol.closestCost(
                new int[]{2, 3},
                new int[]{4, 5, 100},
                18
        ));
        System.out.println(sol.closestCost(
                new int[]{3, 10},
                new int[]{2, 5},
                9
        ));
        System.out.println(sol.closestCost(
                new int[]{10},
                new int[]{1},
                1
        ));
    }

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int negative_closest = Integer.MAX_VALUE;
        int positive_closest = 0;
        for (int baseCost : baseCosts) {
            Queue<Integer> queue = new LinkedTransferQueue<>();
            queue.add(baseCost);
            for (int toppingCost : toppingCosts) {
                int size = queue.size();
                while (size-- > 0) {
                    int base = queue.poll();
                    // +0
                    if (base < target) {
                        if (base > positive_closest) {
                            positive_closest = base;
                        }
                        queue.add(base);
                    } else {
                        if (baseCost < negative_closest) {
                            negative_closest = baseCost;
                        }
                        continue;
                    }
                    // +1
                    int add1 = base + toppingCost;
                    if (add1 < target) {
                        if (add1 > positive_closest) {
                            positive_closest = add1;
                        }
                        queue.add(add1);
                    } else {
                        if (add1 < negative_closest) {
                            negative_closest = add1;
                        }
                        continue;
                    }
                    // +2
                    int add2 = add1 + toppingCost;
                    if (add2 < target) {
                        if (add2 > positive_closest) {
                            positive_closest = add2;
                        }
                        queue.add(add2);
                    } else {
                        if (add2 < negative_closest) {
                            negative_closest = add2;
                        }
                        continue;
                    }
                }
            }
            if (negative_closest == target) {
                return target;
            }
        }

        if (positive_closest == 0) {
            return negative_closest;
        } else if (target - positive_closest <= negative_closest - target) {
            return positive_closest;
        } else {
            return negative_closest;
        }
    }
}

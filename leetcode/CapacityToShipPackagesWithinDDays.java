package leetcode;

/**
 * CapacityToShipPackagesWithinDDays
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 * 1011. 在 D 天内送达包裹的能力
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/solution/er-fen-sou-suo-by-oshdyr-5igq/
 *
 * @author tobin
 * @since 2021-04-26
 */
public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        CapacityToShipPackagesWithinDDays sol = new CapacityToShipPackagesWithinDDays();
        System.out.println(sol.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
        System.out.println(sol.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
    }

    public int shipWithinDays(int[] weights, int D) {
        int maxWeight = 0;
        int sumWeight = 0;
        for (int weight : weights) {
            if (weight > maxWeight) {
                maxWeight = weight;
            }
            sumWeight += weight;
        }

        int left = maxWeight;
        int right = sumWeight;
        while (left < right) {
            int mid = (left + right) / 2;
            int times = count(weights, mid);
            if (times <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int count(int[] weights, int max) {
        int times = 0;
        int sum = 0;
        for (int weight : weights) {
            if (sum + weight <= max) {
                sum += weight;
            } else {
                times++;
                sum = weight;
            }
        }
        if (sum > 0) {
            times++;
        }
        return times;
    }
}

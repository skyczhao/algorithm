package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * MaximumGap
 * https://leetcode-cn.com/problems/maximum-gap/
 * 164. 最大间距
 *
 * @since 2020-11-26
 */
public class MaximumGap {
    public static void main(String[] args) {
        MaximumGap sol = new MaximumGap();

        System.out.println(sol.maximumGap(new int[]{1, 5, 667, 2, 987, 2434, 3, 6, 8, 12, 344}));
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        List<Integer> numList = new LinkedList<>();
        int maxValue = -1;
        for (int num : nums) {
            if (num > maxValue) {
                maxValue = num;
            }
            numList.add(num);
        }

        int digit = 0;
        while (maxValue > 0) {
            digit++;
            maxValue /= 10;
        }

        List<Integer>[] containers = new List[10];
        for (int i = 0; i < 10; i++) {
            containers[i] = new LinkedList<>();
        }
        int base = 1;
        for (int i = 0; i < digit; i++) {
            for (Integer num : numList) {
                containers[num / base % 10].add(num);
            }

            numList.clear();
            for (List<Integer> container : containers) {
                numList.addAll(container);
                container.clear();
            }

            base *= 10;
        }

        int maxDist = 0;
        int last = numList.get(0);
        for (Integer num : numList) {
            maxDist = Math.max(num - last, maxDist);
            last = num;
        }
        return maxDist;
    }
}

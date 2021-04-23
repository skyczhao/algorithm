package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * LargestDivisibleSubset
 * https://leetcode-cn.com/problems/largest-divisible-subset/
 * 368. 最大整除子集
 * https://leetcode-cn.com/problems/largest-divisible-subset/solution/bao-li-sou-suo-jie-ti-by-oshdyr-zi3j/
 *
 * @since 2021-04-23
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        LargestDivisibleSubset sol = new LargestDivisibleSubset();

//        List<Integer> res = sol.largestDivisibleSubset(new int[]{1, 2, 4, 8});
//        List<Integer> res = sol.largestDivisibleSubset(new int[]{1, 2, 3});
//        List<Integer> res = sol.largestDivisibleSubset(new int[]{1, 2, 3, 6});
//        List<Integer> res = sol.largestDivisibleSubset(new int[]{1, 2, 3, 6, 9, 12});
        List<Integer> res = sol.largestDivisibleSubset(new int[]{1, 2, 3, 6, 9, 12, 108});
        System.out.println(res.size());
        for (Integer re : res) {
            System.out.print(re + ", ");
        }
        System.out.println();
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> allNums = new ArrayList<>(nums.length);
        for (int num : nums) {
            allNums.add(num);
        }
        Collections.sort(allNums);

        int totalMaxLength = 1;
        int totalMaxIndex = 0;

        int[] dividerIndex = new int[nums.length];
        int[] maxLength = new int[nums.length];
        dividerIndex[0] = -1;
        maxLength[0] = 1;
        for (int i = 1; i < allNums.size(); i++) {
            dividerIndex[i] = -1;
            maxLength[i] = 1;
            for (int lastI = i - 1; lastI >= 0; lastI--) {
                if (maxLength[lastI] < maxLength[i]) {
                    continue;
                }
                if (allNums.get(i) % allNums.get(lastI) == 0) {
                    maxLength[i] = maxLength[lastI] + 1;
                    dividerIndex[i] = lastI;
                }
            }

            if (maxLength[i] > totalMaxLength) {
                totalMaxLength = maxLength[i];
                totalMaxIndex = i;
            }
        }

        List<Integer> result = new LinkedList<>();
        while (dividerIndex[totalMaxIndex] >= 0) {
            result.add(allNums.get(totalMaxIndex));
            totalMaxIndex = dividerIndex[totalMaxIndex];
        }
        if (totalMaxIndex >= 0) {
            result.add(allNums.get(totalMaxIndex));
        }
        return result;
    }
}

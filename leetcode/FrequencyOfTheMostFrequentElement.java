package leetcode;

import java.util.*;

/**
 * FrequencyOfTheMostFrequentElement
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/
 * 1838. 最高频元素的频数
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/solution/sui-man-dan-guo-by-oshdyr-4c1u/
 *
 * @author tobin
 * @since 2021-07-19
 */
public class FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        FrequencyOfTheMostFrequentElement sol = new FrequencyOfTheMostFrequentElement();
        System.out.println(sol.maxFrequency(new int[]{1, 2, 4}, 5));
        System.out.println(sol.maxFrequency(new int[]{1, 4, 8, 13}, 5));
        System.out.println(sol.maxFrequency(new int[]{3, 9, 6}, 2));
        System.out.println(sol.maxFrequency(new int[]{9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982, 9970, 9932, 9985, 9902, 9975, 9990, 9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963, 9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000, 9916, 9911, 9959, 9957, 9907, 9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981, 9927, 9930, 9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974, 9926, 9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932, 9964, 9972, 9935, 9946, 9966},
                3056));

    }

    public int maxFrequency(int[] nums, int k) {

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            int count = 1;
            if (counts.containsKey(num)) {
                count = counts.get(num) + 1;
            }
            counts.put(num, count);
        }

        List<Integer> values = new LinkedList<>();
        values.addAll(counts.keySet());
        Collections.sort(values);
        int[] valueArr = new int[values.size()];
        int idx = 0;
        for (Integer value : values) {
            valueArr[idx++] = value;
        }

        int maxCount = 0;
        for (int i = 0; i < valueArr.length; i++) {
            int value = valueArr[i];
            int count = counts.get(value);
            int tmp = k;
            for (int j = i - 1; j >= 0; j--) {
                int nextValue = valueArr[j];
                int nextCount = counts.get(nextValue);
                int needed = nextCount * (value - nextValue);
                if (tmp >= needed) {
                    count += nextCount;
                    tmp -= needed;
                } else {
                    count += (tmp / (value - nextValue));
                    break;
                }
            }

            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }
}

package leetcode;


/**
 * BinarySubarraysWithSum
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/
 * 930. 和相同的二元子数组
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/ya-suo-zu-he-jie-ti-by-oshdyr-apbq/
 *
 * @author tobin
 * @since 2021-07-08
 */
public class BinarySubarraysWithSum {

    public static void main(String[] args) {
        BinarySubarraysWithSum sol = new BinarySubarraysWithSum();
        System.out.println(sol.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(sol.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int oneSize = 0;
        int[] zeroBefore = new int[nums.length];
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                zeroBefore[oneSize] = zeroCount;
                oneSize++;

                zeroCount = 0;
            } else {
                zeroCount++;
            }
        }
        int zeroFinal = zeroCount;

        int times = 0;
        if (goal == 0) {
            for (int i = 0; i < oneSize; i++) {
                times += (zeroBefore[i] * (zeroBefore[i] + 1) / 2);
            }
            times += (zeroFinal * (zeroFinal + 1) / 2);
        } else {
            for (int i = 0; i < oneSize; i++) {
                int e = i + goal;
                if (e > oneSize) {
                    break;
                }

                int zeroHead = zeroBefore[i];
                int zeroTail = zeroFinal;
                if (e < oneSize) {
                    zeroTail = zeroBefore[e];
                }
//                if (zeroHead == 0) {
//                    zeroHead = 1;
//                }
                zeroHead += 1;
//                if (zeroTail == 0) {
//                    zeroTail = 1;
//                }
                zeroTail += 1;
                times += (zeroHead * zeroTail);
            }
        }
        return times;
    }

//    public int numSubarraysWithSum(int[] nums, int goal) {
//
//        int[] accumulator = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            if (i > 0) {
//                accumulator[i] = accumulator[i - 1] + nums[i];
//            } else {
//                accumulator[0] = nums[0];
//            }
//        }
//
//        int times = 0;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                int sum = accumulator[j];
//                if (i > 0) {
//                    sum = sum - accumulator[i - 1];
//                }
//                if (sum == goal) {
//                    times++;
//                }
//            }
//        }
//
//        return times;
//    }
}

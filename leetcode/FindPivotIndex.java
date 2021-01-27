package leetcode;

/**
 * FindPivotIndex
 * https://leetcode-cn.com/problems/find-pivot-index
 * 724. 寻找数组的中心索引
 * https://leetcode-cn.com/problems/find-pivot-index/solution/zhong-xin-wei-zhi-bian-li-by-oshdyr-xvko/
 *
 * @author tobin
 * @since 2021-01-28
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        FindPivotIndex sol = new FindPivotIndex();
        System.out.println(sol.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(sol.pivotIndex(new int[]{1, 2, 3}));
        System.out.println(sol.pivotIndex(new int[]{2, 2, 3, 1}));
        System.out.println(sol.pivotIndex(new int[]{-1, -1, -1, -1, -1, 0}));
        System.out.println(sol.pivotIndex(new int[]{-1, -1, -1, 0, 1, 1}));
    }

    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int leftSum = 0;
        int rightSum = 0;
        // 0 也可以... 即边界也可以
        for (int i = 0; i < nums.length; i++) {
            leftSum += (i - 1 >= 0 ? nums[i - 1] : 0);
            rightSum = total - nums[i] - leftSum;
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

//    public int pivotIndex(int[] nums) {
//
//        int leftEndIdx = 0;
//        int leftSum = 0;
//        int rightEndIdx = nums.length - 1;
//        int rightSum = 0;
//        while (leftEndIdx < rightEndIdx) {
//            if (leftSum == rightSum) {
//                leftSum += nums[leftEndIdx];
//                rightSum += nums[rightEndIdx];
//                leftEndIdx++;
//                rightEndIdx--;
//            } else if (leftSum > rightSum) {
//                rightSum += nums[rightEndIdx];
//                rightEndIdx--;
//            } else { // leftSum < rightSum
//                leftSum += nums[leftEndIdx];
//                leftEndIdx++;
//            }
//            if (leftEndIdx == rightEndIdx) {
//                break;
//            }
//        }
//        if (leftEndIdx == rightEndIdx && leftSum == rightSum) {
//            return leftEndIdx;
//        }
//        return -1;
//    }
}

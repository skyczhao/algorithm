package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * MinimumSubsequenceInNonIncreasingOrder
 * https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/
 * 1403. 非递增顺序的最小子序列
 * https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order/solution/xiang-qing-chu-liao-cai-shi-jian-dan-ti-hrfd0/
 *
 * @author Tobin
 * @since 2022-08-04
 */
public class MinimumSubsequenceInNonIncreasingOrder {
    public static void main(String[] args) {
        MinimumSubsequenceInNonIncreasingOrder sol = new MinimumSubsequenceInNonIncreasingOrder();
        System.out.println(sol.minSubsequence(new int[]{4, 3, 10, 9, 8})); // [10, 9]
        System.out.println(sol.minSubsequence(new int[]{4, 4, 7, 6, 7})); // [7, 7, 6]
        System.out.println(sol.minSubsequence(new int[]{6})); // [6]
        System.out.println(sol.minSubsequence(new int[]{5, 5})); // [5, 5]
        System.out.println(sol.minSubsequence(new int[]{10, 2, 5})); // [10]
        System.out.println(sol.minSubsequence(new int[]{73, 71, 26, 65, 53, 63, 46, 54, 45, 1,
                69, 70, 80, 79, 43, 59, 77, 4, 30, 82,
                23, 71, 75, 24, 12, 71, 33, 5, 87, 10,
                11, 3, 58, 2, 97, 97, 36, 32, 35, 15,
                80, 24, 45, 38, 9, 22, 21, 33, 68, 22,
                85, 35, 83, 92, 38, 59}));
    }

    
    public List<Integer> minSubsequence(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int mid = total / 2;

        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);

        List<Integer> res = new LinkedList<>();
        int sum = 0;
        // 从后往前, 降序使用
        for (int j = arr.length - 1; j >= 0; j--) {
            int a = arr[j];
            res.add(a);
            sum += a;
            if (sum > mid) {
                break;
            }
        }

        return res;
    }

    /**
     * slow
     * 
     * @param nums
     * @return
     */
    public List<Integer> minSubsequence2(int[] nums) {
        int all = nums[0];
        int[] accAll = new int[nums.length];
        accAll[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            all += nums[i];
            accAll[i] = accAll[i - 1] + nums[i];
        }

        // 初始化
        int[] target = null;
        int targetSum = 0;

        // 从单个数开始迭代
        Queue<int[]> arrQueue = new LinkedBlockingDeque<>();
        Queue<Integer> sumQueue = new LinkedBlockingDeque<>();
        for (int i = 0; i < nums.length; i++) {
            int[] arr = new int[nums.length];
            arr[i] = nums[i];

            // Bug1: 单个数就足以承受的情况没考虑上
            int nextSum = nums[i];
            int nextLeft = all - nextSum;

            if (nextSum > nextLeft) {
                // 已满足, 可以比较准备退出
                if (target == null || targetSum < nextSum) {
                    target = arr;
                    targetSum = nums[i];
                }
            } else {
                // 仍未满足, 存储并等待处理
                if (target == null) {
                    if (nextSum + all - accAll[i] > all / 2) {
                        arrQueue.add(arr);
                        sumQueue.add(nums[i]);
                    }
                }
            }
        }

        // 迭代下一层级
        while (target == null && !arrQueue.isEmpty()) {
            int size = arrQueue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = arrQueue.poll();
                int sum = sumQueue.poll();

                // 拿到当前迭代后, 从最后往前测试
                // key: 题目条件, 数据大于0; 等于0即未被处理;
                for (int j = arr.length - 1; j >= 0 && arr[j] == 0; j--) {
                    int[] next = Arrays.copyOf(arr, arr.length);
                    next[j] = nums[j];

                    int nextSum = sum + nums[j];
                    int nextLeft = all - nextSum;

                    if (nextSum > nextLeft) {
                        // 已满足, 可以比较准备退出
                        if (target == null || targetSum < nextSum) {
                            target = next;
                            targetSum = nextSum;
                        }
                    } else {
                        // 仍未满足, 存储并等待处理
                        // Bug 2: 剪枝
                        if (target == null) {
                            // Bug 3: 再剪枝
                            if (nextSum + all - accAll[j] > all / 2) {
                                arrQueue.add(next);
                                sumQueue.add(nextSum);
                            }
                        }
                    }
                }
            }
        }

        if (target == null) {
            // 无法取得结果的情况, 如只有一个数据
            target = Arrays.copyOf(nums, nums.length);
        }
        Arrays.sort(target);
        List<Integer> result = new LinkedList<>();
        for (int j = target.length - 1; j >= 0; j--) {
            if (target[j] > 0) {
                result.add(target[j]);
            } else {
                break;
            }
        }
        return result;
    }

}


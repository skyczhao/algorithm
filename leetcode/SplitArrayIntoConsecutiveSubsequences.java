package leetcode;

import java.util.*;

/**
 * SplitArrayIntoConsecutiveSubsequences
 * https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/
 * 659. 分割数组为连续子序列
 *
 * @since 2020-12-04
 */
public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSubsequences sol = new SplitArrayIntoConsecutiveSubsequences();
        System.out.println(sol.isPossible(new int[]{1, 2, 3, 3, 4, 5}));
        System.out.println(sol.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
        System.out.println(sol.isPossible(new int[]{1, 2, 3, 4, 4, 5}));
        System.out.println(sol.isPossible(new int[]{4, 5, 6, 7, 7, 8, 8, 9, 10, 11}));
    }

    public boolean isPossible(int[] nums) {
        List<int[]> endWithLength = new ArrayList<>();
        Map<Integer, List<Integer>> endIdx = new HashMap<>();

        for (int num : nums) {
            if (endIdx.containsKey(num - 1) && endIdx.get(num - 1).size() > 0) {
                // append history
                List<Integer> oriNumIdxs = endIdx.get(num - 1);
                // keng
                int targetIdx = -1;
                for (int i = oriNumIdxs.size() - 1; i >= 0; i--) {
                    int tmpTargetIdx = oriNumIdxs.get(i);
                    if (endWithLength.get(tmpTargetIdx)[1] < 3) {
                        targetIdx = oriNumIdxs.remove(i);;
                        break;
                    }
                }
                if (targetIdx == -1) {
                    targetIdx = oriNumIdxs.remove(0);
                }

                endWithLength.get(targetIdx)[1]++;
                endWithLength.get(targetIdx)[0] = num;

                if (!endIdx.containsKey(num)) {
                    endIdx.put(num, new LinkedList<>());
                }
                endIdx.get(num).add(targetIdx);
            } else {
                // new
                endWithLength.add(new int[]{num, 1});
                if (!endIdx.containsKey(num)) {
                    endIdx.put(num, new LinkedList<>());
                }
                endIdx.get(num).add(endWithLength.size() - 1);
            }
        }

        for (int[] pair : endWithLength) {
            if (pair[1] < 3) {
                return false;
            }
        }
        return true;
    }
}

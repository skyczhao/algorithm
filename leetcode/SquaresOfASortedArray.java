package leetcode;


import java.util.LinkedList;
import java.util.List;

/**
 * SquaresOfASortedArray
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 * 1. 题目不难, 直接能想到的思路: 全部计算后排序
 * 2. 双指针同时移动(归并排序, 逆向), 应该会比本实现快些
 *
 * @since 2020-10-16
 */
public class SquaresOfASortedArray {
    public static void main(String[] args) {
        SquaresOfASortedArray sol = new SquaresOfASortedArray();
        int[] res = sol.sortedSquares(new int[]{-7, -3, 2, 3, 11});
        for (int r : res) {
            System.out.println(r);
        }
    }

    public int[] sortedSquares(int[] A) {
        int sepIdx = 0;
        for (sepIdx = 0; sepIdx < A.length; sepIdx++) {
            if (A[sepIdx] >= 0) {
                break;
            }
        }

        List<Integer> resList = new LinkedList<>();
        for (int i = sepIdx; i < A.length; i++) {
            resList.add(A[i] * A[i]);
        }
        int lastIdx = 0;
        for (int j = sepIdx - 1; j >= 0; j--) {
            int t = A[j] * A[j];
            while (lastIdx < resList.size()) {
                if (t < resList.get(lastIdx)) {
                    break;
                } else {
                    lastIdx++;
                }
            }
            resList.add(lastIdx, t);
        }

        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}

package leetcode;

import java.util.*;

/**
 * CreateMaximumNumber
 * https://leetcode-cn.com/problems/create-maximum-number/
 * 321. 拼接最大数
 * 参考题解Pass, 总结:
 * 1. DFS明显会超时，但是一开始确实没有思路
 * 2. DP应该可以，但转移函数没想好
 * 3. 新学单调栈，写起来掉挺多坑
 * 4. 归并过程需要判断剩余子序列的排序，不仅仅是队头元素，踩坑且还不太明白
 * 5. 需要枚举两个序列需要提取的个数，也不太明白
 *
 * @since 2020-12-02
 */
public class CreateMaximumNumber {
    public static void main(String[] args) {
//        int[] nums1 = {3, 4, 6, 5};
//        int[] nums2 = {9, 1, 2, 5, 8, 3};
//        int k = 5;

//        int[] nums1 = {3, 9};
//        int[] nums2 = {8, 9};
//        int k = 3;

//        int[] nums1 = {8, 9};
//        int[] nums2 = {3, 9};
//        int k = 3;

//        int[] nums1 = {6, 7};
//        int[] nums2 = {6, 0, 4};
//        int k = 5;

        int[] nums1 = {8, 6, 9};
        int[] nums2 = {1, 7, 5};
        int k = 3;

//        int[] nums1 = {3, 9, 1, 0, 0, 5, 6, 3, 7, 3, 1, 6, 1, 9, 5, 4, 3, 6, 4, 0, 8, 8, 2, 8};
//        int[] nums2 = {1, 8, 7, 9, 7, 8, 0, 1, 4, 7, 5, 6, 9, 9, 8, 7, 1, 1, 4, 2, 5, 5, 0, 0, 8, 0, 6, 5, 4, 1, 2, 3, 3};
//        int k = 57;

//        int[] nums1 = {1, 8, 7, 9, 7, 8, 0, 1, 4, 7, 5, 6, 9, 9, 8, 7, 1, 1, 4, 2, 5, 5, 0, 0, 8, 0, 6, 5, 4, 1, 2, 3, 3};
//        int[] nums2 = {3, 9, 1, 0, 0, 5, 6, 3, 7, 3, 1, 6, 1, 9, 5, 4, 3, 6, 4, 0, 8, 8, 2, 8};
//        int k = 57;

        CreateMaximumNumber sol = new CreateMaximumNumber();
        int[] res = sol.maxNumber(nums1, nums2, k);
        if (res != null) {
            for (int num : res) {
                System.out.println(num);
            }
        }
    }

    // try 15
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = null;
        for (int i = 0; i <= k; i++) {
            if (k - i <= nums1.length && i <= nums2.length) {
                int[] aSubSeq = getMaxKSubSeq(nums1, k - i);
                int[] bSubSeq = getMaxKSubSeq(nums2, i);
                int[] tmpSeq = mergeSeq(aSubSeq, bSubSeq);
                if (result != null) {
                    if (compare(tmpSeq, result) > 0) {
                        result = tmpSeq;
                    }
                } else {
                    result = tmpSeq;
                }
            }
        }
        return result;
    }

    private int compare(int[] right, int[] left) {
        if (right.length != left.length) {
            return right.length - left.length;
        }
        for (int i = 0; i < right.length; i++) {
            if (right[i] != left[i]) {
                return right[i] - left[i];
            }
        }
        return 0;
    }

    private int[] mergeSeq(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];

        int idx1 = 0;
        int idx2 = 0;
        for (int idx = 0; idx < merge.length; idx++) {
            // key1
            int tmpIdx1 = idx1;
            int tmpIdx2 = idx2;
            while (tmpIdx1 < a.length &&
                    tmpIdx2 < b.length) {
                if (a[tmpIdx1] != b[tmpIdx2]) {
                    break;
                }
                tmpIdx1++;
                tmpIdx2++;
            }

            // merge
            if (tmpIdx1 < a.length && tmpIdx2 < b.length) {
                if (a[tmpIdx1] > b[tmpIdx2]) {
                    merge[idx] = a[idx1++];
                } else {
                    merge[idx] = b[idx2++];
                }
            } else if (tmpIdx1 < a.length) {
                merge[idx] = a[idx1++];
            } else if (tmpIdx2 < b.length) {
                merge[idx] = b[idx2++];
            }
        }
        return merge;
    }

    // 单调栈
    private int[] getMaxKSubSeq(int[] nums, int k) {
        int size = nums.length;
        int realK = k > size ? size : k;

        int[] result = new int[realK];
        int resTopIdx = -1;
        for (int moveIdx = 0; moveIdx < size; moveIdx++) {
            int canReplaceSize = (size - moveIdx) - (realK - resTopIdx - 1);
            while (canReplaceSize-- > 0) {
                if (resTopIdx >= 0 && nums[moveIdx] > result[resTopIdx]) {
                    // 可被取代, 继续往前试探
                    resTopIdx--;
                } else {
                    // 不可被取代
                    break;
                }
            }
            // 当前TOP是不可取代的, 往后赋值
            if (resTopIdx + 1 < realK) {
                result[++resTopIdx] = nums[moveIdx];
            }
        }
        return result;
    }

}

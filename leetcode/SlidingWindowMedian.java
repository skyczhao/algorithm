package leetcode;


import java.util.*;

/**
 * SlidingWindowMedian
 * https://leetcode-cn.com/problems/sliding-window-median
 * 480. 滑动窗口中位数
 * https://leetcode-cn.com/problems/sliding-window-median/solution/xuan-ze-he-gua-de-shu-ju-jie-gou-by-oshd-gehp/
 * 难题, 个人总结难点在于: 
 * 1. 滑动窗口的操作，边界调节考虑容易不周到
 * 2. 为了实现有序的存储, 采用了多个数据结构, 而操作以及操作之间触发的联动, 考虑都有比较周到
 * 如果不熟悉, 难以一次考虑周到, 没有提示错误用例时, 比较难调试
 * 
 * @author tobin
 * @since 2021-02-03
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        SlidingWindowMedian sol = new SlidingWindowMedian();

        // double[] res = sol.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        // double[] res = sol.medianSlidingWindow(new int[]{1, 2, 3, 4}, 4);
        double[] res = sol.medianSlidingWindow(new int[]{1, 2}, 1);
        // double[] res = sol.medianSlidingWindow(new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648}, 3);
        // double[] res = sol.medianSlidingWindow(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, 2);

        for (double v : res) {
            System.out.print(v + ", ");
        }
        System.out.println();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        // 使用合适的数据结构
        int length = nums.length - k;
        int bigSize = k / 2;
        int smallSize = k - bigSize;

        TreeSet<Integer> smallWindow = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a); // a-b可能会越界!
            }
        });
        Map<Integer, Integer> smallCount = new HashMap<>();
        int smallTotal = 0;

        TreeSet<Integer> bigWindow = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b); // a-b可能会越界!
            }
        });
        Map<Integer, Integer> bigCount = new HashMap<>();
        int bigTotal = 0;

        for (int i = 0; i < k; i++) {
            smallTotal++;
            smallCount.put(nums[i], smallCount.containsKey(nums[i]) ? smallCount.get(nums[i]) + 1 : 1);
            smallWindow.add(nums[i]);
        }
        balance(smallWindow, smallCount, smallTotal, bigWindow, bigCount, bigTotal);
        smallTotal = smallSize;
        bigTotal = bigSize;

        double[] result = new double[nums.length - k + 1];

        for (int startIdx = 0; startIdx + k <= nums.length; startIdx++) {
            int endIdx = startIdx + k;

            if (startIdx - 1 >= 0) {
                int toDrop = nums[startIdx - 1];
                if (smallWindow.contains(toDrop)) {
                    int toDropCount = smallCount.get(toDrop);
                    if (toDropCount > 1) {
                        smallCount.put(toDrop, toDropCount - 1);
                    } else {
                        smallWindow.remove(toDrop);
                        smallCount.remove(toDrop);
                    }
                    smallTotal--;
                } else {
                    int toDropCount = bigCount.get(toDrop);
                    if (toDropCount > 1) {
                        bigCount.put(toDrop, toDropCount - 1);
                    } else {
                        bigWindow.remove(toDrop);
                        bigCount.remove(toDrop);
                    }
                    bigTotal--;
                }

                int toAdd = nums[endIdx - 1];
                if (smallWindow.isEmpty() || toAdd <= smallWindow.first()) { // 只有1个值时候被删了,就可能为空
                    int toAddCount = smallCount.containsKey(toAdd) ? smallCount.get(toAdd) : 0;
                    if (toAddCount > 0) {
                        smallCount.put(toAdd, toAddCount + 1);
                    } else {
                        smallWindow.add(toAdd);
                        smallCount.put(toAdd, 1);
                    }
                    smallTotal++;
                } else {
                    int toAddCount = bigCount.containsKey(toAdd) ? bigCount.get(toAdd) : 0;
                    if (toAddCount > 0) {
                        bigCount.put(toAdd, toAddCount + 1);
                    } else {
                        bigWindow.add(toAdd);
                        bigCount.put(toAdd, 1);
                    }
                    bigTotal++;
                }
                balance(smallWindow, smallCount, smallTotal, bigWindow, bigCount, bigTotal);
                smallTotal = smallSize;
                bigTotal = bigSize;
            }

            if (smallTotal - bigTotal == 0) {
                result[startIdx] = smallWindow.first() / 2.0 + bigWindow.first() / 2.0;
            } else {
                result[startIdx] = smallWindow.first();
            }
        }

        return result;

    }

    private void balance(TreeSet<Integer> smallWindow, Map<Integer, Integer> smallCount, int smallTotal,
                         TreeSet<Integer> bigWindow, Map<Integer, Integer> bigCount, int bigTotal) {
        while (smallTotal - bigTotal > 1) {
            int smallDrop = smallWindow.first();
            int smallDropCount = smallCount.get(smallDrop);
            if (smallDropCount > 1) {
                smallCount.put(smallDrop, smallDropCount - 1);
            } else {
                smallWindow.remove(smallDrop);
                smallCount.remove(smallDrop);
            }
            smallTotal--;

            int bigReceiveCount = bigCount.containsKey(smallDrop) ? bigCount.get(smallDrop) : 0;
            if (bigReceiveCount > 0) {
                bigCount.put(smallDrop, bigReceiveCount + 1);
            } else {
                bigWindow.add(smallDrop);
                bigCount.put(smallDrop, 1);
            }
            bigTotal++;
        }

        while (bigTotal - smallTotal > 0) {
            int bigDrop = bigWindow.first();
            int bigDropCount = bigCount.get(bigDrop);
            if (bigDropCount > 1) {
                bigCount.put(bigDrop, bigDropCount - 1);
            } else {
                bigWindow.remove(bigDrop);
                bigCount.remove(bigDrop);
            }
            bigTotal--;

            int smallReceiveCount = smallCount.containsKey(bigDrop) ? smallCount.get(bigDrop) : 0;
            if (smallReceiveCount > 0) {
                smallCount.put(bigDrop, smallReceiveCount + 1);
            } else {
                smallWindow.add(bigDrop);
                smallCount.put(bigDrop, 1);
            }
            smallTotal++;
        }
    }
}

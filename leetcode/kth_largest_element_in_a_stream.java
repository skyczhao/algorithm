package leetcode;

/**
 * kth_largest_element_in_a_stream
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 *
 * @since 2020-05-29
 */
public class kth_largest_element_in_a_stream {
    public static void main(String[] args) {
        // int k = 3;
        // int[] arr = {4, 5, 8, 2};
        // KthLargest kthLargest = new KthLargest(3, arr);
        // System.out.println(kthLargest.add(3)); // returns 4
        // System.out.println(kthLargest.add(5)); // returns 5
        // System.out.println(kthLargest.add(10)); // returns 5
        // System.out.println(kthLargest.add(9)); // returns 8
        // System.out.println(kthLargest.add(4)); // returns 8

        int k = 7;
        int[] arr = { -10, 1, 3, 1, 4, 10, 3, 9, 4, 5, 1 };
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(6));
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(8));
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(11));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(6));
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(8));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(6));
    }
}

/**
 * 小根堆 
 * 1. 堆的特性, 完全二叉树(所以可以用数组表示, 父子的下标可以简易转换); 
 * 2. 容量限制, 不限制时候不是这个功能; 
 * 3. 小根的特性, 第一个值是最小值, 利用此排除比它更小的, 从而保留了TOP N;
 */
class KthLargest {

    int k;
    int[] array;
    int size;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.array = new int[k];
        this.size = 0;

        for (int num : nums) {
            add(num);
        }
    }

    private void headarrange(int idx) {
        if (idx >= size)
            return;
        int tmp = array[idx]; // 预备用于交换
        int swapIdx = idx; // 预备用于交换

        int leftIdx = idx * 2 + 1;
        int rightIdx = idx * 2 + 2;
        if ((leftIdx < size && array[idx] > array[leftIdx]) || (rightIdx < size && array[idx] > array[rightIdx])) {
            if (leftIdx < size && rightIdx < size) {
                // 取小的进行替换
                if (array[leftIdx] < array[rightIdx]) {
                    // 取左边的进行替换
                    swapIdx = leftIdx;
                } else {
                    // 取右边的进行替换
                    swapIdx = rightIdx;
                }
            } else if (rightIdx < size) {
                // 取右边的进行替换
                swapIdx = rightIdx;
            } else {
                // 取左边的进行替换
                swapIdx = leftIdx;
            }

            array[idx] = array[swapIdx];
            array[swapIdx] = tmp;
            headarrange(swapIdx);

        } else {
            // 依然为小根堆
            // 也即新加进来的数字刚好排名第K, 无需再操作
        }
    }

    private void tailarrange() {
        int idx = size - 1;
        while (idx > 0) {
            int parentIdx = (idx - 1) / 2;
            if (array[parentIdx] > array[idx]) {
                int tmp = array[idx];
                array[idx] = array[parentIdx];
                array[parentIdx] = tmp;

                idx = parentIdx;
            } else {
                break;
            }
        }
    }

    public int add(int val) {
        if (size < k) {
            // 不满的堆插入, 插入最后
            array[size] = val;
            size++;

            // 从尾部重排
            tailarrange();
        } else {
            if (val > array[0]) {
                // 满的堆插入, 判断取代根
                array[0] = val;

                // 从头部重排
                headarrange(0);
            } else {
                // 不足以取代, 省略操作
            }
        }

        // 返回
        if (size == k) {
            return array[0];
        }
        return 0;
    }
}
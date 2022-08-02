package leetcode;

/**
 * DesignCircularQueue
 * https://leetcode.cn/problems/design-circular-queue/
 * 622. 设计循环队列
 * https://leetcode.cn/problems/design-circular-queue/solution/mo-ni-by-oshdyr-1r3g/
 *
 * @author Tobin
 * @since 2022-08-02
 */
public class DesignCircularQueue {

    public static void main(String[] args) {

//        MyCircularQueue obj = new MyCircularQueue(k);
//        boolean param_1 = obj.enQueue(value);
//        boolean param_2 = obj.deQueue();
//        int param_3 = obj.Front();
//        int param_4 = obj.Rear();
//        boolean param_5 = obj.isEmpty();
//        boolean param_6 = obj.isFull();

        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1));  // 返回 true
        System.out.println(circularQueue.enQueue(2));  // 返回 true
        System.out.println(circularQueue.enQueue(3));  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
        System.out.println(circularQueue.Rear());  // 返回 3
        System.out.println(circularQueue.isFull());  // 返回 true
        System.out.println(circularQueue.deQueue());  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 true
        System.out.println(circularQueue.Rear());  // 返回 4

    }

}


class MyCircularQueue {

    private int length;
    private int currSize;

    private int[] queue;
    private int start; // 0
    private int next; // 0

    public MyCircularQueue(int k) {
        length = k;
        currSize = 0;

        queue = new int[length];
        start = 0;
        next = 0;
    }

    /**
     * 向循环队列插入一个元素。如果成功插入则返回真
     *
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        queue[next] = value;
        next = (next + 1) % length;
        currSize++;
        return true;
    }

    /**
     * 从循环队列中删除一个元素。如果成功删除则返回真
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        queue[start] = -1;
        start = (start + 1) % length;
        currSize--;
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回 -1
     *
     * @return
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[start];
    }

    /**
     * 获取队尾元素。如果队列为空，返回 -1
     *
     * @return
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(next - 1 + length) % length];
    }

    /**
     * 检查循环队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return currSize < 1;
    }

    /**
     * 检查循环队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return currSize >= length;
    }
}

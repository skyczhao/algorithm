package leetcode;

import java.util.Stack;

/**
 * ImplementQueueUsingStacks
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 232. 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/shuang-zhan-shi-xian-dui-lie-by-oshdyr-fm7c/
 *
 * @since 2021-03-05
 */
public class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(3);
        queue.push(8);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.peek());
        System.out.println(queue.empty());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }


}

class MyQueue {
    Stack<Integer> main;
    Stack<Integer> support;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        main = new Stack<>();
        support = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        while (!support.isEmpty()) {
            main.add(support.pop());
        }
        main.add(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (!main.isEmpty()) {
            support.add(main.pop());
        }
        return support.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        while (!main.isEmpty()) {
            support.add(main.pop());
        }
        return support.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return main.isEmpty() && support.isEmpty();
    }
}

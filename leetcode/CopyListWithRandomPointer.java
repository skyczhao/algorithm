package leetcode;

import utils.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * CopyListWithRandomPointer
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 138. 复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-ge-mapjie-ti-by-oshdyr-hy1i/
 * 思路:
 * 1. 直接索引; 
 * 2. 借助栅栏, 位置偏移关系立即可查, 取代索引;
 *
 * @author tobin
 * @since 2021-07-22
 */
public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Node head = new Node(0);
        for (int i = 1; i < 5; i++) {
            Node tmp = head;
            head = new Node(i);
            head.next = tmp;
        }

        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }

        CopyListWithRandomPointer sol = new CopyListWithRandomPointer();
        Node newHead = sol.copyRandomList(head);

        Node newTmp = newHead;
        while (newTmp != null) {
            System.out.println(newTmp.val);
            newTmp = newTmp.next;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Integer> oldValue2Index = new HashMap<>();
        Map<Integer, Node> index2NewValue = new HashMap<>();

        Node newHead = new Node(head.val);
        oldValue2Index.put(head, 0);
        index2NewValue.put(0, newHead);

        Node next = head;
        Node newNext = newHead;
        int idx = 1;
        while (next.next != null) {
            newNext.next = new Node(next.next.val);
            oldValue2Index.put(next.next, idx);
            index2NewValue.put(idx, newNext.next);

            next = next.next;
            newNext = newNext.next;
            idx++;
        }

        Node tmp = head;
        Node newTmp = newHead;
        while (tmp != null) {
            if (tmp.random != null) {
                newTmp.random = index2NewValue.get(oldValue2Index.get(tmp.random));
            }

            tmp = tmp.next;
            newTmp = newTmp.next;
        }

        return newHead;
    }

}

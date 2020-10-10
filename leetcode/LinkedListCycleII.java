package leetcode;

import utils.ListNode;
import utils.Lists;

/**
 * LinkedListCycleII
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * @since 2020-10-10
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head = Lists.fromInts(new int[]{3, 2, 0, -4}, 1);

        LinkedListCycleII sol = new LinkedListCycleII();
        ListNode res = sol.detectCycle(head);
        if (res == null) {
            System.out.println("no cycle");
        } else {
            System.out.println("tail connects to node " + res.val);
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        int dist = 0;
        while (true) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                dist = -1;
                break;
            }
            if (fast == slow) {
                dist++;
                break;
            }

            if (fast.next != null) {
                fast = fast.next;
            } else {
                dist = -1;
                break;
            }
            if (fast == slow) {
                dist += 2;
                break;
            }

            slow = slow.next;
            dist++;
        }
        if (dist == -1) {
            return null;
        }

        slow = head;
        fast = head;
        for (int i = 0; i < dist; i++) {
            fast = fast.next;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}

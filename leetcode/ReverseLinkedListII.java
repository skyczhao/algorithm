package leetcode;

import utils.ListNode;
import utils.Lists;

/**
 * ReverseLinkedListII
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * 92. 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/lian-biao-jiao-huan-cao-zuo-by-oshdyr-gqfx/
 *
 * @since 2021-03-18
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode input = Lists.fromInts(new int[]{1, 2, 3, 4, 5}, -1);

        ReverseLinkedListII sol = new ReverseLinkedListII();
        ListNode res = sol.reverseBetween(input, 1, 4);

        ListNode next = res;
        while (next != null) {
            System.out.print(next.val + ", ");
            next = next.next;
        }
        System.out.println();

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode last = null;
        ListNode curr = head;
        int idx = 1;
        ListNode beforeStart = null;
        ListNode start = null;
        while (curr != null) {

            if (idx == left) {
                beforeStart = last;
                start = curr;
            }
            if (idx > left && idx <= right) {
                last.next = curr.next;
                ListNode tmp = curr;
                curr = last;

                tmp.next = start;
                start = tmp;
                if (beforeStart != null) {
                    beforeStart.next = start;
                } else {
                    head = start;
                }
            }

            last = curr;
            curr = curr.next;
            idx++;
        }

        return head;
    }
}

package leetcode;

import utils.ListNode;
import utils.Lists;

/**
 * OddEvenLinkedList
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 * 328. 奇偶链表
 *
 * @since 2020-11-13
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode head = Lists.fromInts(new int[]{1, 2, 3, 4, 5}, -1);

        OddEvenLinkedList sol = new OddEvenLinkedList();
        sol.oddEvenList(head);
        System.out.println();
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode oddEnd = head;
        ListNode toMoveParent = head.next;
        while (toMoveParent != null) {
            if (toMoveParent.next == null) {
                break;
            }

            ListNode oddEndNext = oddEnd.next;
            ListNode toMove = toMoveParent.next;
            ListNode toMoveNext = toMove.next;

            oddEnd.next = toMove;
            toMove.next = oddEndNext;
            toMoveParent.next = toMoveNext;

            oddEnd = oddEnd.next;
            toMoveParent = toMoveParent.next;
        }

        return head;
    }
}

package leetcode;

import utils.ListNode;
import utils.Lists;

/**
 * PalindromeLinkedList
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @since 2020-10-23
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        // ListNode head = Lists.fromInts(new int[]{1, 2, 2, 1}, -1);
        // ListNode head = Lists.fromInts(new int[]{1}, -1);
        // ListNode head = null;
        // ListNode head = Lists.fromInts(new int[]{1, 2}, -1);
        ListNode head = Lists.fromInts(new int[]{1, 2, 2}, -1);
        PalindromeLinkedList sol = new PalindromeLinkedList();
        System.out.println(sol.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode node = recursiveCompare(head, head);
        return head == null || node != null;
    }

    public ListNode recursiveCompare(ListNode head, ListNode negative) {
        ListNode positive = null;
        if (negative != null) {
            // go to the end
            positive = recursiveCompare(head, negative.next);
        } else {
            // stop recursive, return head for compare
            return head;
        }

        if (positive != null) {
            if (positive.val == negative.val) {
                if (positive.next != null) {
                    // iteration for next value
                    return positive.next;
                } else {
                    // finish compare
                    return head;
                }
            }
        }
        return null;
    }
}

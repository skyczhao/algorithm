package leetcode;

import utils.ListNode;
import utils.Lists;

/**
 * RemoveDuplicatesFromSortedListII
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * 82. 删除排序链表中的重复元素 II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/zhi-zhen-cao-zuo-shan-chu-by-oshdyr-a3gg/
 *
 * @since 2021-03-25
 */
public class RemoveDuplicatesFromSortedListII {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII sol = new RemoveDuplicatesFromSortedListII();

        ListNode l1 = Lists.fromInts(new int[]{1, 2, 3, 3, 4, 4, 5}, -1);
        l1 = sol.deleteDuplicates(l1);
        while (l1 != null) {
            System.out.print(l1.val + ", ");
            l1 = l1.next;
        }
        System.out.println();

        ListNode l2 = Lists.fromInts(new int[]{1, 1, 1, 2, 3}, -1);
        l2 = sol.deleteDuplicates(l2);
        while (l2 != null) {
            System.out.print(l2.val + ", ");
            l2 = l2.next;
        }
        System.out.println();

        ListNode l3 = Lists.fromInts(new int[]{1, 1}, -1);
        l3 = sol.deleteDuplicates(l3);
        while (l3 != null) {
            System.out.print(l3.val + ", ");
            l3 = l3.next;
        }
        System.out.println();
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode lastLast = null;
        ListNode last = null;
        boolean duplicatedLast = false;
        ListNode newHead = head;
        ListNode curr = newHead;
        while (curr != null) {
            if (last != null) {
                if (last.val == curr.val) {
                    // remove curr
                    last.next = curr.next;
                    curr = last;
                    last = lastLast;
                    duplicatedLast = true;
                } else {
                    if (duplicatedLast) {
                        // remove duplicated last
                        if (lastLast == null) {
                            newHead = curr;
                            last = null;
                        } else {
                            lastLast.next = curr;
                            last = lastLast;
                        }
                        duplicatedLast = false;
                    }
                }
            }

            lastLast = last;
            last = curr;
            curr = curr.next;
        }

        if (duplicatedLast) {
            // remove duplicated last
            if (lastLast == null) {
                newHead = null;
                last = null;
            } else {
                lastLast.next = null;
                last = lastLast;
            }
            duplicatedLast = false;
        }

        return newHead;
    }
}

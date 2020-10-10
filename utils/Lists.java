package utils;

/**
 * Lists
 *
 * @since 2020-10-10
 */
public class Lists {
    public static ListNode fromInts(int[] values, int cyclePos) {
        if (values == null || values.length < 1) {
            return null;
        }

        ListNode head = new ListNode(-1);

        // trans values
        ListNode curr = head;
        ListNode last = null;
        for (int i = 0; i < values.length; i++) {
            curr.val = values[i];
            if (i + 1 < values.length) {
                curr.next = new ListNode(-1);
                curr = curr.next;
            } else {
                last = curr;
            }
        }

        // cycle list pos
        curr = head;
        for (int i = 0; i <= cyclePos; i++) {
            last.next = curr;
            curr = curr.next;
        }

        return head;
    }
}

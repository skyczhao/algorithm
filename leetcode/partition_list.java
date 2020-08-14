package leetcode;

/**
 * partition_list
 * https://leetcode-cn.com/problems/partition-list/
 * 题目太短, 还不是很好理解...
 * 受与x的大小关系而决定位置的前后, 但除此外保持原相对位置(稳定排序)
 *
 * @since 2020-08-14
 */
public class partition_list {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 双链表法
     * 比较好理解, 有空再实现
     * 
     * @param head
     * @param x
     * @return
     */
    public ListNode partition2(ListNode head, int x) {
        // TBA
        return head;
    }

    /**
     * 原地替换法
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        ListNode edge = null;
        ListNode currLast = null;
        ListNode curr = head;
        boolean swap = false;
        while (curr != null) {
            if (curr.val < x) {
                if (swap) {
                    ListNode tmp = curr;
                    // remove from
                    currLast.next = tmp.next;
                    curr = currLast;

                    // insert into
                    if (edge != null) {
                        tmp.next = edge.next;
                        edge.next = tmp;
                        edge = tmp;
                    } else {
                        tmp.next = head;
                        head = tmp;
                        edge = tmp;
                    }
                } else {
                    edge = curr;
                }
            } else {
                swap = true;
            }

            currLast = curr;
            curr = curr.next;
        }

        return head;
    }


    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        partition_list sol = new partition_list();
        ListNode res = sol.partition(node1, 3);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}

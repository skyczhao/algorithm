package leetcode;

import utils.ListNode;
import utils.Lists;

import java.util.Stack;

/**
 * LiangGeLianBiaoDeLcof
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/shuang-zhi-zhen-fa-lang-man-xiang-yu-by-ml-zimingm/
 *
 * @author tobin
 * @since 2021-07-21
 */
public class LiangGeLianBiaoDeLcof {
    public static void main(String[] args) {
        LiangGeLianBiaoDeLcof sol = new LiangGeLianBiaoDeLcof();

        ListNode a = Lists.fromInts(new int[]{4, 1, 8, 4, 5}, -1);
        ListNode b = Lists.fromInts(new int[]{5, 0, 1, 8, 4, 5}, -1);

        ListNode res = sol.getIntersectionNode(a, b);
        if (res != null) {
            System.out.println(res.val);
        } else {
            System.out.println("null");
        }

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        ListNode tmpA = headA;
        while (tmpA != null) {
            stackA.add(tmpA);
            tmpA = tmpA.next;
        }
        Stack<ListNode> stackB = new Stack<>();
        ListNode tmpB = headB;
        while (tmpB != null) {
            stackB.add(tmpB);
            tmpB = tmpB.next;
        }

        ListNode lastEqual = null;
        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            ListNode topA = stackA.pop();
            ListNode topB = stackB.pop();
            if (topA != topB) {
                return lastEqual;
            }
            lastEqual = topA;
        }
        return lastEqual;
    }
}

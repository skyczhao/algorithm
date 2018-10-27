
#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;

    ListNode(int x) : val(x), next(NULL) {}
};

class Solution
{
  public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)
    {
        ListNode *ret = NULL;
        ListNode *last = ret;

        int inc = 0;
        // 2. 用错关系符
        while (l1 != NULL || l2 != NULL)
        {
            int l = 0;
            if (l1 != NULL)
            {
                l = l1->val;
                l1 = l1->next;
            }
            int r = 0;
            if (l2 != NULL)
            {
                r = l2->val;
                l2 = l2->next;
            }

            int sum = l + r + inc;
            int v = sum % 10;
            inc = sum / 10;
            if (last == NULL)
            {
                ret = new ListNode(v);
                last = ret;
            }
            else
            {
                last->next = new ListNode(v);
                last = last->next;
            }
        }

        while (inc > 0)
        {
            // 1. 低级错误, 弄反两个运算符, 导致死循环
            int v = inc % 10;
            inc = inc / 10;

            last->next = new ListNode(v);
            last = last->next;
        }
        return ret;
    }
};

int main()
{
    ListNode *a = new ListNode(5);
    ListNode *b = new ListNode(5);

    // a->next = new ListNode(4);
    // a->next->next = new ListNode(3);

    // b->next = new ListNode(6);
    // b->next->next = new ListNode(4);

    ListNode *next = a;
    while (next != NULL)
    {
        cout << next->val << "->";
        next = next->next;
    }
    cout << endl;

    next = b;
    while (next != NULL)
    {
        cout << next->val << "->";
        next = next->next;
    }
    cout << endl;

    next = (new Solution())->addTwoNumbers(a, b);
    while (next != NULL)
    {
        cout << next->val << "->";
        next = next->next;
    }
    cout << endl;
    return 0;
}
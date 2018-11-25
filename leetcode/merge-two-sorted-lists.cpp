#include <iostream>
using namespace std;

// Definition for singly-linked list.
struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if (l1 == NULL) return l2;
        if (l2 == NULL) return l1;

        ListNode *main = l1;
        ListNode *other = l2;
        if (l1->val > l2->val) {
            main = l2;
            other = l1;
        }

        ListNode *real_head = main;
        while (main->next != NULL && other != NULL) {
            if (main->next->val <= other-> val) {
                main = main->next;
                continue;
            }

            ListNode *tmp = main->next;
            main->next = other;
            other = tmp;
        }
        main->next = other;

        return real_head;
    }
};

int main() {

}
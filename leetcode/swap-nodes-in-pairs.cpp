#include <iostream>
using namespace std;

/**
 * Definition for singly-linked list.
  */

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        // 坑位2: head同样存在坑位1的问题(所以要返回head, 否则原数组只有1,4,3)
        ListNode *th = head;
        ListNode *last = NULL;
        while (th != NULL) {
            ListNode *first = th;
            ListNode *second = th->next;
            if (second == NULL) {
                break;
            }

            // cout << "idx: " << th->val << endl;
            
            first->next = second->next;
            second->next = first;
            // 坑位3: 不能直接使用first = second, 因为同样存在坑位1的问题, 指针的赋值并不能改变原指针的指向
            if (last == NULL) {
                head = second;
            } else {
                last->next = second;
            }

            th = second;            
            last = th->next;
            th = th->next->next;
        }
        return head;
    }

    // void swapNode(ListNode *left, ListNode *right) {
    //     // 坑位1: 指针交换只做到了内容交换, 没实现指针指向位置的交换
    //     // 如果需要实现, 需要 **pointer (双重指针)

    //     left = right;
    //     return;

    //     ListNode *t_left = left;
    //     ListNode *t_next = right->next;

    //     left = right;

    //     cout << left->val << endl;
    //     left->next = t_left;

    //     cout << left->next->val << endl;
    //     left->next->next = t_next;
    // }
};

// void addNode(ListNode *head, int x) {
    
//     if (head == NULL) {
//         return;
//     }

//     cout << x << endl;
//     ListNode newNode = ListNode(x);
    
//     ListNode *t_head = head;
//     while (t_head->next != NULL) {
//         t_head = t_head->next;
//     }
//     t_head->next = &newNode;
// }

void printNode(ListNode *head) {
    ListNode *t_head = head;
    while (t_head != NULL) {
        cout << t_head->val << ",";
        t_head = t_head->next;
    }
    cout << endl;
}

int main() {
    ListNode headNode = ListNode(1);
    ListNode *head = &headNode;
    ListNode *t_head = head;

    ListNode node_2 = ListNode(2);
    t_head->next = &node_2;
    t_head = t_head->next;

    ListNode node_3 = ListNode(3);
    t_head->next = &node_3;
    t_head = t_head->next;

    ListNode node_4 = ListNode(4);
    t_head->next = &node_4;
    t_head = t_head->next;

    printNode(head);

    Solution s = Solution();
    printNode(s.swapPairs(head));
    printNode(head);

}
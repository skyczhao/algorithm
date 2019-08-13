#include <iostream>
using namespace std;

// 执行用时 :12 ms, 在所有 C++ 提交中击败了45.70%的用户
// 内存消耗 :14.7 MB, 在所有 C++ 提交中击败了87.01%的用户

/**
 * Definition for a binary tree node.
 */
  struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode(int x) : val(x), left(NULL), right(NULL) {}
  };

class Solution {
public:
    bool isSymmetric(TreeNode* root) {
        if (root == NULL) {
            return true;
        }

        return isSymmetric(root->left, root->right);
    }

    bool isSymmetric(TreeNode* left, TreeNode* right) {
        // pointer value
        if (right == NULL && left == NULL) {
            return true;
        }
        if (left == NULL || right == NULL) {
            return false;
        }

        // int value
        // BUG 1: not equal instead of equal
        if (left->val != right->val) {
            return false;
        }

        // subtree
        if (!isSymmetric(left->left, right->right)) {
            return false;
        }
        if (!isSymmetric(left->right, right->left)) {
            return false;
        }

        return true;
    }
};

int main() {
    TreeNode left_leaf_node = TreeNode(3);
    TreeNode left_mid_node = TreeNode(2);
    left_mid_node.right = &left_leaf_node;

    TreeNode right_leaf_node = TreeNode(3);
    TreeNode right_mid_node = TreeNode(3);
    right_mid_node.right = &right_leaf_node;

    TreeNode head = TreeNode(1);
    head.left = &left_mid_node;
    head.right = &right_mid_node;

    Solution s = Solution();
    cout << s.isSymmetric(&head) << endl;
    return 0;
}
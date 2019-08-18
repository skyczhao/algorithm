#include <iostream>
using namespace std;

// 执行用时 :8 ms, 在所有 C++ 提交中击败了51.49%的用户
// 内存消耗 :9.7 MB, 在所有 C++ 提交中击败了70.54%的用户

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    TreeNode* pruneTree(TreeNode* root) {
        if (root == NULL) return NULL;

        root->left = pruneTree(root->left);
        root->right = pruneTree(root->right);

        if (root->left == NULL && root->right == NULL && root->val != 1) {
            return NULL;
        }

        return root;
    }
};

TreeNode* list2tree(int values[]) {
    return NULL;
}

void dfs_print(TreeNode* head) {

}

void bfs_print(TreeNode* head) {

}

int main() {
    cout << "ss" << endl;
    return 0;
}
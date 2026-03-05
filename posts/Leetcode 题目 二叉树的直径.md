# Leetcode 题目 二叉树的直径

给你一棵二叉树的根节点，返回该树的 **直径** 。

二叉树的 **直径** 是指树中任意两个节点之间最长路径的 **长度** 。这条路径可能经过也可能不经过根节点 `root` 。

两节点之间路径的 **长度** 由它们之间边数表示。



二叉树的最大直径等于

max(二叉树左子树的最大直径，二叉树的右子树的最大直径，二叉树经过根节点的最大直径)

注意：如果没有左子树 经过根节点的最大直径只能加1 如果没有右子树 同理 左右都没有那就是0

```c++
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int diameterOfBinaryTree(TreeNode* root) {
        //左子树最大直径 右子树最大直径 和 经过根节点的最大直径中选最大值
        if(!root) return 0;
        int leftMax = diameterOfBinaryTree(root->left);
        int rightMax = diameterOfBinaryTree(root->right);
        int left = (root -> left) ? calDepth(root -> left) : - 1;
        int right = (root -> right) ? calDepth(root -> right) : -1;
        int rootMax = left + 2 + right;
        return max(leftMax,max(rightMax,rootMax));
    }
    int calDepth(TreeNode* root){
        if(!root) return 0;
        else{
            int left = (root -> left) ? calDepth(root -> left) : -1;
            int right = (root -> right) ? calDepth(root -> right) : -1;
            return max(left,right) + 1;
        }
    }
    
};
```


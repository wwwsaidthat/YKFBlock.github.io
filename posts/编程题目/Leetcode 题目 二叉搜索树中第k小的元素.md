# Leetcode 题目 二叉搜索数中最小的元素

给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 小的元素（`k` 从 1 开始计数）。

如果根节点就是第k个那么说明左边有K-1个，直接返回

如果根节点左边有大于K-1个，返回左子树的第k小的元素

如果根节点左边有小于K-1个，返回右子树的第K-left-1个元素

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
    int kthSmallest(TreeNode* root, int k) {
        if(!root) return 0;
            int left = calNumOfNode(root->left);
            if(left > k - 1){
                return kthSmallest(root->left,k);
            }else if(left == k - 1){
                return root -> val;
            }else if(left < k - 1){
                return kthSmallest(root->right,k - left - 1);
            }
    return 0;
    }
    int calNumOfNode(TreeNode* root){
        if(!root) return 0;
        else return calNumOfNode(root->left) + calNumOfNode(root->right) + 1;
    }
};
```


# Leetcode 题目 二叉树中序遍历

1. 递归遍历 左中右

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
       vector<int> inorderTraversal(TreeNode* root) {
           vector<int> ans;
           inOrder(root,ans);
           return ans;
       }
       void inOrder(TreeNode* root,vector<int>& ans){
           if(!root)return;
           if(root -> left) inOrder(root->left,ans);
           ans.push_back(root->val);
           if(root -> right) inOrder(root->right,ans);
       }
   };
   ```

   

2. 非递归 用栈存左节点如果到头了 就吐加到结果里面 然后是中间节点，中间节点有了然后处理右子树实现左中右

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
// class Solution {
// public:
//     vector<int> inorderTraversal(TreeNode* root) {
//         vector<int> ans;

//         traverse(ans,root);

//         return ans;
    
//     }

//     void traverse(vector<int>& ans,TreeNode* cur){
//         if(cur == nullptr) return;
//         traverse(ans,cur -> left);
//         ans.push_back(cur -> val);
//         traverse(ans,cur -> right);
//     }
// };

class Solution {
public:
    vector<int> inorderTraversal(TreeNode* root) {
        stack<TreeNode*> st;
        TreeNode* t = root;
        vector<int> ans;
        while(!st.empty()||t!=nullptr){
            if(t != nullptr){
                st.push(t);
                t = t -> left;
            }else{
                t = st.top();
                st.pop();
                ans.push_back(t->val);
                t = t -> right;
            }
        }
        return ans;
    }
};
```


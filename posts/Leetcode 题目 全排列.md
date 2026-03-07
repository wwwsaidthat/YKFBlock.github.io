# Leetcode 题目 全排列

给定一个不含重复数字的数组 `nums` ，返回其 *所有可能的全排列* 。你可以 **按任意顺序** 返回答案。

```c++
class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> ret;
        sort(nums.begin(),nums.end());
        ret.push_back(nums);
        while(next_permutation(nums.begin(),nums.end())){
            ret.push_back(nums);
        }
        return ret;
    }
};
```

另一种就是回溯了

```c++
class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> result;
        vector<int> temp;
        vector<bool> isUsed(nums.size(),false);
        find(result,temp,nums,isUsed);
        return result;
    }

    void find(vector<vector<int>>& result,vector<int>& temp,vector<int>& nums,vector<bool>& isUsed){
        if(temp.size() == nums.size()){
            result.push_back(temp);
            return;
        }

        for(int i = 0 ; i < nums.size() ; i++){
            if(isUsed[i] == false){
                temp.push_back(nums[i]);
                isUsed[i] = true;
                find(result,temp,nums,isUsed);
                temp.pop_back();
                isUsed[i] = false;
            }
        }
    }
};
```




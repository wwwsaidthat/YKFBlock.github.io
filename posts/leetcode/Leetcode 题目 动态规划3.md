# 最长递增子序列

给你一个整数数组 `nums` ，找到其中最长严格递增子序列的长度。

**子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。

dp[i] = (nums[i] > nums[j],i > j)max(dp[j]) + 1;

```c++
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        //dp[i]代表有第i个数的序列最长子序列长度
        vector<int> dp(nums.size(),1);
        for(int i = 1 ; i < nums.size() ; i++){
            //dp[i] = (nums[i] > nums[j],i > j)max(dp[j]) + 1;
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j] ){
                    dp[i] = max(dp[i] , dp[j] + 1);
                }
            }
        }
        return *(max_element(dp.begin(),dp.end()));
    }
};
```


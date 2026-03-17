# Leetcode 题目 最大子数组和

给你一个整数数组 `nums` ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

**子数组**是数组中的一个连续部分

前缀和的题目，每个前缀和都和之前的最小的前缀和做差，最小值的维护是通过如果到这个了比最小值更小，那么就更新来实现维护的。

```c++
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        vector<int> sum(nums.size() + 1);
        sum[0] = 0;
        for(int i = 1 ; i < sum.size() ; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int maxAns = sum[1];
        int minNum = sum[0];
        for(int i = 1 ; i < sum.size() ; i++){
            maxAns = (sum[i] - minNum >= maxAns) ? sum[i] - minNum : maxAns;
            minNum = (minNum < sum[i]) ? minNum : sum[i];
        }
        return maxAns;
    }
};
```


# 三数之和

给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请你返回所有和为 `0` 且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

1. 整体思路 第一个元素为基准，调整后面两个元素直到和为0
2. 去重思路 
   1. 和上一个元素相同的元素直接跳过，两个相同的基准元素是重复
   2. 移动剩下两个元素的时候，如果是和上一个元素相同的那么就跳过直到不一样
3. 移动思路 
   1. 计算一个整体和 sum 大于0 右边往左移动 小于0 左边往右移动
   2. 注意 当sum等于0的时候左右两变都要移动

```c++
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> ret;
        sort(nums.begin(),nums.end());
        int target = 0;
        int sum = 0;
        for(int i = 0 ; i < nums.size() - 2; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i - 1] ) continue;
            int j = i + 1, k = nums.size() - 1;
            sum = nums[i] + nums[j] + nums[k];
            while( j < k ){
                if(sum == 0){
                    ret.push_back(vector<int>{nums[i],nums[j],nums[k]});
                    j++; k--;
                    while(j < k && nums[j] == nums[j - 1]) j++;
                    while(j < k && nums[k] == nums[k + 1]) k--;
                }else if(sum > 0){
                    k--;
                    while(j < k && nums[k] == nums[k + 1]) k--;
                }else {
                    j++;
                    while(j < k && nums[j] == nums[j - 1]) j++;
                }
                sum = nums[i] + nums[j] + nums[k];
            }

        }
        return ret;
    }
};
```


给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的子数组的个数* 。

子数组是数组中元素的连续非空序列 

前缀和 -- 前面空出来一个0 后面每位都是前面所有的和

找和为k的子数组是连续的 那就是前缀和差是k的有几组就是几组

0 a[1] a[1]+a[2] ...

```c++
class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        //前缀和
        vector<int> sum(nums.size() + 1);
        sum[0] = 0;
        for(int i = 0 ; i < nums.size();i++){
            sum[i + 1] = sum[i] + nums[i];
        }
        int count = 0;
        for(int i = 0 ; i < sum.size();i++){
            for(int j = 0 ; j < i ;j++){
                if(sum[i] - sum[j] == k){
                    count ++;
                }
            }
        }
        return count;
    }
};
```


# 零钱兑换

给你一个整数数组 `coins` ，表示不同面额的硬币；以及一个整数 `amount` ，表示总金额。

计算并返回可以凑成总金额所需的 **最少的硬币个数** 。如果没有任何一种硬币组合能组成总金额，返回 `-1` 。

你可以认为每种硬币的数量是无限的。



dp[i] = -1;

注意1：初始化的时候对于超过目标值的硬币面值不需要初始化

注意2：初始化都成-1 方便处理不能组成的

注意3：目标值小于最小面额无法实现--剪枝

总结1：对于所有数组下标都需要jian'cha

```c++
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
       // dp[amount] = dp[amount - coins[i]] + 1
       if(amount == 0) return 0;
       if(amount < *min_element(coins.begin(),coins.end())) return -1; 
       vector<int> dp(amount + 1,-1);
       dp[0] = 0;
       for(int i = 0 ; i < coins.size() ; i++){
        if(coins[i] > amount) continue;
        dp[coins[i]] = 1;
       }

       for(int i = 1; i < amount + 1; i ++){
        for(int j = 0 ; j < coins.size() ; j++){
            if(i > coins[j] && dp[i - coins[j]] > 0){
                dp[i] = (dp[i] == -1 ) ? dp[i - coins[j]] + 1 : min(dp[i],dp[i - coins[j]] + 1);
            }
        }
       }
       return dp[amount];
    }
    
};
```


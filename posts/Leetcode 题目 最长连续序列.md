# Leetcode 题目 最长连续序列

给定一个未排序的整数数组 `nums` ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 `O(n)` 的算法解决此问题。

思路就是找到每个序列最开始的位置然后依次往后找下一个是否在，注意先用set处理一下nums，否则有很多重复的将会很浪费时间

```c++
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        //遍历一遍找最左和最右
        if(nums.size() == 0) return 0;
        int ret = 1;
        set<int> s;
        for(auto it : nums){
            s.insert(it);
        }
        for(auto it : s){
            if(s.find(it - 1) == s.end()){
                int len = 1;
                int num = it;
                while(s.find(num + 1) != s.end()){
                    num++; len++;
                }
                ret = (ret > len) ? ret : len;
            }
        }
        return ret;

    }
};
```


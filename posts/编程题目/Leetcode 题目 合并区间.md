## Leetcode 题目 合并区间

以数组 `intervals` 表示若干个区间的集合，其中单个区间为 `intervals[i] = [starti, endi]` 。请你合并所有重叠的区间，并返回 *一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间* 。

这题的关键在于先排序，排完序之后插入的顺序就确定了，排序是按照starti的顺序从小到大排序

另外一个关键点在于需要新开一个二元数组，然后合并过程中不断更新更大的那边即可，尽量不要在原来的数组上进行操作。

```c++
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        sort(intervals.begin(),intervals.end(),[](vector<int>&a,vector<int>&b){return a[0] < b[0] ;});
        vector<vector<int>> ret(1);
        ret[0] = intervals[0];
        int ptr = 0;
        for(int i = 1 ; i < intervals.size() ; i++){
            if(intervals[i][0] <= ret[ptr][1])
                ret[ptr][1] = max(intervals[i][1],ret[ptr][1]);
            else {
                ptr ++;
                ret.push_back(intervals[i]);
            } 
        }
        return ret;
    }
};
```


# Leetcode 题目 找到字符串中所有字母异位词



给定两个字符串 `s` 和 `p`，找到 `s` 中所有 `p` 的 **异位词** 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

**示例:**

```
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词
```

思路：滑动窗口思想，在s中想象有一个p长度的窗口在滑动，然后维护这个窗口里面字母的情况

可以用vector(26)来作为每个字母统计的字典，然后比较两个vector是否相同就可以了

滑动的时候把加入窗口的字符增加数量，移出窗口的字符减少数量，这样就可以实现维护了

然后比较就行了，如果和p的统计字典vector比较一下如果相同就把当前的起始位置加到ret vector里面就行了

注意考虑边界问题，开头和结尾重点关注不要漏 不好处理的单独解决

```c++
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        if(s.size() < p.size()) return vector<int>{};
        vector<int> ret;
        int begin = 0,end = p.size() - 1;
        vector<int> pCount(26,0),sCount(26,0);
        for(int i = 0 ; i < p.size() ; i++){
            pCount[p[i] - 'a']++;
            sCount[s[i] - 'a']++;
        }
        if(sCount == pCount) ret.push_back(0);
        
        for(begin = 1,end++;end < s.size() ; begin++,end++){
            sCount[s[begin - 1] - 'a']--;
            sCount[s[end] - 'a']++;
            if(sCount == pCount) ret.push_back(begin);

        }
        return ret;
    }
    
};
```


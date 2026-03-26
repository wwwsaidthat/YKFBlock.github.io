# 无重复字符的最长子串

给定一个字符串 `s` ，请你找出其中不含有重复字符的**最长子串**的长度

用一个set存储，遍历一遍，如果没在 set中找到那就**insert** ，len ++如果超过当前maxLen则替换成为maxLen

如果找到了，就从这个子串的开始位置开始**erase**直至子串的set中找不到，len-- ，然后再把这个字符加进来 len++

最长的就是maxLen

```c++
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        set<char> set;
        int len = 0;
        int maxLen = 0;
        for(int i = 0 ; i < s.size() ; i++){
            if(set.find(s[i]) == set.end()){
                set.insert(s[i]);
                len++;
                if(len > maxLen) maxLen = len;
            }else{
                int beginPos = i - len;
                for(int j = beginPos;j < i && set.find(s[i]) != set.end(); j++){
                    set.erase(s[j]);
                    len--;
                }
                set.insert(s[i]);
                len++;
            }
        }
        return maxLen;
    }
};
```


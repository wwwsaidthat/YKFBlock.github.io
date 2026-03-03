# Leetcode 题目 盛最多水的容器

给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])` 。

找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

**说明：**你不能倾斜容器。



1. 首先明确面积等于长✖宽 受两个因素影响
   1. 长：两根木条中间的距离作为长
   2. 宽：木桶原理，两边的木条中最短的作为宽
2. 从两边往中间挪动长越来越短，要想变大只能通过宽变大，宽受两边两根木条长度中更短的那个的影响
3. 因此只需要在当向内移动过程中，当下一个木条长度超过了当前两边更短的那个的时候才可能会让面积变得更大，才需要计算一下面积维护最大值，别的情况不需要重新算的。
4. 当两遍靠在一起的时候，停止移动



```c++
class Solution {
public:
    int maxArea(vector<int>& height) {
        int left = 0,right = height.size() - 1;
        int area = min(height[left],height[right]) * (right - left);
        while(left < right){
            if(height[left] < height[right]){
                left++;
                if(height[left] > height[left - 1]){
                    area = max(area,(right - left) * min(height[left],height[right]));
                }
            }else{
                right --;
                if(height[right] > height[right + 1]){
                    area = max(area,(right - left) * min(height[left],height[right]));
                }
            }
        }
        return area;
    }
};
```


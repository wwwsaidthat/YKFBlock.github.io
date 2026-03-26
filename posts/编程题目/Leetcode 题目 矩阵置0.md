# Leetcode 题目 矩阵置0

给定一个 `*m* x *n*` 的矩阵，如果一个元素为 **0** ，则将其所在行和列的所有元素都设为 **0** 。请使用 **[原地](http://baike.baidu.com/item/原地算法)** 算法**。**

 原地的算法就是拿第一排第一列直接存，单独用两个变量存第一排第一列就好了

注意循环处理从第二行第二列开始

```c++
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        //第一行 第一列
        bool row0 = false, col0 = false;
        int row = matrix.size();
        int col = matrix[0].size();
        for(int i = 0 ; i < col; i++ ){
            if(matrix[0][i] == 0) row0 = true;
        }
        for(int i = 0 ; i < row; i++){
            if(matrix[i][0] == 0) col0 = true;
        }
        for(int i = 1 ; i < row ; i ++){
            for(int j = 1 ; j < col ; j++ ){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1 ; i < col; i++ ){
            if(matrix[0][i] == 0){
                for(int j = 0 ; j < row ; j++){
                    matrix[j][i] = 0;
                }
            }
        }
        for(int i = 1 ; i < row;i++){
            if(matrix[i][0] == 0){
                for(int j = 0 ; j < col ; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        if(row0) {
            for(int i  = 0 ; i < col ; i++){
                matrix[0][i] = 0;
            }
        }
        if(col0){
            for(int i = 0; i < row;i++){
                matrix[i][0] = 0;
            }
        }
    }
    
};
```


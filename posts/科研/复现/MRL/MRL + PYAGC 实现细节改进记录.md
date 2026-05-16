# MRL + PYAGC 实现细节改进记录

1. 使用gcn有监督作为对照
2. dgi中的编码器要用tunedgcn 加一个激活函数prelu
3. cora数据集对于ccassg的最优生成视图的概率为点掩码0.1 边0.4
4. ccassg的$\lambda$对于cora最好选取0005–0.001
5. 
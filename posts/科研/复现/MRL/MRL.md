# MRL.py

```python
class Matryoshka_CE_Loss(nn.Module):
	def __init__(self, relative_importance: List[float]=None, **kwargs):
		super(Matryoshka_CE_Loss, self).__init__()
		self.criterion = nn.CrossEntropyLoss(**kwargs)
		# relative importance shape: [G]
		self.relative_importance = relative_importance

	def forward(self, output, target):
		# output shape: [G granularities, N batch size, C number of classes]
		# target shape: [N batch size]

		# Calculate losses for each output and stack them. This is still O(N)
		losses = torch.stack([self.criterion(output_i, target) for output_i in output])
		
		# Set relative_importance to 1 if not specified
		rel_importance = torch.ones_like(losses) if self.relative_importance is None else torch.tensor(self.relative_importance)
		
		# Apply relative importance weights
		weighted_losses = rel_importance * losses
		return weighted_losses.sum()
```

1. 这个类是用来计算交叉熵损失的 cross entropy loss $\rightarrow$ CE_LOSS继承自父类nn.Module

   1. 这个类的构造函数有三个参数

      1. self $\rightarrow$ this
      2. Relative_importance（参数名） 类型是float的list  默认值是none
      3.  **kwargs就是用来传入其他参数其他所有参数都可以用这个捕捉，注意kwargs只不过大家都习惯这么写，keyword arguments

      **注意**：有很多继承过来的函数会有很多参数，你自己不想重新写一遍，你就可以在**kwargs里面直接传进来

      1. super（sonClass，self）这个是调用父类的构造函数
      2. Self.criterion = nn.CrossEntropyLoss这个是相当于把一个类实例化 criterion是一个对象，用来计算
      3. self.relative_importance = relative_importance 这个就是简单的赋值没什么好说的

   2. forward函数是用来向前传播的函数，计算最终的损失loss

      1. $$
         \boxed{
         {\min\limits_{ {\{W_{1:m}\}}_{m\in \mathcal{M}} ,\theta_F}} \frac{1}{N}\sum_{i\in[N]}\sum_{m\in \mathcal{M}} \mathcal{L}	(W_{1:m}\cdot F(x_i;\theta_F) ; y_i)
         }
         $$

      2. 一阶张量是向量（一维数组） 二阶张量是矩阵（二维数组） 三阶张量就是多个表格摞在一起（三维数组）

      3. 这里的output就是一个三维数组\[G]\[N][C] 这里的target就是一个一维数组[N]

         1. 这个output是指模型输出的预测结果
         2. target是指真实的标签是哪个one-hot分布，只有真正的那个标签是1其余的都是0

      4. Torch.stack()用来把一维数组变成二维数组，二维数组变成三维数组 ...

      5. CrossEntropyLoss这个类的对象直接作为方法（）的时候是在调用forward方法，因为实现了\_\_call_\_方法

         交叉熵：
         $$
         CrossEntropy = - \sum\limits_{i = 1}^{C}y_i\cdot\log(p_i)
         $$

      6. losses是一个一维向量，向量的每个维度代表某个维度的向量对应的损失是多少，向量的维数是一共有多少监督维度

      7. rel_importance作为权重，如果参数传了那就用传入的，反之则直接全都置1，每个监督维度的权重

      8. 把6和7相乘并相加，结果返回即可

```python
class MRL_Linear_Layer(nn.Module):
	def __init__(self, nesting_list: List, num_classes=1000, efficient=False, **kwargs):
		super(MRL_Linear_Layer, self).__init__()
		self.nesting_list = nesting_list
		self.num_classes = num_classes # Number of classes for classification
		self.efficient = efficient
		if self.efficient:
			setattr(self, f"nesting_classifier_{0}", nn.Linear(nesting_list[-1], self.num_classes, **kwargs))		
		else:	
			for i, num_feat in enumerate(self.nesting_list):
				setattr(self, f"nesting_classifier_{i}", nn.Linear(num_feat, self.num_classes, **kwargs))	

	def reset_parameters(self):
		if self.efficient:
			self.nesting_classifier_0.reset_parameters()
		else:
			for i in range(len(self.nesting_list)):
				getattr(self, f"nesting_classifier_{i}").reset_parameters()


	def forward(self, x):
		nesting_logits = ()
		for i, num_feat in enumerate(self.nesting_list):
			if self.efficient:
				if self.nesting_classifier_0.bias is None:
					nesting_logits += (torch.matmul(x[:, :num_feat], (self.nesting_classifier_0.weight[:, :num_feat]).t()), )
				else:
					nesting_logits += (torch.matmul(x[:, :num_feat], (self.nesting_classifier_0.weight[:, :num_feat]).t()) + self.nesting_classifier_0.bias, )
			else:
				nesting_logits +=  (getattr(self, f"nesting_classifier_{i}")(x[:, :num_feat]),)

		return nesting_logits
```

pytorch会根据forward函数自动计算梯度和其他的一些工作，因此写代码的时候永远都要写model(para) 而不是model.forward(para)

1. 这个线性层的工作是为了把一个输出的特征转换成对应的分类结果，这个的output是上面CE_loss的输入
   1. 输入：特征向量
   2. 输出：分类结果，或者说是叫做各个类别的得分
2. nesting_list是用来作为监督的维度数目构成的列表
3. efficient = true那么就是相当于不同的维度对应的变换矩阵是从最大的切片
4. 初始化的时候只是把这个nestingclassifier处理器规定好对应的linear的参数
5. reset函数就是在对训练前对这个W和b的参数进行初始化
6. reset之后相当于拿到了一个随机的扑克牌，之后W和b会根据loss更新，在调用这个forward函数的时候我们的w和x是都是确定好的
7. torch.matmul是一个矩阵乘法的计算所以forward过程的计算就是相当于是在计算$y = x W^T+b$

```python
class FixedFeatureLayer(nn.Linear):
    '''
    For our fixed feature baseline, we just replace the classification layer with the following. 
    It effectively just look at the first "in_features" for the classification. 
    '''

    def __init__(self, in_features, out_features, **kwargs):
        super(FixedFeatureLayer, self).__init__(in_features, out_features, **kwargs)

    def forward(self, x):
        if not (self.bias is None):
            out = torch.matmul(x[:, :self.in_features], self.weight.t()) + self.bias
        else:
            out = torch.matmul(x[:, :self.in_features], self.weight.t())
        return out
```

输入向量的前in_features维，输出一个logits的分类结果

所以就是这个类实现的就是拿到一个特征向量，对其前k维度进行下游任务--分类处理




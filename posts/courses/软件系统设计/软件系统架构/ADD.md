1. 软件架构从何而来？软件架构驱动因素都有几种？排序依据
	1. 来自软件架构驱动因素
	2. 有六种
		1. 约束：系统必须满足的要求，已经定好的边界
		2. 质量属性：对于系统的质量的可度量要求，例如可用性
		3. 功能性需求：系统必须完成的相应的功能性的需求
		4. 系统类型：绿地新领域 绿地成熟领域 棕地系统
		5. 设计目标：本次设计的原因
		6. 关注点：代码库怎么组织 输入的有效性 异常的处理等等问题
	3. 可逆性 影响
2. 软件设计中都有哪些通用的设计策略？给出架构的例子
	1. 生抽可迭代二分
		1. 生成与测试
		2. 抽象
		3. 可复用
		4. 迭代和细化
		5. 分解
		6. 分而治之
3. ADD3.0的流程
	1. Review Inputs 审视输入
	2. Establish Iteration goal by selecting drivers 通过选择驱动因素确定本轮迭代目标
	3. Choose one or more elements of the system to refine 选择系统中一个或者多个待细化的元素
	4. Choose one or more design concepts that satisfy the selected drivers 选择能满足所选驱动因素的一个或者多个设计概念
	5. Instantiate architectural elements, allocate responsibility, and define interfaces 实例化架构元素、分配职责、定义接口
	6. Sketch views and record design decisions 画草图并记录设计决策
	7. Perform analysis of current design and review iteration goal and achievement of design purpose对当前设计进行分析，回顾迭代目标，检查设计目的是否达成
		2-7重复迭代
4. 架构文档化(视图)
	1. Module view：系统在代码层怎么分
	2. C&C view：系统在运行的时候怎么跑起来的，数据怎么流
	3. Allocation view：分配部署视图
	4. Quality view：质量视图
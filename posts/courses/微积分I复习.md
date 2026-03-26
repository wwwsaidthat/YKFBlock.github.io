

# 答题的策略zzzz

先做会的，要保证将不该犯的错误减少到最低

不要奔满分去，放平心态

采取做一道准一道的策略

最最重要的是要用好演草纸，别慌，有手就行的题目，别着急算的一塌糊涂

# 期中复习

## **总结一下用泰勒展开式证明问题的**

1. 泰勒展开式是
   $$
   f(x)=f(x0)+(x-x0)f’(x0)+\frac{(x-x0)^2f''(x0)}{2}+o(x-x0)^2
   $$

2. 别那么死性    f（x）可以是f'（x）

3. 我们现在一共有两种选择(~~哪怕试一下子都可以~~)

   - 一种是已经知道的变成x

   - 一种是将已经知道的变成x0

4. 利用绝对值不等式将一个带有绝对值的减法变成一个不带绝对值的加法

5. 我们一般习惯把关于导数信息给的多的当作x~0~

6. <span style="color:pink">遇到非常对称的情况我们可以使用泰勒展开式，如果余下了两个$\xi$,  我们可以使用拉格朗日中值定理</span>

7. <span style="color:pink">f^(n+1)^($\xi$~3~) ($\xi$~2~-$\xi$~1~) = f^(n)^($\xi$~2~) - f^(n)^($\xi$~1~)</span>

8. <span style="color:pink">然后把$\xi$~2~-$\xi$~1~放缩放大一点放到泰勒展开式的原始范围</span>

9. <span style="color:pink">注意证明收敛的时候，$\frac{1}{x^\lambda}$其中$\lambda$必须得大于1才可以</span>



## 告诉你一个式子要求你求出来他的极限存在



我们有几种方法

1. 我们可以证明单调有界那么极限存在，极限存在之后我们就可以带入求解了

2. 我们可以使用压缩映照原理（放缩的时候我们可以使用拉格朗日中值定理）

3. 上下来回折腾的那种我们可以使用我们的一个小结论就是
   $$
   \lim\limits_{2n-1\to\infin}a_n=A\\
   \lim\limits_{2n\to\infin}a_n=A
   $$
   上面的和下面的是充分必要的关系
   $$
   \lim\limits_{n\to\infin}a_n=A
   $$



## <span style="color:pink">使用泰勒展开式+积分运算得到表达式</span>

1. $$
   f(x)=f(x_0)+(x-x_0)f'(x)+\frac{(x-x_0)^2}{2}f''(\xi)\\
   然后左右同时积分可以得到一个等式\\
   \int_a^b f(x)dx = f(x_0)(b-a)+\int_a^bf'(x)(x-x_0)dx+\int_a^b\frac{f''(\xi)(x-x_0)^2}{2}dx
   $$

2. 另外的一种想法就是对于变上限积分进行展开
   $$
   \int_a^x f(t) dt = \int_a^{x_0}f(t)dt +f(x)(\int_a^x f(t) dt - \int_a^{x_0}f(t)dt)+\\\frac{1}{2}f'(\xi)(\int_a^x f(t) dt - \int_a^{x_0}f(t)dt)^2
   $$

3. 在进行f''($\xi$)的提取的时候，需要使用m$\le$f''($\xi$)$\le$M

4. 提取出来之后我们不就是在区间(m,M)上了么，再由介值定理，可以在反推回去

5. [Taylor](#Taylor)

## <span style="color:pink">泰勒公式构造琴生不等式</span>[琴生不等式](#一个小结论--琴生不等式：[步骤](#<span style="color:pink">泰勒公式构造琴生不等式</span>[琴生不等式](#一个小结论--琴生不等式)))

1. $$
   f''(x) > 0\\ f(x) > f(x_0)+(x - x_0)f'(x_0)
   \\(f''(\xi)\frac{(x-x_0)^2}{2}\ge0)
   \\let x_0=\int_a^bg(x)dx\\
   x=g(x)\\
   之后在等式两边同时积分\\
   (x-x_0)积完之后就会变成0\\
   \frac{\int_a^bf(g(x))dx}{b-a}\ge f(\frac{\int_a^bg(x)dx}{b-a})
   $$

2. [柯西不等式(积分)](#方法四：柯西不等式)

3. 

## **生猛构造法**：

一定记住了：

比较突兀的告诉你几个值，还可能有导数的值，我们直接使用生猛的构造方法

就是构造一个函数，让他在特殊点的函数值和所给的函数一样构造出来多个零点

进行反复罗尔中值定理

找到一个相等的情况就ok

证明题好像是不会考但是要复习到



## 划分区间法

还有就是第一问用某一个零点存在定理来找到一个区间的分界点然后在两个区间中分别使用拉格朗日定理然后分别对应f'(a)和f'(b)

零点存在定理我们可以使用一些小的技巧除了带入开头的值结尾的值我们呢还可以多带入几个值只要他们相加为0，那么在这几个区间上我们一定会找到有根存在（这种情况并无法将区间固定在某个更小的区间，但是我们知道，一定存在于这个大区间的子区间上）

另外还有是好几个点的导数相加为0（a,b）上

存在$\xi$属于（0，1）f(0)=0  f(1)=1
$$
f'(x1)+f'(x2)+f'(x3)=3
$$

$$
f'(x1)=\frac{f(\frac{1}{3})-f(0)}{\frac{1}{3}-0}\\
f'(x2)=\frac{f(\frac{2}{3})-f(\frac{1}{3})}{\frac{2}{3}-\frac{1}{3}}\\
f'(x3)=\frac{f(1)-f(\frac{2}{3})}{1-\frac{2}{3}}
$$



这种的我们要分成n段每一段等于
$$
xi=\frac{i(b-a)}{n}+a
$$
这个公式最终的结果
$$
3f(\frac{1}{3})-3f(0)+3f(\frac{2}{3})-3f(\frac{1}{3})+3f(1)-3f(\frac{2}{3})=3
$$




反过来如果就是几个导数的倒数相加等于0

那么我们有
$$
\frac{1}{f'(x1)}+\frac{1}{f'(x2)}+\frac{1}{f'(x3)}=3
$$


我们可以找到这样的xi
$$
f(xi)=\frac{f(a)-f(b)}{n}+f(a)
$$

$$
f(x4)=\frac{1}{3}\\
f(x5)=\frac{2}{3}\\
$$

we have
$$
\therefore f'(x1)=\frac{f(x4)-f(0)}{x4-0}\\
\qquad f'(x2)=\frac{f(x5)-f(x4)}{x5-x4}\\
\qquad f'(x3)=\frac{f(1)-f(x5)}{1-x5}
$$
because f(…)-f(…)=$\frac{1}{3}$

so we can get 
$$
\frac{1}{f'(x1)}+\frac{1}{f'(x2)}+\frac{1}{f'(x3)}=3-3*x5+3*x5-3*x4+3*x4-0=3
$$
so we have got the final answer

这个题有的时候是没有第一问的，没有第一问就意味着要自己想到

- 最大值点经常是分割区间的点



## 构造辅助函数法

另外还有可能是一个根有不同的两阶导数那么这种我们就要构造辅助函数来解决

有一种比较简单的想法我们可以见到f’（x）我们就给他配一个（a-b）

这样我们就可以分别凑出来了

有的时候我们需要将某一个整体视为一个函数
$$
f'(x)+g(x)(f(x)\pm c)=0
\\
构造：
\\
F(x)=e^{\int{g(x)dx}}f(x)
$$

我们还可以将积分的步骤转化成待定系数。但是适用范围会比较窄
$$
{e^x}(ax+b)\rightarrow e^x(ax+a+b)\\
if\quad e^{nx}\\
e^{nx}(ax+b)\rightarrow e^{nx}(anx+bn+a)\\
if(ax^2+bx+c)
e^{nx}(ax^2+bx+c)\rightarrow e^{nx}(anx^2+bnx+cn+2ax+b)\rightarrow \\
 e^{nx}[anx^2+(bn+2a)x+cn+b]
 然后就可以对应系数相等
$$

在构造函数的时候，如果直接让F（x）=要证明的式子算不出来的话，把它变成F‘(x)然后反着积分积回去


## 拉格朗日余项用来证明问题

$$
\lim\limits_{n\to\infin}\sum\limits_{i=0}^n\frac{1}{k!}=e
$$


$$
e^x=1+x+\frac{x^2}{2!}+……+\frac{x^n}{n!}+\frac{f^{(n+1)}(\xi)·x^{n+1}}{(n+1)!}
$$
x=1

so 
$$
e^1=0!+1!+\frac{1}{2!}+……+\frac{1}{n!}+\frac{f^{(n+1)}(\xi)·x^{n+1}}{(n+1)!}
$$

$$
e^1-(0!+1!+\frac{1}{2!}+……+\frac{1}{n!})=\frac{f^{(n+1)}(\xi)}{(n+1)!}\\
e-\lim\limits_{n\to\infin}\sum\limits_{i=0}^n\frac{1}{k!}=\lim\limits_{n\to\infin}\frac{e^{\xi}}{(n+1)!}<\frac{e^{\xi}}{n}<\xi_1
$$
这样就可以从定义的角度推出来
$$
\lim\limits_{n\to\infin}\sum\limits_{i=0}^n\frac{1}{k!}=e
$$
这个公式是成立的



## 如何来求解拉格朗日余项当中的$\theta$

主题的思想呢就是我们的拉格朗日余项这个含有$\theta$项的这个和含有正常的这项的泰勒展开式于下一项的拉格朗日余项“$\xi$”是相等的这里我们就拿一阶导数和二阶导数来举例子
$$
f'(\theta(x)x)=f'(0)+\frac{1}{2}xf''(\xi)
$$

$$
\frac{f'(\theta(x)x)-f'(0)}{\theta(x)x-0}*\theta(x)=\frac{1}{2}f''(\xi)
$$

$$
f''(0)*\theta(x)=\frac{1}{2}f''(\xi)
$$

$\xi$是在0到x中间的而x是趋近于0的所以$\xi$=0  因此

$$
\lim\limits_{x\to0}\theta(x)=\frac{1}{2}
$$

## 求解极限的注意事项

1. 我们要想想首先在相加减的情况下万万不可以就展开一项就走了，最后检查的时候我们就是看的这个问题，我们在使用泰勒展开式的时候我们一定要把皮亚诺余项写上

2. 第二使用拉格朗日中值定理求解极限的前提是分离出来之后后面的极限是存在的这一点是保证极限的四则运算是正确的

3. 我们要使用拉格朗日中值定理的时候我们需要直接将趋近的数值代入，

   **万万不可以带入 上限或者下限，求极限的时候就直接得到的是数值**

4. 等价无穷小我们在乘除法的时候随便使用（原理就是更高次项我们会在乘完了之后没有任何的用处所以可以直接换成对应的等价无穷小量）

5. 等价无穷大掌握一个--斯塔林公式
   $$
   n! 与  \sqrt{2\pi n}(\frac{n}{e})^n是等价无穷大但是后者会稍微比前者小一点点
   但是对于n^n则是比n!\\
   更大的量还有就是(\frac{1+n}{2})^n比n！更大的
   如果把2换成3那就是更小的但是斯塔林公式更为接近\\
   也就是说(\frac{1+n}{2})^n>n！>(\frac{1+n}{3})^n
   \\n^n>(\frac{n+1}{2})^n>n!>\sqrt{2\pi n}(\frac{n}{e})^n>(\frac{n+1}{3})^n
   $$
   
6. 

$$
\infin^0 \\0^\infin\\1^\infin--》这个的小技巧还记得不，不记得去找武老师\\\frac{\infin}{\infin}\\\frac{0}{0}\\\infin-
\infin\\通分\\倒带换\\ln运算\\变成洛必达可用的形式\\还可以进行
$$

7. [武老师](#wu)

8. $$
   \infin -\infin和0 - 0 的这两种类型我们要使用的是提取的方法\\
   具体请看例题
   $$

9. [example](#example)

10. 洛必达的安全性很高但是速度有点慢[洛注意](#关于洛必达)

11. 但是洛必达必须得是能算出来极限才算是有效，否则都是无效的（$\infin$就是无效的)

12. |q|^n^怎么放缩？（q<1）

$$
\frac{1}{|q|}=1+h
(h>0)\\
(1+h)^n=\sum\limits_{i=0}^nC_n^ih^{n-i}\\
$$
放缩到比求的东西大一阶就好了仅此而已如果不是很好放缩的话我们还可以

（对于
$$
\lim\limits_{n\to\infin}n^2 q^n=0（q<1）
$$
)
$$
\frac{1}{|q|}=1+h
(h>0)\\
(1+h)^n>C_n^3h^3=\frac{n(n-1)(n-2)}{6}--这样就很不好放缩\\
(1+h)^{n+2}>C_{n+2}^3h^3=\frac{n(n+1)(n+2)}{6}h^3>\frac{{(nh)}^3}{6}\\
原式<\frac{n^2(1+h)^{2}}{n^3h^3}=\frac{C}{n}(C--const-int)=0\\
又有原式>0
夹逼得出原式等于0
$$

9. 还有一个比较好用的小结论：

$$
\lim\limits_{n\to\infin}{(a_1^n+a_2^n+a_3^n……a_m^n)}^{\frac{1}{n}}=max(a_1,a_2,a_3,……,a_n)
$$
10. n!怎么放缩？

$$
\frac{a^n}{n!}-->\frac{a}{1}\frac{a}{2}\frac{a}{3}……\frac{a}{a}(\frac{a}{a+1})^{n-a}
\\在这之后我们就可以等效为C*q^n
\\使用牛顿的那个二项式展开
$$



11. Stolz定理

$$
\lim\limits_{n\to\infin}\frac{\sum\limits_{i=0}^{n}a_i}{{\sum\limits_{i=0}^{n}}{i}}=\lim\limits_{n\to\infin}\frac{a_n}{n}
$$

12. 注意如果x趋近于负无穷，注意正负号的转换

13. 三角形面积别忘记除以2

14. ### wu

    - 
      $$
      1^\infin的极限求解\\
      1对应的部分减去1乘以无穷的部分的极限是多少记为A\\
      那么答案就是e^A
      $$

15. ### example

    1. $$
       \lim\limits_{x\to0}\frac{x^{sinx}-sinx^{sinx}}{x^3} = \lim\limits_{x\to0}\frac{x^{sinx}(1-({\frac{sinx}{x}})^{sinx})}{x^3}\\
       $$

    2. 提取大法好

    



## 函数的连续性的求解

1. 函数可导那么函数就一定是连续的
2. 函数连续就代表左右的极限存在而且相等
3. 函数的可导那么函数导数的左极限等于函数的右极限
4. 函数可导还可以使用在此处连续加上差商的极限是存在的
5. 如果有难算的极值点的数值，慢一点算哈
6. 你的正负号格外注意



## 隐函数的导数，高阶导数的求解,参数方程的求导

1. 正负号别整反了

2. $$
   小结论：{[\frac{1}{(n+\lambda)}]}^{(n)}=(-1)^{n}·n!·\frac{1}{(n+\lambda)^{n+1}}
   $$

3. 莱布尼兹公式

$$
\sum\limits_{i=0}^n C_n^iu^{(i)}v^{(n-i)}
$$

4.二阶导数
$$
\frac{d\frac{dy}{dx}}{dx}=\frac{d^2y}{dx^2}=f''(x)
$$
-   注意千万别忘记求两次d（微分）
  - 尤其是在求参数方程的导数的时候

5.参数方程的求导
$$
\frac{dy}{dx}=\frac{\frac{dy}{dt}}{\frac{dx}{dt}}
$$
***偏微分***{了解}
$$
\frac{dy}{dx}=-\frac{\frac{\partial F(x,y)}{\partial y}}{\frac{\partial F(x,y)}{\partial x}}
$$

## 切线怎么求

方法就是求切点，切点横坐标确定f'(x),切点的坐标确定线的方程

## 介值定理

对于单调递增的函数（此处纯粹为了描述方便）f(x)在（a,b）上一定存在某一个$\xi$使得f($\xi$)=$\lambda$

(其中$\lambda$$\in$(f(a),f(b)))

介值定理可以使用在泰勒展开式+积分的$\xi$的提取中

## 海因定理

不打了累了



## 间断点

分母为零$\rightarrow$关注一下分子为不为0

函数的定义域不存在的点

两边有一个极限不存在就是第二类间断点

两边的极限都存在而且相等是可去
$$
反之为跳跃间断点（经常考的是|\quad|类型和e^\frac{1}{x}这种的）
$$
经常忘记的是tanx这种周期性的忘记了k可以洛必达的不是$\infin$一般都是可去间断点

## 如何证明数列的极限是不存在的

首先我们可以从找两个特殊的子列的极限是不相等的从而证明出数列的极限是不存在的

想不到怎么办

极限是$\infin$

## 基础公式

1.
$$
sin^4x=\frac{1}{8}(3-4cos2x+cos4x)
\\cos^4x=\frac{1}{8}(3+4cos2x+cos4x)\\
arctan2x=\frac{arctanx}{2-2x^2}(了解)\\
$$

## 极限存在的三个原理

1. 夹逼
2. 柯西
3. 单调有界
4. 压缩映照原理
   - 需要验证
     1. fx的值域是fx的子集
     2. 还需要证明|f'x|$\le$C<1





如果说只告诉了你f(x)在x=0处连续，
$$
\lim\limits_{x\to0}\frac{x^2(f(x)+1)}{x-sinx}=2
$$
求f'(0)=?

f'(0)这个问题关键就是要证明他的存在性求或许并不难求

方法一：
$$
\lim\limits_{x\to0}\frac{6(f(x)+1)}{x+o(x)}=2
\\
\lim\limits_{x\to0}\frac{(f(x)+1)}{x+o(x)}=\frac{1}{3}\\
f(x)=\frac{1}{3}x+o(x)-1\\
\lim\limits_{x\to0}f'(x)=\frac{1}{3}
$$
方法二：
$$
f'(0)=\lim\limits_{x\to0}\frac{f(x)-f(0)}{x-0}=\lim\limits_{x\to0}\frac{(f(x)+1)}{x}=\lim\limits_{x\to0}\frac{f(x)-f(0)}{\frac{1}{6}x+o(x)}*\frac{\frac{1}{6}x+o(x)}{x}=\frac{1}{3}
$$



## 导数的应用之画图象题目的解法

1. 首先，二阶导大于零，上凹，二阶导小于零，下凹
2. 上凹就是函数在切线的上方，下凹就是函数在切线的下方
3. 渐近线别忘记求解水平的和竖直的
4. 斜的渐近线的b求解的时候千万别忘记0$\infin$的极限不是0偶
5. 如果说某一个点的导数等于零但是这个点不存在，那么我们在写极值点的时候，不写
6. 在书写<span style="color:red">单调区间</span>和<span style="color:red">凹向区间</span>的时候我们应该是把无定义的区间排除掉
7. 函数的图像我们要一段一段的画出来不同的单调区间不同的凹凸性
8. 函数的极值嫌疑点是函数的导数不存在的点和函数的导数为0的点
9. 函数的嫌疑拐点是函数的二阶导数不存在的点和二阶导数为0的点
10. 嫌疑点的左右两边如果符号不相同就是真的，反之就是假的
11. 别拉题

## 关于洛必达

如果洛出来是无穷或者其他的极限不存在的情况，我们视为不可进行洛必达



---

# 期末复习

## 	专项1不定积分

###       1. 换元法

- 三角换元

- for example：

  - $$
    sinx,cosx出现奇数次方，往dx里面拿一个
    \\
    sinx 和cosx 在分母出现奇数次方上下同时乘以sin或者cos\\
    \int {\frac{dx}{sin(2x)+2sinx}}=\int {\frac{sinxdx}{2sin^2x(1+cosx)}}=\int {\frac{dcosx}{2(1-cos^2x)(1+cosx)}}\\
    \int \frac{1}{sin^3x}dx\\
    从经验上来讲直接用三角万能公式是比凑齐次式更slow一些
    $$
    
  - $$
    出现\sqrt{1+x^2}换成tan m\\出现\sqrt{1-x^2}换成cosx或者sinx 都可以\\
    万能公式：\\sinx=\frac{2tan(\frac{x}{2})}{1+tan^2(\frac{x}{2})}\\
    cosx=\frac{1-tan^2(\frac{x}{2})}{1+tan^2(\frac{x}{2})}\\
    tanx=\frac{2tan(\frac{x}{2})}{1-tan^2(\frac{x}{2})}\\
    d2arctanm=\frac{2}{1+m^2}\\
    剩下的有理分式计算就好啦\\
    (tan^2x+1)dx=\frac{1}{cos^2x}dx=d(tanx)\\
    齐次式\int{\frac{1}{3sin^2x+4cos^2x}}dx\\
    \int  \frac{sin^6x}{cos^{10}x}dx=\int \frac{sin^6x(sin^2x+cos^2x)}{cos^8x}dtanx=\\
    \int (tan^8x+tan^6x)dtanx\\
    \\
    凑齐次式的时候要分母的次数减可以往d里面拿\\分子次数加可以使用(sin^2x+cos^2x=1)将次数变大
    \\\int \frac{sin^3x}{sin^3x+cos^3x}dx\\
    =\int\frac{tan^3x}{tan^3x+1}dx
    \\
    然后将tanx=t\\
    \int \frac{t^3}{1+t^3}\frac{1}{1+t^2}dt
    这样就可以进行有理函数的积分\\
    \int \frac{(xcosx)}{sin^3x}dx=\int x d (sinx)^{-2}
    然后分部积分+齐次式
    \\基本的等价变换：\frac{1}{sin^2x}=(-cotx)'
    \\ln(cosx)'=-\frac{sinx}{cosx}
    \\ln(tan\frac{x}{2})'=\frac{1}{2cos^2\frac{x}{2}tan\frac{x}{2}}=\frac{1}{sinx}\\
    cos的导数是-sinx\\
    $$
    
    
    $$
    \int\frac{1}{\cos^2x}dx=\tan x\\
    \int\frac{1}{\sin^2x}dx=-\cot x\\
    \int\frac{1}{\cot x}dx=\ln(\sin x)+C\\
    \int{\tan x}dx=-\ln (\cos x)+C\\
    \int\frac{1-\ln x}{(x-\ln x)^2}dx=\int d \frac {x}{x-\ln x}\\
    \int\frac{1}{sinx}=ln|tan\frac{x}{2}|+C\\
    \int\frac{1}{cosx}=ln|tanx+secx|+C\\
    \lim\limits_{x\to+\infin}\frac{\int_0^x(arctant)^2dt}{\sqrt{1+x^2}}别想多，简简单单洛必达就好啦
    $$
    
    
    
  - [那道题](#一道比较综合的三角积分题)
    
  - 根号换元
    
  - 欧拉代换
  
  - 分式换元
  
  - 整体换元
    $$
    \sqrt{ax^2+bx+c}=zx\pm\sqrt{c}(c\ge{0})欧拉代换1
    $$
  
  - $$
    \sqrt{ax^2+bx+c}=\sqrt{a}x\pm{z}(a\ge{0})欧拉代换2
    $$
  
  - $$
    \sqrt{a(x-x1)(x-x2)}=t(x-x1)欧拉代换3
    $$
  
  - $$
    \sqrt[n]{ax+b}=t\\x=\frac{t^n-b}{a}\\
    \sqrt[n]\frac{ax+b}{cx+d}=t\\x=\frac{b-dt^n}{ct^n-a}
    \\整体换元
    $$
    
  - $$
    新技巧\int{x^nlnx}dx可以不用分部积分
    \\
    \frac{1}{(n+1)^2}\int{lnx^{n+1}}dx^{n+1}
    $$
  
  - $$
    \int\tan^6xsec^4xdx=\int tan^6x(tan^2x+1)dtanx
    \\
    =\int tan^8x+tan^6xdtanx
    \\
    =\frac{1}{9}tan^9x+\frac{1}{7}tan^7x+C
    $$
    
  - $$
    \int \frac{sinx}{x}dx和\int \frac{x}{sinx}dx记住不可积分，积不出来
    $$
    
  - $$
    \int e^{x^2}dx积不出来\\
    \int e^xarctanx积不出来
    $$
    
  - $$
    \int \frac{sin^4x}{1+cosx}dx可以进行三角换元直接换元进行求解\\
    另外的一种方法在别的地方
    $$
    
  - 三角换元和欧拉换元的区别
  
    - 当出现
  
    - $$
      \sqrt{a^2-b^2x^2}
      $$
  
    - 用三角换元
  
    - 当出现了一次项
  
    - $$
      \sqrt{c+bx+ax^2}
      $$
  
    - 两个人都可以使用
  
    - **三角换元先配方（而且要求分母不能有其他的东西）**
  
    - **分母如果有x*或者x+这一类的只能用欧拉代换**
  
  - 块的选择
  
    - $$
      \int \frac{1+x}{x(1+xe^x)}dx=\\
      \int \frac{e^x(1+x)}{xe^x(1+xe^x)}dx
      $$
  
    - $$
      (2arctan(\sqrt{x}))'=\frac{1}{\sqrt{x}(1+x)}
      $$
  
    - $$
      ln(ln(x))'=\frac{1}{xlnx}
      $$
  
    - $$
      ln(ln(ln(x)))'=\frac{1}{xlnxln(lnx)}
      $$
  
    - $$
      \int \frac{xe^x}{\sqrt{e^x-1}}dx\\(\frac{e^x}{2\sqrt{e^x-1}})
      这一部分作为整体\rightarrow\sqrt{e^x-1}
      $$
  
    - $$
      \int \frac{2lnx+1}{x^3(ln^2x)}dx(上下同时乘以x)
      $$
  
  - 反三角换元和块换元的选择
  
    - $$
      \int\frac{1+x}{x(1+xe^x)}dx=\int\frac{(1+x)e^x}{xe^x(1+xe^x)}dx
      为什么想到这\\
      (xe^x)'=e^x(x+1)
      $$
  
    - $$
      \int \frac{arcsin{\sqrt{x}}}{\sqrt{1-x}}dx
      \\
      \int \frac{arctan{\sqrt{x}}}{\sqrt{1+x}}dx
      \\
      使用反三角换元
      \\
      \int \frac{arctan{\sqrt{x}}}{\sqrt{x}{{(1+x)}}}dx
      使用块代换
      $$
    
    - 一种方法解决分母根号
  
      - $$
        \int {\frac{1}{\sqrt[3]{{(x-1)}^4{(x+1)^2}}}}dx=\int {\frac{1}{\sqrt[3]{\frac{x-1}{x+1}}(x-1)(x+1)}}dx
        $$
    
    - #### 一道比较综合的三角积分题
  
      - $$
        \int \frac{sin^4x}{1+cosx}dx
        \\
        想法一tan(\frac{x}{2})=\frac{sinx}{1+cosx}
        之后用万能公式换到底
        $$
    
      - $$
        想法二\int \frac{-(sinx*(1-cos^2x))dcosx}{1+cosx}=\\\int -sinx*(1-cosx)dcosx=\\
        \int -\sqrt{1-cos^2x}{(1-cosx)}dcosx
        也就是说\\
        \int-\sqrt{1-t^2}(1-t)dt=\\
        \int -\sqrt{1+t}\sqrt{1-t}(1-t)dt=\\
        \int (t^2-1)\sqrt{(\frac{1-t}{1+t})}\\
        之后对于\sqrt\frac{1-t}{1+t}进行换元
        $$
    
        

###     2.分部积分

举几个常见的例子

- $$
  \int{arcsinx}dx
  $$

- $$
  \int{arctanx}dx
  $$

- $$
  \int{lnx}dx
  $$

- $$
  \int e^xarctane^xdx
  $$

- 遇见x和sinx/arctanx/lnx这种的我们需要先将谁放到d后面呢？

- xsinx这种我们需要将sinx放到d后面

- arctanx，和lnx这种我们求完导数之后我们会更好的进行积分的我们就需要先将x^n^先一步放到d的后面

- 别忘了表格法

- 上导下积，先正后负

- 凡是遇到 ln 这种的不管后面有多么的复杂，你都得用分部积分，把除了ln以外的拿到d后面去

- 证明函数不可积，基本上能遇见的都是函数在某个区间上无界

  - *了解内容:上确界减下确界\*$\delta$x的极限是0*
  - 否则不可积分


###      3.分步积分

1. 我们经常忘记的一种分子上和分母好像并没有啥关系，但是分别单独进行积分可能就可以解开了

2. $$
   \int {\frac{x^2}{x^2+1}}*arctanx dx
   $$

   对于这道题，我们很容易想到$ {\frac{1}{x^2+1}}$和arctanx之间的关系但是多了一个x^2^就比较难办所以我决定变化成为$1-{\frac{1}{x^2+1}} $对于前半部分我们就可以纯粹的进行分部积分但是对于后半部分我们就先换元再进行积分就可以解决这个问题

3. 



###      4.大模型，小结论

- $$
  \int {\frac{\alpha+\beta{x^2}}{1+\lambda{x^2}+x^4}}dx
  \\
  \alpha+\beta{x^2} = a(1+x^2)+b(1-x^2)
  \\
  a+b = \alpha
  \\
  a-b = \beta
  \\
  a=\frac{\alpha+\beta}{2}
  \\
  b=\frac{\alpha-\beta}{2}
  \\
  \int \frac{1+x^2}{1+\lambda{x^2}+x^4}dx \\ 
  =\int\frac{\frac{1}{x^2}+1}{x^2+\frac{1}{x^2}+\lambda}dx\\
  =\int\frac{d(x-\frac{1}{x})}{(x-\frac{1}{x})^2+\lambda+2} \\
  =\int\frac{dm}{m^2+(\lambda+2)}\\
  =\frac{1}{\sqrt{\lambda+2}}\arctan (\frac{m}{\sqrt{\lambda+2}})+C\\
  =\frac{1}{\sqrt{\lambda+2}}\arctan (\frac{x-\frac{1}{x}}{\sqrt{\lambda+2}})+C\\
  
  \int \frac{1-x^2}{1+\lambda{x^2}+x^4}dx \\
  =\int\frac{\frac{1}{x^2}-1}{x^2+\frac{1}{x^2}+\lambda}dx\\
  =\int\frac{-d(x+\frac{1}{x})}{(x+\frac{1}{x})^2+\lambda-2} \\
  =-\int\frac{dm}{m^2+(\lambda-2)}\\
  =-\frac{1}{\sqrt{\lambda-2}}\arctan (\frac{m}{\sqrt{\lambda-2}})+C\\
  =-\frac{1}{\sqrt{\lambda-2}}\arctan (\frac{x+\frac{1}{x}}{\sqrt{\lambda-2}})+C\\
  原式=\frac{\alpha+\beta}{2}\frac{1}{\sqrt{\lambda+2}}\arctan (\frac{x-\frac{1}{x}}{\sqrt{\lambda+2}})-\frac{\alpha-\beta}{2}\frac{1}{\sqrt{\lambda-2}}\arctan (\frac{x+\frac{1}{x}}{\sqrt{\lambda-2}})+C\\
  $$
  
  
  
- 倒代换基本的模型是（一般用于分母的系数比分子要高，但有局限性，剩下的考虑有理函数的积分）

- $$
  \int{\frac{1}{x\sqrt{x^2+\lambda}}}dx
  \\
  x=\frac{1}{t}
  \\
  原式=\int \frac{t}{\sqrt{\frac{1}{t^2}+\lambda}}d\frac{1}{t}
  \\
  =\int -\frac{t^2}{\sqrt{(1+\lambda t^2)}}\frac{1}{t^2}dt
  \\
  =\int - \frac{1}{\sqrt{(1+\lambda t^2)}}dt
  \\
  t=\sqrt{\frac{1}{\lambda}}tanm
  \\原式=\sqrt{\frac{1}{\lambda}}\int-cosmdtanm
  \\
  =\sqrt{\frac{1}{\lambda}}\int-\frac{1}{cosm}dm
  \\
  =-\sqrt{\frac{1}{\lambda}}ln(secm+tanm)+C	
  \\
  或者
  \\
  \int{\frac{1}{x^2\sqrt{x^2+\lambda}}}dx
  \\
  这个同理
  \\
  另外还要注意一下如果发现分母的次数大的离谱我们可以考虑倒带换
  \\
  倒代换完事之后上面的次数就会比下面的大或者相等
  \\
  然后就可以进行下一步了-->那就是拆开
  \\
  $$
  
- 要记住的一些小结论

- $$
  ln(x+\sqrt{x^2+a})'=\frac{1}{\sqrt{x^2+a}}
  $$

- $$
  ln(x+\sqrt{x^2-a})'=\frac{1}{\sqrt{x^2-a}}
  $$

- $$
  \int\frac{1}{x^2-a^2}dx=\frac{1}{2a}ln|\frac{x-a}{x+a}|+C
  $$

- $$
  \int \frac{1}{x^2+a^2}dx=\frac{1}{a}*arctan\frac{x}{a}+C
  $$

- $$
  \int \frac{1}{\sqrt{a^2-x^2}}dx=arcsin\frac{x}{a}+C
  $$

- $$
  arctanx+arctan\frac{1}{x}=\frac{\pi}{2}\\
  arcsinx+arccosx=\frac{\pi}{2}
  $$

- $$
  \int tanxdx=ln|cosx|+C
  \\如果正好积分到\frac{\pi}{2}\\ln0=-\infin
  $$
  
- $$
  \int \frac{1}{sinx}dx=ln|tan\frac{x}{2}|+C	\\
  \int \frac{1}{cosx}dx = ln|secx+tanx| + C
  $$
  
  

###       5. 组合积分法

- I=$\int{\frac{asinx}{\alpha{sinx}+\beta cosx}}$
- J=$\int{\frac{acos}{\alpha{sinx}+\beta cosx}}$
- 目的是为了凑出原式子分母或者分母的导数$\rightarrow$本质是变成可积函数

###       6.有理函数的积分

- 拆分母
- 能用快速算法的先用快速算法
- 挡一项，带入值
- 一般情况下配凑是更快的
- $$
  \int \frac{1}{1+x^6}dx 可以看作是1+(x^2)^3\\
  \int \frac{1}{1+x^4}dx可以使用待定系数法对1+x^4进行拆分\\
  这个题可以使用大模型配凑出来(当作第三种思路)
  $$
- 或者使用虚根
- $$
  e^{i\pi}+1=0\\
  x^4+1=0\\
  x^4=e^{i\pi}\\
  x=e^{\frac{i\pi}{4}}\\
  $$
- 方法一：带入具体的数值之后解方程
- 方法二：把最大的分母乘一下

### 																																														**7.一些有关的定义**

1. 如果说一个函数是偶函数，那么他的导函数是奇函数
2. 但是他的原函数并不一定是奇函数因为还有一个C就是那个要加上的常数



### 8.利用递推公式求解积分（具体的例子见下面的定积分例题）

[建立递推公式](#4.建立递推公式)

---

##   专项2 定积分

### 1. 点火公式：

$$
\int _{0}^{\frac{\pi}{2}}sin^nxdx=\int _{0}^{\frac{\pi}{2}}cos^nxdx
答案就是\\
\frac{(n-1)(n-3)(n-5）……}{n(n-2)(n-4)……}如果最后到1了那就点火成功*\pi/2
\\
否则就不*\\
一个特殊的小结论：
\int _0^\pi x*f(sinx)dx=\frac{\pi}{2}\int _0^\pi f(sinx)dx
=\pi\int _0^\frac{\pi}{2}f(sinx)dx
\\
\sin^mx\cos^nx这种的只要m,n是有一个以上是偶数那我们就可以使用点火公式\\
cos^{2k+1}x在(0,2\pi)上进行积分结果为0
$$

### 2. 奇偶函数对称区间

1. 对于奇函数，对称区间就是0

2. 例如

3. $$
   \int _{-\frac{\pi}{2}}^{\frac{\pi}{2}}x^4sinxdx=0
   $$

4. $$
   \int _{-1}^{1}\frac{xcosx}{1+x^2}dx=0
   $$

5. $$
   \int _{-\frac{\pi}{2}}^{\frac{\pi}{2}}\frac{xsin^2x}{(1+cos^2x)^2}dx=0
   $$

6. 对于偶函数，对称区间就是二倍

7. 例如

8. $$
   \int_{-a}^{a}f(x)dx=2\int_{0}^{a}f(x)dx(其中f(-x)=f(x))
   $$

### 3.分部积分（正常积分积不出来的可以使用分部积分法，有三道例题）

1. $$
   \int _0^{\frac{1}{2}}xln{\frac{x+1}{1-x}}dx
   $$

2. $$
   \int _0^1ln(x+\sqrt{x^2+1})dx\\
   =xln(x+\sqrt{x^2+1})|_0^1-\int _0^1xdln(x+\sqrt{x^2+1})\\
   =ln(1+\sqrt{2})-\frac{1}{2}\int_0^1\frac{2x}{\sqrt{x^2+1}}dx\\
   =ln(1+\sqrt{2})-\int_0^1\frac{1}{2\sqrt{x^2+1}}d(x^2+1)\\
   =ln(1+\sqrt{2})-\sqrt{x^2+1}|_0^1\\
   =ln(1+\sqrt{2})-\sqrt{2}+1
   $$
   
3. $$
   \int _0^{\frac{\pi}{4}}\frac{x}{1+cos2x}dx
   $$

4. 以上是三个分部积分的例子





### 4.建立递推公式

**常见的形式就是发现会有m，n这一类的东西**

1. $$
   \int _0^1x^m(lnx)^ndx=-\frac{n}{m+1}\int x^m(lnx)^{n-1}dx=……递推到1结束\\对\int _0^1x^mdx进行积分就好了
   $$

2. $$
   \int x^{m-1}(1-x)^{n-1}dx=\frac{1}{m}\int (1-x)^{n-1}dx^m\\=\frac{1}{m}(x^m(1-x)^{n-1}-\int x^m d(1-x)^{n-1})\\=\int x^m(1-x)^{n-2}dx+[\frac{1}{m}x^m(1-x)^{n-1}]_0^1
   $$

3. $$
   \int _0^1tan^{2n}xdx=\int  _0^1tan^{2n-2}xtan^2xdx=\int  _0^1tan^{2n-2}x(tan^2x+1-1)dx\\=\int _0^1 tan^{2n-2}xdtanx-\int _0^1 tan^{2n-2}xdx
   $$

4. 定积分求导上限带入并将上限函数求导求极限使用洛必达的话原理是一样的

5. 定积分上下限颠倒前面加-



### 5.变换区间方法（区间再现，倒代换）

$$
\int _{a}^{b}f(x)dx=\int _a^bf(a+b-x)dx=\frac{1}{2}\int _{a}^{b}{(f(x)+f(a+b-x))}dx
\\
\int _a^b\frac{f(x)}{f(a+b-x)+f(x)}dx=\frac{b-a}{2}\\
\int_0^2 \frac{\sqrt{2+x}}{\sqrt{2+x}+\sqrt{4-x}}dx=\int_0^2 \frac{\sqrt{4-x}}{\sqrt{2+x}+\sqrt{4-t}}dx=\\ \frac{1}{2}(\int_0^2 \frac{\sqrt{2+x}}{\sqrt{2+x}+\sqrt{4-x}}dx+\int_0^2 \frac{\sqrt{4-x}}{\sqrt{2+x}+\sqrt{4-t}}dx)
$$

倒代换和上面的不定积分差不多是一样的

例：
$$
f(x)+f(-x)\equiv A\\
g(x)=g(-x)\\
\int _{-a}^{a}f(x)g(x)dx=A\int_{0}^{a}g(x)dx
$$


### **6.定积分求导积分变限函数**

定积分求极限的时候经常会使用洛必达，而我经常容易犯的错误就是光顾着上面定积分求导了，就忘记下面的另外的一部分也需要求导

积分上限函数的另外一种形式

$$
x\int_0^1f(xt)dt=\int _0^1f(xt)d(xt)=\int _0^xf(m)dm
$$

$$
\frac{d\int _0^{x^2}tf(x^2-t^2)dt}{dx}=(\int _0^{x^2}f(x^2-t^2)d(\frac{1}{2}t^2))'=(\frac{1}{2}\int _0^{x^2}f(x^2-m)dm)'\\=(\frac{1}{2}\int _0^{x^2}f(m)dm)'=\frac{1}{2}f(x^2)*2x\\总之记住在求积分的导数的时候里面不能带x的而消除里面的x的方法就是通过区间再现公式和换元\\
一般换元是要凑到含有积分上限的形式\\
极限和定积分的综合题
$$

在这里谈一谈我对于为什么里面不能含有x的理解：

首先在上限的x是变量的x在里面的x是一个常数所以两者不能混为一谈

### 7.其他方法

定积分还可以使用几何意义，比如说是一个半圆的面积，一个四分之一圆的面积这种

### 8.<span style="color:purple">***了解内容***</span>

1. <span style="color:purple">曲率和曲率半径</span>

   - 求解曲率（期末不会考）

     1. 在直角坐标系下：
        $$
        K=\frac{f''(x)}{({1+f'^2(x)})^\frac{3}{2}}
        $$

     2. R=$\frac{1}{K}$;

     3. 在极坐标系下：

     4. $$
        K=\frac{\rho^2(\theta)+2\rho'^2(\theta)-\rho*\rho' }{({\rho(\theta)^2+\rho'^2(\theta)})^\frac{3}{2}}
        $$

     5. R=$\frac{1}{K}$;



### 9.题型总结

1. 递归类题型

   1. 给你一个等式，等式中有着自己在某个区间上的积分，设他为A对原函数两边积分

   2. 另外如果你发现里面的积分多出来一个G(x)那你就在外面*G(x)再进行积分

   3. $$
      f(x)=x+\int _0^af(x)dx\\
      f(x)=x+2\int _0^{\frac{\pi}{2}}f(x)cosxdx
      $$

2. 积分中套着一个积分

   1. 使用分部积分法，要的就是让内部积分进行求导

3. 变限积分求导的时候，内部还有上下限

   1. 能拿出去的先拿出去
   2. 拿不出去的，整体换元，别忘记改变上下限的大小

4. 积分和极限的综合题

   1. 对于积分的上下限相等的，进行洛必达法则+无穷小等价替换
   2. 对于定积分的概念--黎曼积分--求解极限可以使用级数的思想，方法在下面

5. 两条曲线所围成的面积的总和

   1. 构造F(x)=f(x)-g(x)
   2. 解出交点x1 x2
   3. 在(x1,x2)进行定积分
   4. 这是在平面直角坐标系当中
   5. 用x还是用y都是相同的

6. 在极坐标系当中求某个曲线的周长或者面积

   1. $$
      \frac{1}{2}\int _\alpha^\beta \rho^2(\theta) d\theta
      $$

   2. 当成扇形直接求$\rho(\theta)*\rho(\theta)*\theta$

7. 平移体体积求解

   1. 平移体的求解要求知道横截面积的表达式，注意一定是关于垂直于平移体的那条线的表达式
   2. 然后对于S关于长度求定积分

8. 旋转体的体积求解

   1. 关于x轴对称的体积
      $$
      \int_{a}^{b} \pi f^2(x)dx
      $$

   2. 关于y轴对称的体积

      

   $$
   \int _a^b 2\pi xf(x)dx
   $$

   3. <span style="color:purple">**关于一条斜线的旋转体积分**</span>
      1. 抽象出来一个竖直的，一个水平的，
      2. 两个相互垂直，在一个方向上，求解旋转一圈的面积
      3. 对于面积进行积分
      4. 可能会涉及到坐标系的转换，利用点到直线的距离公式，将旧的转换成新的

9. 弧长的求解

   1. 直角坐标系当中的
      $$
      \int_a^b \sqrt{1+f'^2(x)}dx
      $$

   2. 极坐标系下的求解
      $$
      \int _a^b\sqrt{\rho^2(\theta)+\rho'^2(\theta)}d\theta
      $$

   3. 计算弧长的关键点在于不论斜率是正数还是负数，算的是类似于路程的东西，
      所以得加上绝对值，要不然你有可能算出来的长度等于0

10. 求解旋转曲面的面积

$$
\int_a^b 2\pi f(x)\sqrt{(1+f'^2(x))}dx
$$

11. 求解旋转曲面的面积（极坐标版）
    $$
    \int 2\pi\rho sin\theta {(\rho^2(\theta)+\rho'^2(\theta))}d\theta
    $$
    
12. 参数方程的求解

1. ==参数方程的易错点就在于参数方程后面的dx还得再求一次==
2. 极坐标的理解：

   1. 极坐标下我们可以从0-2$\pi$进行旋转，找到几个定点的位置

13. 用定积分的定义（黎曼积分）进行求解极限

- 和定积分的联系
  1. $\frac{1}{n}$~dx
  2. $\frac{k}{n}$~x
  3. x的范围就是定积分的上下限

（定积分与求解极限的第二处联动）

14. 给你一个积分的值，让你求另外的一个积分，思路就是转变成为给出的积分
    （现在遇到的题的方法：分部积分）
15. 求解开根号的定积分的时候：

16. 出来的时候要带绝对值
17. 绝对值要在$\int$去掉之前进行讨论分成几个区间

18. 计算极限

    1. $$
       \lim\limits_{x\to0}\frac{(\int_0^xe^{t^2}dt)^2}{\int_0^xte^{2t^2}dt}
       $$

    2. 关键就是下面别忘了求导别只求一个

    3. 求一次导数就是会消去一个∫符号

    








## 专项3 积分中值定理

1. $$
   \int _a^bf(x)g(x)dx=f(\xi)\int _a^bg(x)dx\\注意事项：谁不变号谁就留在里面
   \\
   证明：f(\xi)=\frac{\int _a^bf(x)g(x)dx}{\int _a^bg(x)dx}
   \\
   \frac{m\int _a^bg(x)dx}{\int _a^bg(x)dx}<=\frac{\int _a^bf(x)g(x)dx}{\int _a^bg(x)dx}<=\frac{M\int _a^bg(x)dx}{\int _a^bg(x)dx}\\
   m<=\frac{\int _a^bf(x)g(x)}{\int _a^bg(x)}<=M
   \\
   根据介值定理，\\\exist a <\xi<b \\s.t.\\m<f(\xi)<M
   $$

2. 构造辅助函数

   1. $$
      \exist\xi\in (a,b)使得g(\xi)\int _a^{\xi}f(x)dx=f(\xi)\int_\xi^bg(x)dx
      这件事情如何求证
      \\
      首先构造F(x)=g(x)\int _a^xf(t)dt-f(x)\int_x^bg(t)dt\\
      发现带进去无效证明不出来\\
      改变思路--F'(x)=g(x)\int _a^xf(t)dt+f(x)\int_b^xg(t)dt\\
      ==>F(x)=(\int_a^xf(t)dt\int_b^x g(t)dt)
      真正的罗尔思路
      \\
      如果F'(x)还不行
      就用F''(x)还有一种就是泰勒展开式具体的情况还没有遇到\\
      $$

   2. $$
      \int _a^bf(x)dx=f(\xi)(b-a)
      $$

3. 使用分部积分来进行积分中值定理的证明

   1. f(0)=0 |f'(x)|<M |$\int _0^af(x)dx$|$\le$$\frac{a^2}{2}M$

   2. $$
      \int _0^af(x)dx=\int _0^af(x)d(x-a)=f(x)(x-a)
      |_0^a-\int _0^a(x-a)df(x)
      \\
      这个方法比较巧妙
      $$

4. 南京大学的课后习题当中不等式的证明都是通过设主元的方法进行的





### 专题 使用积分中值定理进行不等式的证明

1. <span style="color:purple">**方法一：进行变限积分的构造**</span>

   1. 将一个积分不等式的积分上限转换成为函数的自变量，并且注意要将原来函数里面的X转变成t这样比较好看一些

   2. 之后就进行正常的求导操作即可，需要格外注意的是有的时候出现了两个函数相乘的现象，别忘记是前导*后+后导\*前

   3. 很重要的两个不等式

      1. 柯西不等式

      2. $$
         \int _a^bf^2(x)dx*\int _a^bg^2(x)dx\ge(\int _a^bf(x)g(x)dx)^2
         $$

      3. 切比雪夫不等式

      4. $$
         当f(x)和g(x)的单调性相同的时候：\\
         
         (b-a)\int _a^bf(x)g(x)dx\ge\int _a^bf(x)dx\int_a^bg(x)dx
         \\
         如果f(x)和g(x)单调性不同：
         \\
         (b-a)\int _a^bf(x)g(x)dx\le\int _a^bf(x)dx\int_a^bg(x)dx
         $$

      5. 这两个不等式都可以使用构造变上限积分

   4. 在这种方法中应该熟练的掌握积分的形式和f(x)之间的转换：弄清楚对谁积分，如果积分变量是t那么只含有x的量就可以拿到外面去

   5. 利用单调性不等式的积分进行证明（f(a)-f(b))(a-b)$\ge$0||（f(a)-f(b))(a-b)$\le$0

      - 左右同时进行积分即可
      - 有好多积分的不等式都是从一个等式或者不等式两边同时进行积分得到的

   6. 不只限于这种方法的注意事项：如果出现了f(a)和f(b)都是0，那么我们有一种思想就是取中点

2. **<span style="color:purple">方法二：拉格朗日中值定理，柯西中值定理，积分中值定理</span>**

   1. 建立f(x)和f'(x)之间的桥梁一共有3种：
      1. 拉格朗日中值定理f(x)-f(a)=f'($\xi$)(x-a)
      2. 分部积分（df(x))
      3. 进行积分的变化 N-L的逆用  f(x)-f(a)=$\int_a^xf'(t)dt$
      4. 注意一下如果发现不  了某些f(x)=0你可以随意的$\pm$如果没有可以加一项减一项
   2. 什么时候我们使用积分不等式？$\rightarrow$我们发现题干告诉了f(x)的单调性，积分区间要相同啊
      - 注意区间要错开，区间不同，最终的用单调性判断出来即可(本质上来讲积分中值定理就是要派出一个不可积分的项)

3. <span style="color:purple">**方法三：泰勒中值定理（本质是一个拉格朗日余项的使用）**</span>

   1. 就是纯粹的展开：选好被展开点和展开点：选择的方法就是根据不等式的不等号的方向

   2. 对于这种题目一般都是先计算f"(x)，一般情况下，他们是符号不变化的，然后根据f"(x)的正负我们可以进行放缩，对于原函数的放缩，因为f"(x)*(X-X0)^2^/2!其他的项肯定是正的所以可以根据这个得到一个不等式

   3. 之后对于两边同时积分就可以啦

   4. ##### 一个小结论--琴生不等式：[步骤](#<span style="color:pink">泰勒公式构造琴生不等式</span>[琴生不等式](#一个小结论--琴生不等式))

   5. 提醒一下(x-x~0~)在对称区间上面积完之后等于0

   6. $$
      when \quad f''(x)\ge0\\
      \frac{\int _a^bf(g(x))dx}{b-a}\ge f(\frac{\int_a^bg(x)}{b-a})\\
      when \quad f''(x)\le0\\
      \frac{\int _a^bf(g(x))dx}{b-a}\le f(\frac{\int_a^bg(x)}{b-a})\\
      $$

4. ### <span style="color:purple">方法四：柯西不等式</span>

   1. 现总结一下拆f(x)g(x)的方法：
      - 看着等号的左边进行拆分
      - 拆成平方的形式
   2. 出现积分的平方和平方的积分我们会想到使用柯西不等式
   3. 另外出现f^2^(x)和f'^2^(x)的情况我们是有定式的
      1. 写出f(a)-f(b)=$\int _a^bf'(x)dx$
      2. 第二步两边同时平方
      3. 进行柯西不等式的放缩
      4. 之后进行积分上下限的放缩变成定长区间
      5. 最后一步进行左右同时积分即可得到证明的式子

5. <span style="color:darkred">**方法五：构造双重积分**</span>

   1. 出现了两个积分相乘的形式
      1. $\int_a^bf(x)dx\int_a^bg(x)dx=\int _a^bf(x)dx\int_a^bg(y)dy=\oiint f(x)g(y)dxdy $
      2. 应该注意的是如果x和y他们的范围是关于y=x对称的那么原式等于$\oiint \frac{1}{2}[f(y)g(x)+f(x)g(y)]dxdy$









1. 分部积分的技巧，我们希望分部积分分出来的前面是0，那我们可以调整d后面的部分d(x-1)

2. 求证：
   $$
   \int_0^1  f(x)dx = \frac{1}{2}\int_0^1 x(x-1)f''(x)dx\\
   使用分部积分从右边往左还原或者从左边往右都可以
   $$

3. 存在$\xi$使得某个关于$\xi$的等式是成立的

   1. 中值定理

   2. 罗尔定理

   3. 拉氏定理

   4. 积分中值定理

   5. 泰勒展开式(带有拉格朗日余项的)

   6. 例子: ![例题](D:\picture\微信截图_20231228163342.png)

      1. 这个题就构造

      2. $$
         F(x) = xe^{1-x}f(x)
         $$

      3. 然后我们会发现F(1) = f(1)

      4. 由积分中值定理我们可以得到f(1) = F($\xi$)

      5. 我们由F(1) = F($\xi$)

      6. 从而存在$\alpha$使得所证明的式子是成立的

   7. #### Taylor

   8. $$
      f'(a) = f'(b) = 0\\
      \exist\xi\,\,\, s.t. \int _a^bf(x) dx = (b-a)\frac{f(a) + f(b)}{2}+\frac{1}{6}(b-a)^3 f''(\xi)
      $$

   9. F(x) = F(a) + F'(a)(x-a) + $\frac{1}{2}F''(a)(x-a)^2$+$\frac{1}{6}F'''(\xi)(x-a)^3$

   10. F(b) = $\int_a^bf(t)dt = F'(a)(b-a)+\frac{1}{6}F'''(\xi)(b-a)^3)$

   11. 我们可以直接泰勒展开式，之后积分，因为f''（$\xi$)后面的东西是一样的所以我们是可以提取出来后面的那部分之后使用介值定理（就是等效于把他提到外面来）

   12. [taylor](#<span style="color:pink">使用泰勒展开式+积分运算得到表达式</span>)

4. 零点个数

   1. $$
      \int _0^xf(t)dt = \int _0^xg(t)dt的零点个数
      \\移项
      \\求导\\
      看是否有单调性\\
      $$

5. 不等式

   1. 有一种思想就是使用分部积分+放缩的方法把原来的式子在不等式的另一边再现这样就得到了关于原始的式子的不等式

   2. 第二种是移项之后求导，使用变上限积分的方式

   3. $$
      \int _0^af(x)dx
      \\=\int_0^a f(x)d(x-a) 
      \\=(x-a)f(x)|_0^a-\int_0^a(x-a)f'(x)dx
      \\=\int_0^a(a-x)f'(x)dx
      $$
      
      

## 专项4 广义积分

广义积分和柯西准值的区别：

1. 广义积分是两侧以任意的速度趋近于某一个值，但是柯西准值则是要求两侧是相同的速度
2. 例如 

$$
\int _{-\infin}^{+\infin}xdx对于广义积分来说是不可积分的\\
但是对于柯西准值来说是可以积分的\int_{-\infin}^{-\xi}xdx+\int_{\xi}^{+\infin}xdx=0
$$

3. 综上所述，广义积分是比柯西准则更加的苛刻
4. 了解一个概念之后其他的求广义积分就是把值往里带，多一个求极限的过程



## 专项5 向量代数与空间解析几何





### 定比分点原理

1. $$
   \overrightarrow{PF}=\lambda \overrightarrow{PQ}
   \\P(x1,y1,z1)\\
   Q(x2,y2,z2)\\
   F(\lambda x2+ (1-\lambda)x1,\lambda y2+ (1-\lambda)y1 ,\lambda z2+ (1-\lambda)z1)\\
   $$



### 如何求解角分线的方向向量

$$
|\overrightarrow{AB}|·\overrightarrow{AC}+|\overrightarrow{AC}|·\overrightarrow{AB}=\vec{s}
\\eg..\overrightarrow{AB}=(3,4,12)\,\,\,\,\, \overrightarrow{AC}=(2,3,6)
\\\vec{s}=13(2,3,6)+7(3,4,12)=(47,67,162)\\
重心  \overrightarrow{OA}+\overrightarrow{OB}+\overrightarrow{OC}=\vec{0}
\\\overrightarrow{PO}=\overrightarrow{PA}+\overrightarrow{AO}
\\\overrightarrow{PO}=\overrightarrow{PB}+\overrightarrow{BO}
\\\overrightarrow{PO}=\overrightarrow{PC}+\overrightarrow{CO}
\\
上述三个式子相加得到
\\3\overrightarrow{PO}=\overrightarrow{PA}+\overrightarrow{PB}+\overrightarrow{PC}
\\\vec{a}\times\vec{a}=\vec{0}
\\注意有可能会有两个 \,\,前面有一个\,\,后面有一个
$$


反交换律
$$
\vec{a}\times\vec{b}=-\vec{b}\times\vec{a}
$$
分配律是满足的
$$
(\vec{a}+\vec{b})\times\vec{c}=\vec{a}\times\vec{c}+\vec{b}\times\vec{c}
$$


三个向量共面的条件
$$
\overrightarrow{AB}\,\,\overrightarrow{AC}\,\,\overrightarrow{AD}共面的条件
\\
\overrightarrow{AB}\times\overrightarrow{AC}·\overrightarrow{AD}=0
$$

两条直线相交 两条直线上各自取一个点 如果法向量垂直于两点连线那么说明两条直线相交 

三角形面积别忘记除以1/2

点到直线的距离求解：

​	



1. 如果是直线的一般式，先用$\times$计算出方向向量
2. 然后任取两个点，得到一个向量，之后有这个向量和方向向量在同一个平面上求出来夹角之后乘以sin
    $\theta$得到的就是点到直线的距离
    		1. 具体说明一下：如何求点，取x = 0那么我们就可以得到一个二元一次方程组，分别把y和z求出来
      		2. 注意一下先得到的是cos，最后乘以的是sin0
3. 关于直线束的思想：
    		1. a(A1X+B1Y+C1Z)+b(A2X+B2Y+C2Z)=0表示过某一条直线的所有的平面
          		2. 例如有一道题告诉你了一条直线的一般式方程，然后告诉你一个平面过这条直线，你就可以使用这种方法，注意这里的平面还需要有另外的一个条件才能确定下来











### 所谓空间解析几何就是说研究点线面之间的关系和距离：

1. 点是不是在线上，或者说直线是不是过某一个点？点是不是在某一个平面上的，或者说平面上是不是存在某一个点？

   1. 把这个点的坐标带入到这显得方程当中，如果原来的方程式成立的，那么我们就认为这条直线是过这个点的，这个点在这条直线上的
   2. 点是不是在某一个平面上的，道理相同，依旧是代入即可。

2. 直线是不是在某个平面上？或者说平面是不是过某个直线

   1. 方法一：在这条直线上任意取两个点如果说这两个点全部都在这个平面上，那么不可能是平行，不可能是相交，那就是在这个平面内了
   2. 方法二：看这个平面的法向量是不是和这条直线的方向向量垂直，如果垂直，那就再找一个点在这个平面上就好了

3. 三条直线共面的充要条件：

   - 三者的混合积 = 0
   - $\vec{a}\times\vec{b}·\vec{c} = 0$

4. 点、直线、平面的表达式：

   1. 点(x,y,z)

   2. 直线

      1. 点向式
         $$
         \frac{x-a}{m}=\frac{y-b}{n}=\frac{z-c}{p}
         $$

         2. 一般式
            $$
            A1x+B1y+C1z+D1=0\\
            A2x+B2y+C2z+D2=0
            $$

   3. 平面

      1. $$
         Ax+By+Cz+D=0
         $$

      2. $$
         A(x-x0)+B(y-y0)+C(z-z0)=0
         $$

      3. $$
         \frac{x}{a}+\frac{y}{b}+\frac{z}{c}=1
         $$

5. 由他们的表达式我们能够得到的一些信息

   1. 直线的方向向量(m,n,p)
   2. 平面的法向量(A,B,C)
   3. 平面的法向量($\frac{1}{a},\frac{1}{b},\frac{1}{c}$)

6. 两条直线平行就是m,n,p成比例 但是不重合

7. 两平面平行就是ABC相同但是D不同

8. 点到直线的距离(平行直线之间的距离求解)

   1. 方法：面积法：

   2. $$
      h(d) =\frac{|\,\overrightarrow{a}\times\overrightarrow{s}\,|}{|\vec{s}|}
      $$

9. 

7. 点到平面的距离(其实这点和平行于平面的直线到平面的距离一样的)

- 代公式：

- $$
  \frac{|Ax_0+By_0+Cz_0+D|}{\sqrt{A^2+B^2+C^2}}
  $$

- 

8. 平行平面之间的距离

- 代公式

- $$
  \frac{|D1-D2|}{\sqrt{A^2+B^2+C^2}}
  $$

9. 两条异面直线之间的距离

   1. 引入概念-->公垂线

   2. 和两条直线都有垂直的线段

   3. 长度的求法：

      1. 先使用两个方向向量进行叉乘求出向量$\vec{s}$

      1. 在两条直线上各自取一个点之后这两个点构成的直线在$\vec{s}$上面的投影长度就是公垂线的长度

   4. 公垂线方程的求解：

      1. 使用两次共面条件：

      2. 在公垂线上取一个点设为M（x,y,z）

      3. 在直线L1上取一个点M1（x1，y1，z1）

      4. MM1构成一个新的向量加上公垂线的方向向量和L1的方向向量三者共面

      5. 在直线L2上取一个点（x2，y2，z2）

      6. MM2构成一个新的向量加上公垂线的方向向量和L2的方向向量三者共面

10. 直线的参数方程

    1. 求解一条直线和一个平面的交点的时候
    2. x = $mt+x_0$
    3. y =  $nt + y_0$
    4. z = $pt +  z_0$
    5. 然后带入平面方程里面去最后求出来t
    6. 分别带入上面的式子就可以得到xyz的值

11. 平面束的思想

    1. 过一个直线的任何一个平面都可以写成平面束的形式

    2. $$
       A1x+B1y+C1z+D1=0\\
       A2x+B2y+C2z+D2=0
       $$

    3. 那么过这条直线的平面就是

    4. $$
       A1x+B1y+C1z+D1 + \lambda(A2x+B2y+C2z+D2
       )=0
       $$

12. 直线和直线所成的角度：

    1. 两个方向向量成的角度

13. 平面和直线所成的角度：

    1. 方向向量和法向量的余角

14. 平面和平面所成的角度：

    1. 法向量所成的角度

15. 数学微积分旋转曲面复习

    1. 已知母线和准线怎么求解曲面方程？

       1. 准线是
          $$
          F(x,y,z)=0\\
          G(x,y,z)=0\\
          $$

       2. 母线是
          $$
          方向向量是(l,m,n)\\
          $$

       3. 曲面的方程就是将

       4. | x-->x-lt | y-->y-mt | z-->z-nt |
          | -------- | -------- | -------- |

       5. 在这个过程之后我们需要把这个t消去之后我们得到的就是相应的曲面的方程

    2. 其余的正常列出表达式之后我们直接把方程化简一下就好啦

    3. 绕x,y,z轴旋转的曲面方程

       1. F(x,y) = 0绕x轴旋转 --> F(x,$\pm\sqrt{y^2+z^2}$) = 0
       2. F(x,y) = 0绕y轴旋转 --> F($\pm\sqrt{x^2+z^2}$,y) = 0
       3. F(y,z) = 0绕y轴旋转 --> F(y,$\pm\sqrt{x^2+z^2}$) = 0
       4. F(y,z) = 0绕z轴旋转 --> F($\pm\sqrt{y^2+x^2}$,z) = 0
       5. F(x,z) = 0绕x轴旋转 --> F(x,$\pm\sqrt{y^2+z^2}$) = 0
       6. F(x,z) = 0绕z轴旋转 --> F($\pm\sqrt{y^2+x^2}$,z) = 0
    
    
    
    
    
    
    
    
    
    
    
    
    
    

# 错题总结 plus注意事项：

 1. 方向向量直接抄，但是点要求是后面系数的相反数
 2. 计算距离的时候是绝对值
 3. 计算曲线长度的时候也是绝对值
 4. 渐近线是有斜渐近线、竖直渐近线、水平渐近线（别忘了求后面两个）
 5. 单调区间和凹向区间是要求把无定义的点排除的 
 6. 求极限的时候进行洛必达慢一点，求导的时候把系数写在前面，要不然容易忘记
 7. 求出来的直线方程记得约分
 8. 一个函数让你求它在某一点的导数，但是没有告诉你它是连续的或者可导的，使用差商来求解
    吗一般这种的差商都得需要一点洛必达的成分
 9. 让你求一个东西的单位向量，求完之后别忘了让他的模长为1方法就是除以模长
 10. 求解平分两个向量的角分线方向向量的话一共有两个别求一个就走了
 11. 格外小心当$x\to-\infin$的时候我们要注意看好一开始是正的还是负的
 12. 想好了边界条件能不能取到等于号
 13. f'’(x)> 0 上凹
 14. f''(x)<0 下凹
 15. 求解面积一定要注意先画草图，要不然容易少求
 16. 求极限别忘了还有夹逼的方法 放缩两次 大小相同则极限确定                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
 17. 证明及现实存在的-->你要证明这个点的左极限等于右极限
 18. 常数积分记得要*积分区间
 19. 积分中值定理的更大的意义在于它能够让积分变得可积/好积










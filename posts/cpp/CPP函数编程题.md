# 函数编程题

1. 利用函数模板设计一个求数组元素总和的函数sum，使得下方的main函数可以正确运行。 

















2. 编写一个智能指针类SmartPtr。

1、 实现基于RAII的堆上对象资源的管理，即将一个堆上对象资源封装在一个SmartPtr对象的生命周期内，避免资源泄漏（4分）；

2、 实现SmartPtr类的解引用运算符（*）和箭头运算符（->）的重载，从而使得SmartPtr对象可以像封装在其中的堆上对象的指针一样使用（4分）；

3、 使用模板类编写SmartPtr类，使得它可以封装各种类型的堆上对象资源（2分）。

1. 下列程序输出如下星状图形，请将程序空白部分补充完整：

   \* 

   \* * 

   \* * * 

   \* * * *

   

   int printEachLine(int j){ 

   if (__________) 

   return 1; 

   else{

   ______________________

   ______________________

   return1;

   } 

   }

   int print(int i){

   if (________) 

   return 1; 

   else{

   ____________________

   ____________________

   cout<<endl;

   return1;

   } 

   }

   void main(){ 

   print(4); 

   }

   

   

   

   观察者模式：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

   举个博客订阅的例子，当博主发表新内容的时候，即博客内容发生了改变，那些订阅的读者就会收到通知。博主与读者之间存在种一对多的依赖关系，**博客中可能有多个观察者**（即订阅者），当博客的内容发生变化时，通过notify成员函数通知所有的观察者，告诉他们博客的内容更新了。而观察者通过update成员函数获取博客的内容信息 

   **请根据以下类图和部分类描述，请用****C++****给出相关类的实现（**Blog, BlogCSDN，Observer，ConsoleObserver**）。**

    

    

   类名Blog：

   | 属性        | 描述                           |
   | ----------- | ------------------------------ |
   | m_content   | 博客内容（长度< 1024个字符）   |
   | m_observers | 多个观察者                     |
   | 方法        | 描述                           |
   | attach      | 添加博客听众                   |
   | remove      | 删除博客听众                   |
   | notify      | 通知所有听众，获取当前博客内容 |
   | setContent  | 设置博客内容                   |
   | getContent  | 获取博客内容                   |

    

    

   类名BlogCSDN:

   | 属性   | 描述                      |
   | ------ | ------------------------- |
   | m_name | 博主名称（长度<16个字符） |

    

    

   类名ConsoleObserver：

   | 属性   | 描述                                     |
   | ------ | ---------------------------------------- |
   | m_name | 观察者名称（长度< 128个字符）            |
   | 方法   | 描述                                     |
   | update | 获得观察的博客的更新内容，并在控制台显示 |

    

在C++程序设计中，可以利用析构函数防止资源泄露，请给出模板auto_ptr的基本定义、实现，以及应用实例。





























编写函数 int count_word(const char *text,const char*word)来统计一个英语文本（由参数text指向）中的某个单词（由word指向）出现的次数。例如：函数调用count——word （“the theater is showing the film Gone With The Wind ”,"the"）返回值为3. 10%



























定义一个时间类CTime，它表示时分秒，并能实现以下程序段所需的功能。25%























1.编写程序，使其能读入最多10个正整数，其中输入过程中一旦有零或付整数输入，则停止读取，然后反向输出已读入的正整数。其中，输入的部分要求以函数GetNums实现，反向输出的部分以ReverseWrite实现，并且要求使用递归。













































 

 2．参照以下表格，定义两个类CStudent（学生）  和CUnderGraduated（大学生），要求使用继承，即定义CUnderGraduated  为CStudent  的子类，同时使其能完成如下功能： 
 [1]可以这样定义数组： 

CStudent  students[20];  CUnderGraduated   A[20];
 [2] CStudent   S(“Smith”,  0,  1002);
 0代表女生 
 CUnderGraduated   S(“John”,   3201,   1,   50,  “Title:  Programming  Language  C++”);           

1  代表男生 
 CUnderGraduated   S1  =  S; 
 [3] 在CUnderGraduated  中提供函数AddContent  用以增加该学生的论文内容 
 [4] 对于CUnderGraduated的对象S，S+=X  (X为int型)  可以使S学生的学分数增加X，例如   S  +  =20，可以使S学生的学分数增加20 
 [5] 对于CUnderGraduated的对象S1和S2，可以用  S1 <S2  比较两个大学生学号的先后关系 
 [6] 提供全局函数，统一输出学生的信息（输出格式没有要求），要求使用多态性 

 学生 Name 最长16个字符 No 正整数 Sex Male  或Female Name  表示学生姓名 Sex  表示性别    No表示学号 
 大学生 Name 最长16个字符 No 正整数 Sex Male  或Female Credits 正整数 Thesis 不定长的一组字符 Name  表示学生姓名 Sex  表示性别  No表示学号 Credits  表示学生的学分数  Thesis  表示毕业论文 

 注意：题目要求不得使用系统类string 
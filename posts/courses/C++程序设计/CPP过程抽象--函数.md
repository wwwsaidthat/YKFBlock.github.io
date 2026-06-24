# 函数

## Pointer and Array

## 数组降维

```c++
int maximum(int a[],int n){
    int max = 0;
 	for (int k=0;k<n;k++)
 		if (a[k] > max)
			max = a[k];
	return max;
}
void main(){ 
    int A[2][4] = { {68,69,70,71} , {85,86,87,89}};
 	cout << “the max grade is” << maximum(A[0],2*4);//语法糖而已
}
```

```c++
void show(int a[], int n){
 for (int i=0;i<n;i++){
     cout << a[i] << " ";
     cout << endl;
 }
 	cout << endl;
}

void show(int a[][2], int n)
{ for (int i=0;i<n;i++)
  for (int j=0;j<2;j++)
 { cout << a[i][j] << " ";
   cout << *(a+i)+j << " :" << a[i][j] << " ";
  if ((i*2+j+1)%4 == 0)
 	cout << endl;
 }
 cout << endl;
}

void show(int a[][2][3], int n)
{
 for (int i=0;i<n;i++)
 	for (int j=0;j<2;j++)
		for (int k=0;k<3;k++)
        {   cout << << a[i][j][k] << " :"<< a[i][j][k] << " ";
 			if((i*6+j*3+k+1)%4 == 0)
 				cout << endl;
        }
		cout << endl;
}

void main(){
    int b[12];
 	for (int i=0;i<12;i++) b[i] = i+1; 
    show(b,12);
    using T = int[2];
    show( ( T *) b,6);
	using T1 = int[3];
	using T2 = T1[2];
    show((T2 *)b,2);
}
```



## Pass By Value and Pass By Reference

```c++
//pass by value
int func(int a,int b){
    int r = a + b;
    return r;
}
int main(){
    int r = 0;
    r = func(1,2);
    return 0;
}
//pass by reference
void func(int& a){
    a = 1;
}
int main(){
    int r = 0;
    func(r);
    return 0;
}

```













## ADT Abstract Data Type

![image-20251201111852652](C:\software\Typora\picture\image-20251201111852652.png)

## any 和 any_cast

1. 如果自己构造的类，拷贝构造函数被删除了，那么这样的自定义类是无法用any来作为容器承担的

2. 读取any中的值必须要用any_cast<>

3. ```c++
   any a = 10;
   int temp = any_cast<int>(a);
   const int* ptr_a = any_cast<int>(&a);//如果不匹配返回的是nullptr
   a = "Hello";
   string str = any_cast<string>(a);
   any b = 3.14;
   double& b_ref = any_cast<double&>(b);
   
   
   any a;
   a = any_cast<int>(a) + 1;
   cout << any_cast<int>(a)<<endl;
   a = 1.5;
   cout << any_cast<double>(a)<<endl;
   a = string("hello");
   cout << any_cast<string>(a)<<endl;
   a = "hello";//默认是const char *
   cout << any_cast<const char*>(a)<<endl;
   ```

## const_cast(**Use const whenever possible**)用于移除const

```c++
用于移除const
    const int* ptr = 10;
	void func(int* p){
        *p = 100;
    }
	int main(){
        func(const_cast<int*> (ptr));
        cout << *ptr; //100
    }
```

## reinterpret_cast<>用于改变指针类型

```c++
int x = 123;
int* p = &x;
char* c = reinterpret_cast<char*>(p);
cout << *c;
```

## static_cast<>

1. 基本类型转换

   ```c++
   int a = 10;
   double b = static_cast<int>(a);
   
   double d = 1.1;
   int x = static_cast<double>(d);
   
   enum Color{Red = 0,Blue,Green};
   Color color = static_cast<Color>(2);//保证合法
   int color_val = static_cast<Color>(color);
   return 0;
   ```

2. 父子类继承体系转换

   ```c++
   Child c;
   Parent& t = static_cast<Parent&>(c);
   
   Parent* p = static_cast<Parent*>(&c);
   
   //Child* c = static_cast<Parent*> p;错的
   ```

3. void* 的转化

   ```c++
   int a = 10;
   void* void_ptr = &a;
   int* int_ptr = static_cast<int*>(void_ptr);
   
   ```

4. 消除重载函数的二义性

   ```c++
   void f(int i);
   void f(int i,int j = 10);
   static_cast<void(*)(int,int)>(f)(1);
   static_cast<void(*)(int)>(f)(1);
   
   ```

   ![image-20251205200356810](C:\software\Typora\picture\image-20251205200356810.png)

## dynamic_cast<>

```c++
Parent* p = new Parent();
Child* c = dynamic_cast<Child*>(p);//p指向父类实例 转换错误 nullptr
Parent* p = new Child();
Child* c = dynamic_cast<Child*>(c);//p指向子类实例 转换成功
Parent& p = Parent();
Child c = dynamic<Child*>(p);//p指向父类 转换错误 抛出bad_cast异常
```



## enum && union

```c++
enum Type{CHAR,INT,FLOAT};
struct Data{
    Type type;
    union{
        int i;
        char c;
        float f;
    }
}
int main(){
    Data d;
  	
    d.type = CHAR;
    d.c = 'c';
    printData(d);
    
    d.type = INT;
    d.i = 1;
    printData(d);
    
    d.type = FLOAT;
    d.f = 3.14f;
    printData(d);
    return 0;
}

void printData(const Data& d) {
    switch (d.type) {
        case INT:
            cout << "Int value: " << d.i << endl;
            break;
        case FLOAT:
            cout << "Float value: " << d.f << endl;
            break;
        case CHAR:
            cout << "Char value: " << d.c << endl;
            break;
        default:
            cout << "Unknown type" << endl;
    }
}
```

老师的例子:

```c++
union B{
    ... 
}
B b = ...
//B是类型

    
union{
    
}b
//b本身就是这个union类型了，但是这个union类型没名字
```

```c++
//float转换成二进制表示
#include "string"
using namespace std;
union{
 	float f;
    int i;
}u;
string float2B(float f){
    u.f = f;
    string s = "";
    for(int i = 31 ; i >= 0 ; i--){
        s += u.i & (1 << i) ? '1' : '0';//float 不能和int进行异或
    }
    return s;
}
```

```c++
union B{
    char b;//1
    int a;//4
    short c;//2
}
cout << sizeof(B);//4
```



```c++
//union
union Matrix{
    struct{
        double _a11,_a12,_a13;
        double _a21,_a22,_a23;
        double _a31,_a32,_a33;
    };
    double _element[3][3];
}
Matrix m;
int i;
int j;
m._a11 = 0;
m._a22 = 0;
m._a33 = 0;
for(i = 0 ; i < 3 ; i++){
    for(j = 0 ; j < 3 ; j++){
        m.element[i][j] = (i + 1) * (j + 1);
    }
}
for (i = 0;i < 3;i++){ 
    for ( j = 0;j < 3;j++)
 		cout << _element [i][j] << " ";
	cout << endl;
}
```

```c++
enum Figure_Type{LINE,RECTANGULAR,ELLIPSE};
struct Line{
    Figure_Type t;
    int x1,x2,y1,y2;
}
struct Ellipse{
    Figure_Type t;
    int x,y,r;
}
struct Rectangular{
    Figure_Type t;
    int left,top,right,bottom;
}
union Figure{
    FigurE_Type t;
//为什么这里可以写这个因为在每个结构体里面也都是在最前面的，等于解析方式也是相同的
//如果再加其他的东西，那么也是相同的 比如int width int color
    Line l;
    Ellipse e;
    Rectangular r;
}
Figure figures[100];
void main(){
    input(figures,100);
    for(int i = 0 ; i < 100 ;i++){
        draw(figures[i]);
    }
}
void draw(Figure f){
    switch(f.t){
        case LINE: draw_line(figure.line.x1,...);break;
        case RECTANGULAR : draw_rect(...);break;
        case ELLIPSE:draw_ellipse(...);break;    
    }
}
void input(Figure f[],int size){
    int t;
    for(int i = 0 ; i < size ; i++){
        cin >> t;
        switch(t){
            case LINE: ... break;
            case RECTANGULAR:...break;
            case ELLIPSE:...break;
        }
    }
}
```



## 函数重载

1. 函数名称是相同的，参数是不同的（个数、类型、顺序）

2. 返回值类型不作为区别重载函数的依据

3. 匹配原则

   1. 严格
   2. 内部转换（提升）
   3. 用户定义的转换

   ```c++
   void f(long);//提升
   void f(double);//跨类别转换
   f(10);//f(long)
   ```

## 带默认参数的函数

### 默认参数的声明

函数原型(函数声明)中给出，先定义的函数中给出 

```c++
void f(int a = 10);
void f(int a = 100){
    int b = a;
    cout << b;
}
//f()打印的是？编译出错，声明和定义给出都可以，但是必须是第一次！！！
```

如果是有声明那就声明的地方说默认，如果要是没有就在定义的地方写，但是必须是**第一次**

### 默认参数的顺序

从右到左，而且不能间断

```c++
void f(int a = 1,int b,int c = 0){
    
}
f(1);//error a不能省略编译都通过不了的

```

### 默认参数与函数重载

```c++
void f(int i,int a = 1);
void f(int i);
f(1);//error ambiguous
```

编译错误不是运行错误，违反二义性

## **内联函数inline**

目的：提高可读性，提高效率

实现方法：编译系统将为 inline 函数创建一段代码，在调用点，以相应的代码替换

**仅仅是请求**！！！

适用于使用频率高、简单、小段代码

缺点：

增大目标代码（object code）

病态的换页（thrashing)

降低指令快取装置的命中率（instruction cache hit rate)

## Lambda表达式

[capture list] (parameter list) specifiers exception -> type { function body } 

capture list：用于指定Lambda表达式内部如何访问其外部作用域中的变量。 

捕获方式有值(=)捕获、引用(&)捕获和混合捕获。 

parameter list: 定义Lambda表达式的参数，可以省略。

specifiers：用于指定Lambda表达式的属性，如mutable。 

exception specifier：用于指定Lambda表达式是否抛出异常，如noexcept。 

function body：Lambda表达式的主体部分，包含具体的执行代码。

## bind

1. 绑定普通函数：固定部分参数（参数占位）

​    场景：原函数有多个参数，绑定部分参数为固定值，剩余参数通过占位符留到调用时传。

```c++
#include <iostream>
#include <functional> // 必须包含该头文件
using namespace std;
using namespace placeholders; // 占位符_1、_2需要该命名空间

// 原函数：计算a + b * c
int calc(int a, int b, int c) {
    return a + b * c;
}

int main() {
    // 绑定：固定a=10，b用占位符_1，c用占位符_2
    // 调用时只需传2个参数（对应_1、_2），a固定为10
    auto bound_calc = bind(calc, 10, _1, _2);

    // 调用绑定对象：_1=2，_2=3 → calc(10, 2, 3) = 10+2*3=16
    cout << bound_calc(2, 3) << endl; // 输出16

    // 绑定：固定c=5，a=_1，b=_2 → calc(_1, _2, 5)
    auto bound_calc2 = bind(calc, _1, _2, 5);
    cout << bound_calc2(2, 3) << endl; // 2+3*5=17
    return 0;
}
```

2. 调整参数顺序

场景：原函数参数顺序不符合需求，通过占位符调整。

```c++
// 原函数：打印 a -> b
void print(int a, int b) {
    cout << a << " -> " << b << endl;
}

int main() {
    // 绑定：交换参数顺序 → print(_2, _1)
    auto bound_print = bind(print, _2, _1);

    // 调用时传(10, 20) → _1=10，_2=20 → print(20, 10)
    bound_print(10, 20); // 输出 20 -> 10
    return 0;
}
```

3. 绑定类成员函数（核心场景）

类成员函数隐含 `this` 指针，`bind` 需显式绑定 `this`（对象 / 指针），将成员函数转为普通可调用对象。

```c++
class Calculator {
public:
    int multiply(int a, int b) { // 非静态成员函数
        return a * b;
    }
    static int divide(int a, int b) { // 静态成员函数（无this）
        return a / b;
    }
};

int main() {
    Calculator calc;

    // 3.1 绑定非静态成员函数：第一个参数必须是对象/指针（&calc）
    // 格式：bind(&类名::成员函数, this对象, 参数占位符...)
    auto bound_mult = bind(&Calculator::multiply, &calc, _1, _2);
    cout << bound_mult(4, 5) << endl; // 4*5=20

    // 3.2 绑定静态成员函数：无需绑定this（无隐含指针）
    auto bound_div = bind(&Calculator::divide, _1, _2);
    cout << bound_div(10, 2) << endl; // 10/2=5
    return 0;
}
```

4.  绑定到 `std::function`（回调场景）

```c++
// 回调函数类型：接收int，返回int
using Callback = function<int(int)>;

// 执行回调
void run_callback(int x, Callback cb) {
    cout << "Result: " << cb(x) << endl;
}

// 原函数：计算 x * y
int multiply(int x, int y) {
    return x * y;
}

int main() {
    // 绑定：固定y=5，x用_1 → multiply(_1, 5)
    auto bound_mult = bind(multiply, _1, 5);
    
    // 赋值给std::function
    Callback cb = bound_mult;
    
    // 调用回调：x=6 → 6*5=30
    run_callback(6, cb); // 输出 Result: 30
    return 0;
}
```

5. 绑定引用参数（避免拷贝）

```c++
void modify(int& x) {
    x += 10;
}

int main() {
    int a = 5;

    // 错误：bind拷贝a，修改的是拷贝后的临时变量
    auto bad_bind = bind(modify, a);
    bad_bind();
    cout << a << endl; // 仍为5

    // 正确：用ref传递引用，修改原变量
    auto good_bind = bind(modify, ref(a));
    good_bind();
    cout << a << endl; // 15

    // 常量引用：cref
    int b = 20;
    auto const_bind = bind([](const int& x) { cout << x << endl; }, cref(b));
    const_bind(); // 输出20
    return 0;
}
```

#### declaration

namespace L{

int k;

void f(int);

}

using L::k;

using L::f;

k = 0;

f(6);

#### directive

using namespace L;

k = 0;

f(6);

## using namespace std; 

## using的用法

```c++
//using 别名 = 原类型;
using INT = int;
using INTPTR = int*;
using STR = string;
using CalcFunc = int(*)(int,int);
using StrVec = vector<string>;
template <typename T>
using IntMap = std :: map<int,T>;
//using namespace 命名空间名称
using namespace std;//将命名空间内的所有成员暴露到当前作用域
//using 命名空间::成员
int main(){
    using std::cout;
    using std::endl;
    cout<<"Hello"<<endl;
    return 0;
}
//用于继承引入基类成员
class Base {
public:
    void func(int a) { cout << "Base::func(int): " << a << endl; }
    void func(double a) { cout << "Base::func(double): " << a << endl; }
    int value = 100;
};

class Derived : public Base {
public:
    // 引入基类的func重载集（否则派生类的func会隐藏基类版本）
    using Base::func;
    
    // 重定义一个func版本
    void func(string a) { cout << "Derived::func(string): " << a << endl; }
    
    // 调整基类成员的访问权限（Base::value是public，这里改为private）
    using Base::value;
private:
    // value现在是Derived的private成员
};

int main() {
    Derived d;
    d.func(10);    // 调用Base::func(int)（因using引入）
    d.func(3.14);  // 调用Base::func(double)（因using引入）
    d.func("test");// 调用Derived::func(string)
    // cout << d.value; // 编译报错：value是Derived的private成员
    return 0;
}
//using的局部作用域 只在{}内有用
#include <iostream>
#include <string>

int main() {
    if (true) {
        using namespace std; // 仅在if块内生效
        string s = "local";
        cout << s << endl;
    }
    // string s = "test"; // 编译报错：std::string未引入
    std::cout << "1";//right
    return 0;
}
```

## namespace

```c++
// 定义命名空间
namespace MyMath {
    // 命名空间内的函数
    int add(int a, int b) { return a + b; }
    // 命名空间内的常量
    const int PI = 3.14159;
    // 命名空间内的类
    class Calculator {};
}
int main() {
    // 全限定名访问
    cout << MyMath::add(3,4) << endl; // 7
    cout << MyMath::PI << endl;       // 3.14159

    // 引入单个成员
    using MyMath::PI;
    cout << PI << endl; // 3.14159

    // 引入整个命名空间
    using namespace MyMath;
    Calculator c; // 直接使用类
    return 0;
}
```

```c++
// 匿名命名空间：仅当前.cpp可见
namespace {
    int b = 20; // 仅当前文件可访问
    void helper() { cout << "Helper func" << endl; }
}

int main() {
    cout << b << endl; // 合法（当前文件）
    helper();          // 合法
    return 0;
}
```

*内联命名空间（Inline Namespace，C++11+）

- **含义**：用 `inline namespace` 定义的命名空间，其成员会 “透传” 到外层命名空间，主要用于**版本兼容**（比如库的新旧版本切换）。

- **核心价值**：对外暴露统一接口，内部可切换不同版本的实现，无需修改外部调用代码。

  ```cpp
  namespace MyLib {
      // 内联命名空间：版本2（默认生效）
      inline namespace V2 {
          void func() { cout << "V2 func" << endl; }
      }
  
      // 非内联命名空间：版本1（需显式访问）
      namespace V1 {
          void func() { cout << "V1 func" << endl; }
      }
  }
  
  int main() {
      MyLib::func(); // 直接调用V2::func（因V2是内联）
      MyLib::V1::func(); // 显式调用V1::func
      return 0;
  }
  ```

  

- **场景**：库升级时，将新版本设为内联，旧版本保留，外部代码无需修改即可使用新版本，如需兼容旧版本可显式指定。

*命名空间别名（Namespace Alias）

- **含义**：为长命名空间（尤其是嵌套命名空间）定义短别名，简化访问。

- **语法**：`namespace 别名 = 原命名空间名;`

- 示例：cpp

  ```cpp
  // 原嵌套命名空间
  namespace Company::Product::Math {
      int add(int a, int b) { return a + b; }
  }
  
  // 定义别名
  namespace M = Company::Product::Math;
  
  int main() {
      cout << M::add(3,4) << endl; // 等价于全限定名
      return 0;
  }
  ```

## define 宏

## Function

1. 定义不允许嵌套
2. 先定义后使用



```c++
void func1(){func2();}
void func2(){func1();}
//不允许嵌套定义但是允许递归
```

## Function Pointer

```c++
double(*fp1)(int) = &f;
(*fp1)(10);
int(*fp2)() = &g;
(*fp2)();
double f1(int x1){...}
double f2(int x2){...}
double f3(int x3){...}
using FP= double(*)(int);
FP fp = f1;
fp = f2;
fp = f3;
```

定义的模式为

```c++
返回值类型 (*函数指针名称) (参数1类型，参数2类型...);

typedef 返回值类型 (*函数指针类型) (参数1类型，参数2类型...);//声明的是类型的别名，用typedef是为了兼容旧代码
    
using 函数指针名 = 函数返回值类型 (*) (参数1类型，参数2类型...);//声明的是类型的别名
```

```c++
int add(int a,int b){
    return a + b;
}
int sub(int a,int b){
    return a - b;
}
int main(){
    int (*fp) (int a,int b) = add;//right
    //int (*fp) (int a,int b) = &add;is also right
    int res1 = (*fp)(1,2);
    int res2 = fp(1,2);//语法糖
    fp = sub;
    int res3 = fp(5,3);
    cout << res1 << " " << res2 << " " << res3<<endl;
    return 0;
}


result : 
3 3 2
    
```

```c++
typedef int(*fp1)(int,int);
fp1 f1 = add;
using fp2 = int (*) (int,int);
fp2 f2 = sub;                         
```

*指向类成员函数的指针

```cpp
class Math {
public:
    int multiply(int a, int b) { return a * b; } // 非静态成员函数
    static int divide(int a, int b) { return a / b; } // 静态成员函数
};
int main(){
    int (Math::*mem_fp)(int,int) = &Math::multiply;//这里必须加&
    Math m;
    cout << (m.*mem_fp)(4,5)  << endl;
    Math* pm = &m;
    cout <<(pm -> *mem_fp)(4,5) << endl;
}
```

指向重载函数的指针

```c++
void print(int a) { cout << "int: " << a << endl; }
void print(double a) { cout << "double: " << a << endl; }
int main(){
    void(*fp1)(int) = print;
    fp1(1);
    void(*fp2)(double) = print;//根据参数列表匹配
    fp2(1.1);
}
```

函数指针作为函数的参数

```c++
using Callback = void(*)(int,int);
void calculate(int a,int b,Callback cb){
    cb(a,b);
}
void printSum(int a,int b){
    cout << a + b<<endl;
}
void printProduce(int a,int b){
    cout << a * b<<endl;
}
int main(){
    calculate(3,4,printSum);
    calculate(3,4,printProduce);
    return 0;
}
```

老师的例子

```c++
#include <math.h>
 double integrate(double (*f)(double),double a, double b)
 { … f(x), a , b, … }
 double my_func(double x)
 { … }
 void main()
 { …
 integrate(sin,0,1);
 integrate(cos,1,2);
 integrate(my_func,1,10);
 …
 }
```


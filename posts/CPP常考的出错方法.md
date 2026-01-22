1. 函数的副作用

   1. 注意函数里面修改了某个变量的值

      ```c++
      int x;
      int f(){
          int y = 1;
          y = x + 10;
          x++;//改了x的值
          return y;
      }
      int main(){
          int m = f();
          m = m + x;
          return 0;
      }
      ```

2. C++采取的是静态作用域

   1. ```c++
      int x;
      ...x... // 外部的x
      while(...x...)//外部的x
      {
          ...x...//外部的x
              int x；
              ...x...//内部的x
      }
      ...x...//外部的x
      ```

   2. **不管循环多少次永远都是这样的**

   3. extern的作用：声明的变量或者函数不在当前的编译范围内、

      ```c++
      int x;
      void f(){
          ...x...//ok
      }
      void g(){
          ...x...//ok
      }
      ```

      ```c++
      file1.cpp
      int x = 1;
      extern int y;
      extern void f();
      int main(){
          ...x...//ok
          ...y...//ok
          ...z...//no
          f();//ok
          g();//error
      }
      file2.cpp
      int y = 0;
      void f(){
          ...y...//ok
          ...x...//error
      }
      double z = 2;
      void g(){
          ...z...//ok
      }
      ```

   4. 想要在局部里面用全局的

      ```c++
      int x = 10;
      int main(){
          int x = 1;
          cout << ::x;//10
          cout << x;//1
      }
      ```

3. static 只能在定义他们的源文件中使用 外部不能使用--编译正确但是链接错误

   ```c++
   file1.cpp
       static int a = 0;
   file2.cpp
       extern int a;//error
   	cout << a;//error
   ```

4. goto只能跳转到函数当前作用域内的位置

5. namespace A{} A::x using A x

6. 不同的源文件可以有不同的声明值，相同的源文件只能有一个声明值

   ```c++
   void f(int a,int b = 1,int c = 10);
   void f(int a,int b,int c){
       ...
   }
   f(1);//ok
   
   
   void f(int a,int b,int c);
   void f(int a,int b = 1,int c = 10){
       ...
   }
   f(1);//error
   
   
   void f(int a,int b =1 ,int c = 10){
       ...
   }
   f(1);//ok
   
   
   void f(int a,int b = 1,int c = 10){
       ...
   }
   void f(int a,int b = 2,int c = 20){
       ...
   }//error 默认参数是不参与函数重载的
   
   
   void f(int a,int b = 1,int c = 10){
       ...
   }
   void f(int a,int b = 10){
       ...
   }
   f(1);//error 二义性 如果不写这个是不报错的
   static_cast<void (*)(int ,int ,int)> (f)(1,1,10);//默认参数是语法糖 对函数指针不生效
   
   ```

7. 重载函数的匹配

   1. 精确匹配
   2. 提升匹配 char -> int  float ->double
   3. 标准转换匹配
   4. 自定义转换匹配 强制类型转换

8. λ函数

   [捕获参数]（形参）->返回值{函数体}

9. 内联函数的优点

   1. 高效
   2. 方便编程提高可读性

10. 内联函数滥用的缺点

    1. 导致变异之后的代码体积过大
    2. 指令快取装置命中率低
    3. 病态换页

11. 返回局部变量导致访问无效内存的只能是指针 引用（和数组）

12. 可能导致delete多次的

    1. 两个指针指向同一个对象，在函数结束的时候就释放，其中还有浅拷贝的协助

       ```c++
       int* p1 = new int[10];
       int* p2 = p1;
       delete p1;
       delete p2;//释放两次
       
       MyClass* mc1 = new MyClass;
       MyClass* mc2 = mc1;//要求是浅拷贝才会出问题
       //默认的复制构造函数是浅拷贝
       ```

13. delete错误

    1. 析构函数不是虚函数

       ```c++
       base derived
       Base* b = new Derived();
       delete b;//把b析构调用的是Base的析构函数，而不是derived可能导致derived中的一些新东西没有被释放
       ```

14. 八个默认函数

    ```c++
    Empty();
    ~Empty();
    Empty(const Empty&);
    Empty(Empty&&);
    Empty& operator=(const Empty&);
    Empty& operator=(Empty&&);
    Empty* operator&();
    const Empty* operator&() const;
    ```

    

typedef T U[c2]

using U = T[c2]
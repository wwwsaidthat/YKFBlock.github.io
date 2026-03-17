1. 重载函数

   1. 不能只有返回值不同
   2. 匹配顺序
      1. 精确匹配
      2. 提升匹配
      3. 标准转换
      4. 自定义转换
   3. int匹配的顺序
      1.  int
      2.  long   & long long 
      3. double & float 同时出现报编译错误

2. lambda表达式

   1. 形式

      [捕获变量] (参数列表) 可选限定符 -> 返回类型{

      //函数体

      }

   2. ```c++
      [](float a,float b){return a<b;}
      [](float a,float b)->bool {return a<b;}
      []//代表不捕获外部变量
      [=]//自动捕获 按值拷贝 不可以修改这个值
      [&]//自动捕获 按引用获取 可以修改这个值
      [x,y]//默认按值
      [&a,&b]//按引用拷贝
      [=,&x]//规定某个是引用 别的按照默认走
      [&,x]//规定某个是按值 别的按默认走
      ()//这里面是函数的参数值
      ->//后面写的是函数的返回值类型 不写默认自动识别了
      {}//里面写函数体
      [x]()mutable {
          
      }
      //函数
      float x = 1.0f;
      float y = 2.0f;
      auto p = [x,y](float a,float b)->float{return a*b + x*y;}
      float result = p(3.0f,4.0f);
      return result;
      
      sort(vec.begin(),vec.end(),[](int a,int b)->bool{return a<b;})
      ```

   3. 捕获变量和参数列表的区别 捕获变量不是这个函数的传入的参数，是从外部作用域自动捕获的 
      外部作用域包括函数内的局部变量、全局变量、类成员变量等等
      在函数体内部实现的时候这些参数没有任何的差异，直接用就可以，这相当于在写一个函数，函数的参数是（）里面的

3. 操作符重载

   1. const class& operator (符号) (){}
   2. 多目运算符的重载
   3. 单目运算符的重载

4. 继承多态 

   1. 虚函数 纯虚函数 非虚函数

   2. 类的成员函数才可以是虚函数，静态成员函数不能是虚函数，内联成员函数不能是虚函数

   3. 虚函数 virtual的存在是为了重写override

      1. 纯虚函数 只有函数声明没有函数实现 virtual funcName() = 0;
         1. 含有纯虚函数的类称之为抽象类，抽象类的子类在子类没有实现纯虚函数的时候也是抽象类，子类**必须**重写父类的纯虚函数之后才能定义对象，并不影响抽象类可以与自己的成员和构造函数,抽象类不能创建对象
         2. 为派生类提供框架，派生类提供抽象基类的每个成员函数的实现
         3. 这个等于零意味着函数的定义不在此处，"not there"
         4. 子类重写的时候可以写一个override
         5. 构造函数不能是纯虚函数，析构函数可以，但是需要有定义（定义之后但是不一定用）而且虚构函数往往是虚函数
            1. 举个例子
               1. Parent* p = new Child();
               2. Parent的析构函数如果不是虚函数delete p删除的就是p自己的虚函数，否则调用的是child的析构函数
            2. 这并不是因为每个类的析构函数的名字不一样，因为编译器对于不一样名字的析构函数会进行特殊处理，因此可以正常实现继承（**派生类析构函数名称可以与基类不同**）
            3. 派生类销毁的时候会调用基类的析构函数，因此基类的析构函数可以是纯虚函数，但是必须有定义

      2. 虚函数 有函数声明也有函数实现，继承的时候只继承函数声明 virtual funcName(){}
         1. 包含虚函数的类是可以实例化对象的
         2. 有实现，有声明
         3. 父类virtual void func(){cout<<"virtual func";}
         4. 子类void func()override{cout << "func";}
         5. 不要求子类一定重写，可以继承父类的实现，也可以继承基类的实现

      3. 非虚函数 没有virtual ~ 不可以在子类重写了

      4. 什么是纯虚函数，虚函数和非虚函数？合理定义三种成员函数所应该遵循的基本原则，请给出一个你认为合理定义的一个实例，并说明

         1. ![image-20251201093134452](C:\software\Typora\picture\image-20251201093134452.png)

         2. 纯虚函数是指只有函数声明但是没有函数接口的函数，有纯虚函数的类称为抽象类，抽象类不可以定义实例对象，如果子类想要定义实例对象，必须在子类完成纯虚函数的实现 override，函数声明前面加virtual 最后加=0

         3. 虚函数是指有函数声明也有函数实现的函数，子类不对其重写也可以直接继承基类的实现，函数声明前面加virtual，不希望再被重写可用 final

            1. ```c++
               class A {
                   virtual void f1(int) const;
                   virtual void f2();
                   void v3();
                   virtual void f5(int) final;
                   
               }
               class B : public A{
                   void f1(int) const override;//right
                   void f2(int) override;//error no para
                   void f3() override;//
                   void f4() override;
                   void f5(int);
               }
               ```
            2. ```c++
               struct B{
                   virtual void f1(int)const;
                   virtual void f2();
                   void f3();
                   virtual void f5(int)final;
               }
               struct D : B{
                   void f1(int) const override;
                   void f2(int) override;
                   void f3()override;
                   void f4()override;
                   void f5(int);
               }
               
               ```

               1. f1是正确的
               2. f2是错误的，参数不对，重写必须保证参数是一致的
               3. f3是错误的，override必须得是虚函数啊 B结构体里面不是virtual的
               4. f4错误 B里面都没有你重写个damn啊
               5. f5错误 B里面声明了final了，你还重写，final就是为了不想让你override你还override上了，错误

         4. 非虚函数就是正常的函数，不可以进行父类和子类之间的重写操作

         5. 举例实现

            ```c++
            class Shape{
                public:
                virtual void draw() const = 0;
                
                virtual void error(const string& msg);
                
                int ObjectId() const;
            }
            ```

            

      5. 纯虚函数和虚函数的默认参数都是基类的默认参数，如果基类没写，那认为他就是没有默认参数的，参数的默认值只看基类

         ```c++
         class A{
             public :
             virtual void f(int x = 0)= 0;
         }
         class B : public A{
             public:
             virtual void f(int x = 1){
                 cout << x;
             }
         }
         A* pa;
         B b;
         pa = &b;
         pa->f();
         
         
         ```
      
         虚函数是动态绑定的，但是虚函数的默认参数是静态绑定的，追求效率
      
      6. 虚函数表
      
         1. 动态绑定或者叫做后期绑定的实现
            1. 对象的内存空间中含有指针，指向其虚函数表


​      

```c++
  class A{
      int x,y;
      public:
      virtual void f();
      virtual void g();
      h();
  }
  class B:public A{
      int z;
      public :
      void f();
  }
  A a;
  B b;
  A* p = &a; // ---- 1
  A* p = &b; // ---- 2
```


​      

​      

​      

​      

​      

​      

      7. private inheritance
    
         1. ```c++
            class CHumanBeing{...};
            class CStudent:private CHumanBeing{...};
            void eat(const CHumanBeing& h){...};
            CHumanBeing a;
            CHumanBeing b;
            eat(a);
            eat(b);
            //error Parent* p = new Child();是不允许的，因此这个也是错误的
            ```
         
         2. 私有继承不能使用派生类自己定义意外的其他的任何内容，基类的东西，派生类对象都不可以用
         
         3. ```c++
            Parent* p = new Child();//这个东西是不允许的
            ```
         
         4. 
         
      8. 抽象工厂模式
    
         代码是一致的，具体执行的函数根据实际的情况来确定
    
         ```c++
         AbstractFactory* fac;
         case MAC:
         	fac = new MacFactory;
         case WIN:
         	fac = new WinFactory;
         
         Button* pb = fac -> CreateButton();
         pb->SetStyle(...);
         Label* pl = fac -> CreateLabel();
         pl->SetStyle(...);
         ```
    
         ![image-20251201084939956](C:\software\Typora\picture\image-20251201084939956.png)
    
      9. 虚继承又叫做菱形继承
    
         1. 虚基类
    
            1. ```c++
               class A{
                   public: 
                   	int x;
                   	A(int i):x(i){}
               }
               class B : virtual public A{
                   public :
                   	int y;
                   	B(int k):A(0){y = k;}
               }
               class C:virtual public A{
               	public :
                   	int z;
                   	C(int j):A(0){z = j;}
               }
               class D:public B,public C{
                   public:
                   	int w;
                   	D(int i,int j,int k):C(j)，B(i)，A(k){w = 0;}
                   	
               }
               
               //在构造D的对象时候，先构造虚基类A，然后构造BC 从上往下，从左往右 B和C的顺序由classD的声明顺序，class D:public B,public C先B后C
               //实现是通过BC本身继承的就是同一个A来实现，也是虚基类表
               ```

5. 模板（参数化多态）

   1. 指针实现多态

      1. void*可以接收任意类型的

      2. 函数指针实现比较

      3. ```c++
         void sort(void* base , unsigned int count,unsigned int element_size,bool(*less_than)(const void*,const void*));
         bool int_less_than (const void* a, const void* b){
             return *(int*)a < *(int* b);
         }
         bool double_less_than(const void* a, const void* b){
             return *(double*)a < *(double*)b;
         }
         int x = 1,y = 2;
         const void* a = &x;
         const void* b = &y;
         sort(base,count,element_size,int_less_than(a,b));
         ```

      4. 缺点：

         1. 需要额外定义参数
         2. 程序易读性很差
         3. 不利于编译程序进行类型检查

   2. 函数模板实现类属函数

      1. ```c++
         template<class T>
             void sort(T elements[],unsigned int count){
             ...
         }
         ```

   3. 函数模板的实例化（函数模板：确定函数模板实例的过程叫做模板实参推演）

      1. ```c++
         sort<int>(参数)
         ```

   4. 函数模板也能重载，形参表或者类型参数表也是不一样的

   5. 有实例化的话只能匹配函数模板，没有的话优先匹配非模板函数，然后匹配模板函数，然后匹配实参经过类型转换之后能够匹配的普通函数（重载），然后报错

   6. 注意不要定义与继承而来的非虚成员函数同名的成员函数，因为不管参数列表是否是相同的，都会覆盖掉基类的，而不会形成重载

      ```c++
      class B{
          public :
          virtual void mf();
      }
      class D : public B{
          public :
          void mf(int);
      }
      int main(){
          D* pd = new D;
          pd->mf();
      //error 基类 的 无参数的mf函数已经被覆盖掉了，编译不通过
      }
      ```

      

   7. 匹配函数模板的时候不会进行类型的自动转换

   8. 类模板

      1. ```c++
         template<class T1,class T2>
         class pair{
         public:
             T1 key;
             T2 value;
             pair(T1 k,T2 v):key(k),value(v){}
             
         }    
         ```

      2. 类模板派生出来的不同类，静态变量是不同的，相同的类才能共用

6. 绑定

   1. 静态绑定（又称为前期绑定） ： 

      1. 编译的时候进行绑定
      2. 依据对象的静态类型
      3. 效率高、但是灵活性差

   2. 动态绑定 ： 
   
      1. 运行的时候进行绑定
      2. 依据对象的实际类型
      3. 灵活性高但是效率低
      4. 根据实际引用和指向的对象类型
   
   3. C++编译器讲究效率，因此默认是前期绑定，静态绑定的东西不会动态绑定，在C++中动态绑定的只有虚函数virtual（显示指出）
   
      ```c++
      class myClass{
          public :
          void test(){
              cout<<"this is myClass\n";
          }
      }
      int main(){
          myClass MyClass = Null;
          MyClass.test();//在C++中是可以正常输出this is myClass 
          //编译的时候根据变量声明的类型确定调用的函数方法，而不是看对象的实际类型
          
      }
      ```
   
   4. 动态绑定只有在类内虚函数继承多态的时候才是动态绑定其余情况都是静态绑定
   
      ```c++
      class A{
          public :
          virtual void test(){
              cout << "this is class A";
          }
          //virtual void try1() = 0;
          void set(){
              cout<<"abab";
          }
      }
      class B : public A{
          void test()override{
              cout << "this is class B";
          }
          //void try1(){
            //  cout << "B";
          //}
          void set2(){
              cout<<"abab2";
          }
      }
      
      int main(){
          A* a = new B();//子类全 能给父类赋值
          a.test();//这个时候在编译的时候无法确定调用的是哪个函数了，那就会在运行时才会去绑定，又叫做动态绑定
          a.try1();//就算是纯虚函数也是动态绑定的,这里只是为了说明 这个实际上是错误的，因为抽象类不可以定义，因此知道纯虚函数也要动态绑定就行了
      }
      ```
   
      在C++中只有这一种情况是动态绑定的情况其他都是静态绑定的情况
   
      
   
   5. 静态绑定的主要是下面两种
   
      1. 类里面的非虚函数（包括静态函数）考的话有考点的就是继承
   
         ```c++
         class parent{
             public :
             void print(){
                 cout<<"parent";
             }
         };
         class son:public parent{
             public:
             void print(){
                 cout<<"son";
             }
         };
         int main(){
             parent* p = new son();
             son* s = new son();
             p.print();//parent
             s.print();//son
         }
         ```
   
         这样就属于函数隐藏了，如果子类和父类的函数名称一样，那么不管参数是否相同，子类都会直接把父类覆盖掉，这不会是重载，你就无法调用父类的函数形式了
   
         ```c++
         class parent{
             public:
             void print(string s){
                 cout<<s;
             }
         };
         class son:public parent{
             public :
             void print(){
                 parent::print("abab");//允许显示调用
                 cout<<"son";
             }
         };
         int main(){S
             parent* p = new son();
             son* s = new son();
             string s = "ababa";
             p.print(s);//"ababa"
             s.print(s);//error! son的print函数已经覆盖掉父类的print(s)函数了,无法拿到这个函数 子类没有这样的函数的
         }
         ```
   
         
   
      2. 正常的函数重载
   
         c++是允许函数重载的，同名但是不同的函数参数的函数之间
   
         ```c++
         void print(string s){ cout << s; }
         void print(int a){cout << a;}
         int main(){
             string s = "aba";
             int a = 10;
             print(s);//编译的的时候会变成比如print_string()
             print(a);//编译的时候就会变成比如print_int()
         }
         ```
   
      3. 函数模板(这个不算是静态绑定) 算是一种模板实例化 但是也是在编译期间（编译期间把模板转换成需要的函数）实现的， 编译出来的 函数
   
      4. 另外注意除了虚函数的多态别的都是静态绑定，但是不要把不是绑定的东西当成绑定，我是说引用类型的使用他不是看声明成什么，因为他是直接用的本身，所以可以视为类动态绑定或者说静态绑定帮的是本身的类型而不是声明的类型
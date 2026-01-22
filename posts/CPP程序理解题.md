# 程序理解题

1. 设有一个矩阵：       

​	 0    2   1
 	       1    0    2
 	       1    2    0 
 	现把它放在一个二维数组a中，写出执行下面语句后a的值 
 		for  (int  i=0;  i <=2;  i++)
   		for  (int  j=0;  j <=2;  j++)
​     		a[i] [j]  =  a[a [i] [j] ] [a[j] [i]];



a[0] [0] = a[a[0] [0]] [a[0] [0]] = a[0] [0] = 0;

a[0] [1] = a[a[0] [1]] [a[1] [0]] = a[2] [1] =2;

a[0] [2] = a[a[0] [2]] [a [2] [0] ] = a[1] [1] = 0;

a[1] [0] = a[a[1] [0]] [a[0] [1]] = a[1] [2] = 2;

a[1] [1] = a[a[1] [1] ] [a [1] [1]] = a[0] [0] = 0;

a[1] [2] = a[a [1] [2] ] [a[2] [1] ] = a[2] [2] = 0;

a[2] [0] = a[a[2] [0]] [a[0] [2]] = a[1] [0] = 2;

a[2] [1] = a[a[2] [1]] [a[1] [2]] = a[2] [0] = 2;

a[2] [2] = a[a[2] [2]] [a[2] [2] ] =a[0] [0] = 0;



2. ```c++
   #include   <iostream   >
   using   namespace   std;
   void   f(int   &x,int   y)
   {     y   =   x   +   y;
         x   =   y   %   4;
         cout   < <   x   < <   y   < <   endl;
    //1 9
   }        
   void   main()
   {   int   x   =   4,   y   =   5;
       f(y,x);//f(5,4)
       cout   < <   x   < <   y   < <   endl;//
       f(x,x);//f(4,4)
       cout   < <   x   < <   y   < <   endl;//
   }
   
   ```

   1 9

   4 1

   0 8

   0 1

   

3. ```c++
   #include   <iostream.h>
   class   A
   {   
   public:
        A() {   cout   <<   "in   A 's   constructor\n ";   };
        ~A() {   cout   <<   "in   A 's   destructor\n ";   };
   };
   class   B
   { 
   public:
        B() {   cout   <<   "in   B 's   constructor\n ";   };
        ~B() {   cout   <<   "in   B 's   destructor\n ";   };
   };
   class   C:   public   B
   { 
   private:
        A   a;
   public:
       C(){   cout   <<   "in   C 's   constructor\n ";   };
       ~C(){   cout   <<   "in   C 's   destructor\n ";   };
   };
   void   main()
   {     A   a;
     ①
         B   *p=new   C;
     ②
                 delete   p;
     ③
   }④
   
   ```

   in   A 's   constructor

   in   B 's   constructor

   in   A 's   constructor

   in   C 's   constructor

   in   B 's   destructor//B的析构函数是非虚析构函数，如果是的话才会调用C的

   in   A's    destructor

   

4. 指出程序中的错误

```c++
const   int   x=0; 
int   y   =   2;
const   int   *p1   =   &x;
int   *const   p2;
int   *p3;
p1   =   &y;
*p1   =   10;
p2   =   &y;
p3   =   &x;

```

1. p2作为指针常量，应该在声明的时候就初始化
2. p1是一个指向常量类型的指针，不可以通过p1来修改本来的值
3. &x不能赋值给p3，因为p3是一个指向int类型的指针，如果把指向常量类型的指针赋值给int类型的指针会导致常量被修改



5. ```c++
   class   A{
   int   m1;
   static   char   m2;
   public:
         void   f1()   {   m1   =   0;   m2   =   1;   };
         static   void   f2()   {   m1   =   2;   m2   =   3;   };
         void   f3()   const   {   m1   =   4;   m2   =   5;   };
   };
   
   ```

   1. 静态成员函数不能修改非静态变量
   2. 常量成员函数不能修改当前对象，也就是说不能修改m1

6. ```c++
   四、下面的C++程序能正常结束吗？如果不能，请指出原因。（5分） 
   class   A
   {   int i,j;
       public:
         A()   {   i=j=0;   }
   };
   class   B
   {     A   *p;
       public:
           B()     {     p   =   new   A;     }
         ~B()   {     delete   p;     }
   };
   void   f(B   x)
   {   ......
   }
   void   main()
   { B   b;
       f(b);
   }
   
   ```

   f(b) 调用B类型的默认复制构造函数，发生的是浅拷贝，在f函数里面结束的时候会导致b被释放，而在main函数的结尾又被释放一次，从而导致了两次释放出现错误。

   

7. ```c++
   #include <IOSTREAM>
   #include <stdlib.h>
   #include <time.h>
   using namespace std;
   
   class poker{
   public:
   	unsigned int id;
   	poker(){ id = rand() % 13 + 1; } // 产生1-13的随机数
   };
   void main() {
   	srand(unsigned(time(NULL)));  // 根据系统时间，设置随机种子值
   	poker* p = new poker[5];
   	int sum = 0;
   	for(int i=0; i<5; i++, p++)
   		sum += p->id;
   	cout << "总点数为: " << sum << endl;
   	delete p;
   }
   
   
   ```

   for循环里面移动了p的位置，导致了释放错误

   应该使用delete[] p;否则poker类的析构函数只被调用了一次，会导致没有被析构就被释放的情况出现

8. ```c++
   int* get_inputs() {
   	int numbers[10];
   	int i;
   	for(i = 0; i < 10; i ++) {
   		cin >> numbers[i];
   	}
   	return numbers;
   }
   
   
   int find_max(int* inputs, int size) {
   	int i;
   	int max = -1;
   	for (i = 0; i < size; i ++ ) {
   		if (max < inputs[i]) {
   			max = inputs[i];
   		}
   	}
   	return max;
   }
    
   
   void main() {
   	cout << "inputs" << endl;
   	int* inputs = get_inputs();
   	cout << "find the max" << endl;
   	int max = find_max(inputs, 10);
   	cout << "max: " << max << endl;
   }
   
   ```

   1. int numbers[10] 分配的空间在栈上面，函数结束之后空间会被释放，导致inputs指向了一个释放的空间
   2. 解决办法
      1. 分配空间到堆上面 new int[10];
      2. 用静态数组 static int[10];//到main结束才会释放
      3. 用vector会自动管理内存的

9. ```c++
   #include <IOSTREAM>
   using namespace std;
   
    
   class Vehicle{
   public:
   	virtual void run(int number = 10){
   cout << "we do not know how to run\n";
   	}
   	
   	virtual void stop(){
   cout << “we do not know how to stop\n”;
   }
   	void announce(){
   		cout << "this is a vehicle\n";
   	}
   };
   
   class Car:public Vehicle
   {
   public:
   	void run(int number = 60){
   cout << "driving at " << number << " km/h\n";
   	}
   	void stop(){
   		cout <<“brake to stop \n”;
   }
   	void announce(){
   		cout << "this is a car\n";
   	}
   };
    
   
   void main()
   {
   	Vehicle v1,*v2;
   	Car c1;
   	v1.run();//we do not know how to run
   	c1.announce();//this is a car
   
   	v2 = &c1;
   	v2->run();//driving at 10 km/h  // 默认参数用的是基类的值！！！
   	v2->stop();//brake to stop
   	v2->announce();//this is a vehicle
   }
   
   ```

10. ```c++
    class Error { 
    public: 
    	virtual void show(){ cout << "something is error"<<endl;}
    }; 
    
     
    class nameError:public Error { 
    public: 
    	void show() { 
    cout<<"name is error"<<endl; 
    } 
    }; 
    
    class ageError:public Error { 
    public: 
    	void show() { 
    cout<<"age is error"<<endl; 
    } 
    }; 
     
    class Person { 
    private: 
    	int age;
    	char* name; 
    public: 
    	void setAge(int a) { 
    		ageError ag; 
    		if(a<0||a>100) 
    			throw ag; 
    		this->age=a; 
    } 
    	void setName(char* str){
    nameError ne; 
    		if(str=="exit") 
    			throw ne; 
    		this->name=str; 
    }
    }; 
     
    void catcher(int command, Person p){
    	try { 
    		switch(command){
    		case 1:
    			p.setAge(101);
    			break;
    		case 2:
    			p.setName("exit");
    			break;
    		}
    	}
    	catch(nameError ner){
    		ner.show();
    	}
    	catch(Error er) { 
    		er.show();
    	}
    	catch(ageError aer){
    		aer.show();
    	}
    }
     
     
    int main(void) {
    	Person p; 
    	catcher(1, p);//something is error
    	catcher(2, p);//name is error
    	cout<<"program end"<<endl; //program end
    	return 0; 
    }
    
    ```

11. ```c++
    typedef double F;
    int main()
    {
        int a=8,b=3;
        F c=2.0;
        cout << (a/b/c>1?b/a>0?1:2:3);
        //(a/b/c > 1) ? (b / a > 0 ? 1 : 2) : 3
        return 0;
    }
    
    //3
    
    ```

12. ```c++
    void func( long a, long b) { 
        cout << "long"; 
    }
    void func(double a, double b) { 
        cout << "double"; 
    }
    int main(int argc, const char * argv[]) { 
        int a=1, b=1; 
        func(a, b);
        return 0; 
    }
    //long
    
    ```

13. ```c++
    class A{
        int x; 
    public:
        A(int i = 0) { 
            x= i; 
            cout<< x << " is constructed" << endl; 
        }
        A(A&A) { 
            x= A.x; 
            cout<< "Copy of " << x<< " is construced." << endl; 
        }
        ~A() {
            cout<< x << " is destructed" << endl; 
        }
    };
     
    int main(intargc, const char* argv[]) { 
        A a(10),//10 is constructed
        b(a); //Copy of 10 is constructed
        return 0; 
    }
    //10 is destructed 
    //10 is destructed 
    
    ```

14. ```c++
    int main()
    {
    	char str1[10]="aaaaaa";
    	char str2[10]="aaa";
    
    
    	char *p1 = str1;
    	char *p2 = str2;
    	int sum = 0;
    	while (*p1 != '\0')
    	{
    		if (*p1 == *p2)
    		{
    			p2++;
    			int i = 1;
    			while (p1[i] != '\0' && p1[i] == *p2)
    			{
    				i++; p2++;
    			}
    
    			if (*p2 == '\0')
    			{
    				sum++;
    				cout << p1 << endl;
    			}
    
    			p2 = str2;
    		}
    		p1++;
    	}
    
    	cout << sum << endl;
    	return 0;
    }
    
    //aaaaaa sum = 1
    //aaaaa sum = 2
    //aaaa sum = 3
    //aaa sum = 4
    //4
    
    ```

15. ```c++
    class FileErrors{  };
    class NonExist : public FileErrors {  };
    
    int main() {
        NonExist e;
        try {
            throw e;
        }
        catch (FileErrors& e) {
            cout << "FileErrors" << endl;
        }
        catch (NonExist& e) {
            cout << "NonExist" << endl;
        }
    return 0;
    }
    //FileErrors
    ```

16. ```c++
    class Person {
    private:
        string name;
    public:
    	virtual void printName () const {cout << name<<" ";}
        Person(string name) : name(name) {}
        //没有默认构造函数
    };
    
    class Student: public Person{
    private:
      string id;
    public:
        Student(string id, string name):id(id), Person(name){printName();}
        void printName() const {
            cout << id << " ";
            Person::printName();
        }
    };
    
    void Print(Person& p) {p.printName();}
    
    int main() {
        Student s("101", "Tommy");//101 Tommy
        Person& p1 = s;
        p1.printName();//101 Tommy
        Person p2 = s;
        p2.printName();//Tommy
        Print(s);//"101 Tommy"
        return 0;
    }
    
    ```



# 考点总结

1. 考察函数参数
   1. 引用类型传入函数的时候直接修改的是本来的值
   2. 非引用类型是按照值拷贝的方式
2. 动态绑定和静态绑定
   1. 父类* 变量 = new  子类 delete 变量
      1. new调用子类的构造函数
      2. delete调用父类的构造函数,但是如果父类的析构函数是虚函数，那么就用实际类型的析构函数就是子类的
   2. 父类& 变量 = 子类对象；
      1. 因为他只是一个引用，实际编译之后会变成他真正指向的内容
   3. 同名函数会覆盖，父类会继承子类全部的内容virtual的会重写，私有变量/函数会继承，但是无法访问
3. 构造函数
   1. 子类构造函数优先调用基类的构造函数
   2. 封闭类
      1. 如果没写复制构造函数的话，在执行复制构造函数体之前优先执行成员变量的函数体
      2. 如果手动实现复制构造函数的话，没有成员初始化列表的话，执行的就是成员对象的默认构造函数了复制构造函数，会自动加成员对象类的默认构造eg Enclosing(const Enclosing& e):member(){} 这个member()就是编译器自动给你加的
4. 常指针、常引用、常量成员函数
   1. 常量不初始化
   2. 常量指针改值
   3. 指针常量改指向 
   4. 不能把常量指针赋值给正常指针
   5. 常量成员函数不能修改对应的对象，但是可以改静态变量
   6. 常量对象不能改
5. 静态函数：静态函数不能访问非静态变量（不依赖对象）
6. 析构函数
   1. 多次析构
      1. 函数的局部变量在函数结束的时候会销毁
      2. 函数传进来的是一个经过浅拷贝的对象就会出现问题（没有重写复制构造函数，传入的是值不是引用，导致在函数内部就析构了）
      3. 函数内部栈上的东西只有一个值的这种可以返回不会影响，一但是一个空间，在函数结束的时候空间就被收回，返回的地方就是空的无效的
         1. 用static
         2. 用new 分配到堆上面
         3. 用容器
   2. delete析构的是静态绑定的，除非析构函数是虚函数
   3. delete[]
7. try catch 第一个匹配的catch后面的catch就不会在匹配了 类似if  else
8. 
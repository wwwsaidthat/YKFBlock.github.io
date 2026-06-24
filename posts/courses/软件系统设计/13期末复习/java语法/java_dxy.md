# Java

### PART 1	语法

#### 1.类型

​	``int``			32bit
​	``double``		  64bit
​	``boolean``		1bit
​	``char``		      16bit
​	``String``		  对象

#### 2.标准输入和输出

​	(1) 读取控制台内容最常见的方式 -> ``nextInt nextDouble nextLine``
​	(2) 双引号内部分直接被输出，加号连接输出内容

```java
Scanner in = new Scanner(System.in);

int price;
price = in.nextInt;
//输出
System.out.println("echo:" + in.nextLine());
//特殊的输出和C语言写法相同
System.out.printf("%.2f", sum);
```

#### 3.整行读入的``BufferedReader``

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();//特点：读一整行，包含空格
```

​	注：`Scanner.nextLine`和 `BufferedReader.readLine` 区别:
​		应用`Scanner`中`nextInt`不读取换行，后续用`nextLine`会有读取换行符的风险
​		`BufferedReader`有需要检测异常`IOException`

#### 4.切分字符串

​	由``BufferedReader``读入的整行字符串可能需要切割才会得到我们想要的结果
​	(1)根据不同的分隔符切分成字符串数组

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String line = br.readLine();
String[] parts = line.split(" ");  //按空格切分
String[] parts2 = line.split(","); //按逗号切分
```

​	(2)按行切分为Stream

```javascript
.lines();
.forEach(System.out::println);
```

#### 5.类型转换

​	(1) 从``String``类型到其他类型（``int double long``)

```java
int n = Integer.parseInt("42");			// -> 42
```

​	(2) 结合``BufferedReader``（要养成先用``.trim()``的习惯）

```java
int n = Integer.parseInt(br.readLine().trim());	//.trim()可以去除字符串前后空格
//读一行多个数
String[] tokens = br.readLine().split(" ");
int[] arr = new int[tokens.length];
for(int i = 0; i < tokens.length; i++){
    arr[i] = Integer.parseInt(tokens[i]);
}
```

​	(3)从数值到``String``

```java
String s1 = String.valueOf(42);		//适用面广 -> "42"
String s2 = Integer.toString(42);   // -> "42"
```

#### 6.instanceof

​	作用：判断一个对象是否是某个类的实例，返回boolean

#### 7.文本块

​	作用：简洁，所见即所得

#### 8.空白格处理

​	(1)判断空白``isBlank() isEmpty()``

```java
//判断是否为空串或纯空白
String ykf = "IAmYKFAndILoveDXY";
ykf.isEmpty();	//只含空格、换行、制表符返回false
ykf.isBlank();	//只含空格、换行、制表符返回true
```

​	(2)去除空白

```java
//strip()支持Unicode空白，trim()仅支持ASCII空白
s.strip();				//去除前后空白
s.stripLeading();		//去除前空白
s.stripTrailing();		//去除后空白
s.trim();				//去除前后空白
```

#### 9.格式化字符串

```java
"Hello, %s! You are %d years old.".formatted("YKF", 20);
// -> "Hello, YKF! You are 20 years old"
//等价于 String.format( , )
```

#### 10.for循环的遍历

​	(1)普遍循环写法为C语言风格
​	(2)for-each

```java
for(int i = 0; i < s.length; i++){}			//正向遍历
for(int i : arr){}							//for-each
for(int i = list.size(); i >= 0; i--){}		//反向遍历
```

#### 11.函数

​	(1)构成
```java
//访问修饰词  返回类型  方法名  参数列表
	static  double   area  (doubie width, double height){
        return width * height;	//函数体：逻辑实现	return语句
    }
```

​	(2)访问修饰符：``public private static``

#### 12.import与包

```java
package edu.nju.software.token;		//声明当前类属于哪个包

import java.until.ArrayList;		//导入单个类
import java.until.*;				//导入整个包
```

#### 13.数组Array

​	(1)声明数组``int[] arr``

​	(2)访问``arr.length``

```java
//以截断数组为例
static int[] sopyRange(int[] arr, int from, int to){
    int[] result = new int[to - from];
    for(int i = 0; i < result.length; i++){	//result.length是属性，不需要有括号
        result[i] = arr[from + i];
    }
    return result;
}
```

#### 14.String的常用方法

```java
s.length();			//s.length()是方法，要加括号
s.charAt(i);		
s.equals(other);	//比较字符串内容切记不要使用 ==
s.contains(sub);	//是否包含字串	"hello".contains("ell") -> true
s.split(sep);		//分割为数组
s.substring(i, j);	//截断[i, j)	
s.isEmpty();
s.indexOf(sub);		//查找子串位置	返回下标
s.toUpperCase();	//大写
```

#### 15.StringBuilder字符串拼接

​	性能最好的大量拼接

```java
StringBuilder sb = new StringBuilder();				//声明
for(String w : words){sb.append(w).append(" ");}	//方法为sb.append();
String result = sb.toString();
```

#### 16.线性数据结构概览

![image-20260414201113519](D:\typora_picture\image-20260414201113519.png)

​	如何选择？
​		按下标访问？ ——是 -> ArrayList
​					——否 -> 需要什么操作 ——后进先出	-> ArrayDeque(当Stack用)
​										 ——先进先出	-> ArrayDeque / LinkedList(当Queue用)
​										 ——两端增删	-> ArrayDeque(当Deque用)
​										 ——频繁头部增删->LinkedList

#### 17.ArrayList动态列表

​	(1)ArrayList = 长度可变的数组，来自``java.util.ArrayList``

​	(2)泛型只能包装类 如``Integer Double String``

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);		//将元素加到list最后
list.set(i, k);		//将k放到下标i
list.get(0);		//获取0元素的内容
list.remove(0);		//删除0元素内容，后边元素前移
list.size();		//获取长度
list.contains();	//是否包含	return: true/false
```

​	(3)删除元素的陷阱：使用``list.remove(i)``后边元素前移容易有漏网之鱼 —— 反向遍历解决

```java
for(int i = list.size() - 1; i >= 0; i--){
    if(list.get(i) == target)	list.remove(i);
}
```

​	(4)陷阱``remove(int)和 remove(Object)``

```java
//ArrayList中的remove有两个方法
list.remove(int index);		//删除对应下标
list.remove(Object obj);	//删除对应元素（对象）-> list.remove(Integer.valueOf(target))
//Integer.valueOf(target) -> 将数字变成Integer对象
```

#### 18.LinkedList双向链表

​	(1)head 和 tail，每个节点持有 prev 和 next 两个指针

​	(2)大多数ArrayList够用，头部频繁增删才会用LinkedList

```java
LinkedList<String> list = new LinkedList<>();
list.addFirst("A");		//[A]
list.addLast("B");		//[A, B]
list.add(1, "X");		//[A, X, B]
list.removeFirst();		//[X, B]
list.removeLast();		//[X]
list.get(0);			//"X" -> 下标访问时间复杂度O(n)而ArrayList是O(1)
```

​	(3)``LinkedList``同时实现``List Queue Deque``三个接口

```java
//作为Queue使用（先入先出）
Queue<String> queue = new LinkedList<>();
queue.offer("A");	//入队（放在尾部）
queue.offer("B");	//入队（放在尾部）
queue.poll();		//出队（头部）-> "A"

//作为Deque使用
Deque<String> deque = new LinkedList<>();
deque.offerFirst("A");	//头部入队
deque.offerLast("B");	//尾部入队
deque.pollFirst();		//头部出队 -> "A"
deque.pollLadt();		//尾部出队 -> "B"
```

#### 19.Stack栈（后进先出）

```java
Stack<Integer> stack = new Stack<>();
stack.push(1);		//压入1
stack.push(2);		//压入2
stack.peek();		//查看栈顶 -> "2"
stack.pop();		//弹出
stack.isEmpty();	//false
stack.size();		//1
```

​	注：推荐用``Deque``替代``Stack``

```java
Deque<Integer> stack = new ArrayDeque<>();	//做栈使用ArrayDeque性能比LinkedList好
stack.push(1);
stack.pop();
```

#### 20.Deque双端队列

​	(1)``Deque``可以同时充当``Stack Queue``

​		``Stack``	``push`` = ``offerFirst``	``pop`` = ``pollFirst``
​		``Queue``	``offer`` = ``offerLast``	``poll`` = ``pollFirst``

#### 21.ArrayDeque使用方式和性能

```java
Deque<Integer> stack = new ArrayDeque<>();	//调用方法和LinkedList声明的没差别
Queue<String> queue = new ArrayDeque<>();
```

​	(1)优点
![image-20260414205514229](D:\typora_picture\image-20260414205514229.png)

​	(2)注意：	不允许null
​				非线程安全
​				不支持下标访问

#### 22.HashMap

​	(1)用``ArrayList``查找时``.get(i)``时间复杂度O(n)，用``HashMap HashSet``时间复杂度O(1)

​	(2)常见的键值映射

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("vjf1", 83);	//插入键值对
map.put("vjf2", 95);
map.put("vjf1", 100);	//相同key覆盖旧值

map.get("vjf1");		//"100"
map.get("cpl");			//null
map.getOrfault("cpl", 0);//0 -> 如果key不存在，返回设定默认值而非null

map.containsKey("vjf1");
map.ContainsValue(100);
map.remove("vjf2");
map.size();
```

​	(3)遍历

```java
for(String key: map.keySet()){ System.out.println(key + "=" + map.get(key));}//所有键集合
for(Integer val: map.values()){ System.out.println(val);}//所有值集合
for(Map.Entry<String, Integer> e
   	: map.entrySet()){//键值对集合
    System.out.println(e.getKey() + "=" + e.getValue());
}
```

​	(4)``HashSet``是``HashMap``只有key没有value的版本

#### 23.TreeMap有序Map

​	(1)会自动按照键升序排列的``HashMap``

​	(2)额外支持``firstKey() lastKey()``

```java
TreeMap<String, Integer> tm = new TreeMap<>();
tm.put("banana", 2);
tm.put("apple", 3);
tm.put("cherry", 1);

for(String k: tm.keySet()){	System.out.println(k);}	//apple -> banana -> cherry
```

#### 24.LinkedHashMap保持插入顺序

​	(1)以HashMap为底层，同时维护一个双向链表

​	(2)保证顺序按照插入顺序
```java
linkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
lhm.put("banana", 2);
lhm.put("apple", 3);
lhm.put("cherry", 1);

for(String k: lhm.keySet()){	System.out.println(k);}	//apple -> banana -> cherry
```

<img src="D:\typora_picture\image-20260415144442224.png" alt="image-20260415144442224" style="float:left;zoom:80%;" />

#### 25.File文件元信息操作

```java
File file = new File("data.txt");

file.exists();				//是否存在
file.isFile();				//是否为普通文件
file.isDirectory();			//是否为目录
file.length();				//文件大小（字节）
file.getName();				//文件名
file.getAbsolutelyPath();	//绝对路径
file.lastModefied();		//最后修改时间
file.createNewFile();		//创建新文件
file.delete();				//删除
file.mkdirs();				//创建多级目录
file.listFiles();			//列出字文件
```

### PART 2	Java vs C 的关键差异


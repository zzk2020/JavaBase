# Java高并发程序设计
## 并行
### 为什么需要并行
#### 并行计算还出于业务模型的需要
- 并不是为了提高系统性能，而是确实在业务上需要多个执行单元。
- 比如HTTP服务器，为了每一个Socket连接新建一个处理线程
- 让不同线程承担不同的业务工作
- 简化任务调度

#### 性能
- 进程销毁开销太大

#### 反对意见
##### LinusTorvalds
- 忘掉那该死的并行吧！
- 需要有多么奇葩的想象力才能想象出并行计算的用武之地？
- 并行计算只有在图像处理和服务端编程2个领域可以使用，并且它在这2个领域确实有着大量广泛使用，但在其他任务地方，并行计算毫无建树！

#### 摩尔定律的失效
- 预计18个月会将芯片的性能提高一倍
- IntelCEOBarret单膝下跪对4GHz感到抱歉

![image-20200607111038983](.\md-images\image-20200607111038983.png)

 - 在2004年秋季，Intel宣布彻底取消4GHz计划

- 虽然现在已经有了4GHz的芯片，但频率极限已经逼近
#### 顶级计算机科学家唐纳德·尔文·克努斯
- 在我看来，这种现象（并发）或多或少是由于硬件设计者已经无计可施了导致的，他们将摩尔定律失效的责任推脱给软件开发者

### 几个重要的概念

#### 同步（synchronous）和异步（asynchronous）

![image-20200607111241692](.\md-images\image-20200607111241692.png)

#### 并发（Concurrentcy）和并行（Parallelism）

![image-20200607111330802](.\md-images\image-20200607111330802.png)

#### 临界区
- 临界区用来表示一种公共资源或者说是共享数据，可以被多个线程使用。但是每一次，只能有一个线程使用它，一旦临界区资源被占用，其他线程要箱使用这个资源，就必须等待。

![image-20200607111718513](.\md-images\image-20200607111718513.png)

#### 阻塞（Blocking）和非阻塞（Non-Blocking）
- 阻塞和非阻塞通常用来形容多线程间的相互影响。比如一个线程占用了临近区资源，那么其它所有需要这个资源的线程就必须在这个临界区中进行等待，等待会导致线程挂起。这种情况就是阻塞。此时，如果占用资源的线程一直不愿意释放资源，那么其它所有阻塞在这个临界区的线程都不能工作。
- 非阻塞允许多个线程同时进入临界区

#### 死锁（Deadlock）、饥饿（Starvation）和活锁（Livelock）

![image-20200607112136006](.\md-images\image-20200607112136006.png)

#### 并行的级别
阻塞、无障碍、无锁、无等待，后三种属于非阻塞

##### 阻塞
- 一个线程进入临界区后，其它线程必须等待
##### 无障碍（Obstruction-Free）宽进严出，不断重试
- 无障碍是一种最弱的非阻塞调度
- 自由出入临界区
- 无竞争时，有限步内完成操作
- 有竞争时，回滚数据
##### 无锁（Lock-Free）
- 是无障碍的
- 保证有一个线程可以胜出
```java
while(!atomicVar.compareAndSet(localVar, localVar+1)) {
    localVar = atomicVar.get();
}
```

##### 无等待（Wait-Free）
- 无锁的
- 要求所有的线程都必须在有限步内完成
- 无饥饿的

### 2个重要的定理
#### Amdahl定律（阿姆达尔定律）

![image-20200607113523319](.\md-images\image-20200607113523319.png)

#### Gustafson定律（古斯塔夫森定律）

![image-20200607113756696](.\md-images\image-20200607113756696.png)


## 多线程
### 什么是线程
- 线程是进程内的可执行单元
- 进程上下文切换时非常重量级操作
### 线程的基本操作

![image-20200607115401394](.\md-images\image-20200607115401394.png)**

#### 新建线程
```java
Thread t1 = new Thread();
tl.start();// 开启新线程，在新线程中执行
```

```java
Thread t1 = new Thread();
tl.run(); // 不能开启线程，在当前调用线程执行
```

```java
// Thread.run()的实现
// target时Runnable接口
public void run() {
    if(target != null) {
        target.run();
    }
}
```


```java
// 重写run()方法
Thread t1 = new Thread() {
    @Override
    public void run() {
        System.out.println("Hello, I am t1.");
    }
};
tl.start();
```

```java
// 传一个target实例
Thread t1 = new Thread(new CreateThread());
tl.start();
```

#### 终止线程
- Thread.stop()不推荐使用。它会释放所有monitor锁
> 记录1： ID=1, NAME=小明
> 记录2： ID=2, NAME=小王

![image-20200607120740840](.\md-images\image-20200607120740840.png)

#### 中断线程
```java
public void Thread.interrupt() // 中断线程，设置中断标志位
public boolean Thread.isInterrupted() // 判断释放中断，查看中断标志位
public static boolean Thread.interrupted() // 判断释放中断，并清除中断标志位
```
```java
public void run() {
    while(true) {
        Thread.yield();
    }
}
// 针对上面这个实现来说，下面的代码是不起作用的
t1.interrupt();
```
```java
// 优雅的实现
public void run() {
    while(true) {
        if(Thread.currentThread().isInterrupted()) {
            System.out,println("Interrupt!")
            break;
        }
        Thread.yield();
    }
}
```
#### public static native void sleep(long millis) throws InterruptedException

```java
// 优雅的实现
public void run() {
    while(true) {
        if(Thread.currentThread().isInterrupted()) {
            System.out,println("Interrupt!")
            break;
        }
        
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted When Sleep.");
            // 设置中断标志位，抛出异常后会清除中断标志位
            Thread.currentThread().interrupt();
        }
        
        Thread.yield();
    }
}
```

#### 挂起（suspend）和继续执行（resume）线程
- suspend()不会释放锁
- 如果加锁发生在resume()之前，则死锁发生

![image-20200607122847276](.\md-images\image-20200607122847276.png)

> 住：jps命令
> jstack 线程ID

#### 等待线程结束（join）和谦让（yield）
> yield() 释放当前占用的资源，让其它线程执行，但是后续自己还会抢占资源的

```java
public final void join() throws InterruptedException
public final synchronized void join(long millis) throws InterruptedException
```
```java
// join的本质
while(isAlive()) {
    wait(0);
}
// 线程执行完毕后，JVM会调用notifyAll()
```
> 不要在Thread实例上使用wait()和notify()方法

### 守护进程
- 在后台默默地完成一些系统性的服务，比如垃圾回收线程、JIT线程就可以理解为守护线程
- 当一个Java应用内，只有守护进程时，Java虚拟机就会自然退出
```java
Thread t = new DaemonThread();
t.setDaemon(true); // 设置为守护进程， 必须在start方法前调用
t.start();
```

### 线程优先级
```java
public final static int MIN_PRIORITY = 1;
public final static int NORM_PRIORITY = 5;
public final static int MAX_PRIORITY = 10;
```
```java
Thread high = new HightPriority();
Thread low = new LowPriority();
high.setPriority(Thread.MAX_PRIORITY);
low.setPriority(Thread.MIN_PRIORITY);
low.start();
high.start();
```
> 高优先级的线程更容易在竞争中获胜，概率问题

### 基本的线程同步操作
- synchronized
	- 指定加锁对象：对给定对象加锁，进入同步代码前要获得给定对象的锁。
	- 直接作用与实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
	- 直接作用与静态方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁。
- Object.wait()、Object.notify()
> 调用方法前要获取 Object monitor

![image-20200607130047436](.\md-images\image-20200607130047436.png)

> 唤醒当前对象的所有线程上

## Java内存模型和线程安全
### 原子性
- 原子性是指一个操作是不可中断的。即使是在多线程一起执行的时候，一个操作一旦开始，就不会被其它线程干扰。
> i++是原子操作吗？ 不是

### 有序性
- 在并发时，程序的执行可能就会出现乱序
```java
class OrderExample {
	int a = 0;
	boolean flag = false;
	public void writer() {
		a = 1;
		flag = true;
	}
	public void reader() {
		if(flag) {
			int i = a + 1;
		}
	}
}
```

![image-20200607150525838](.\md-images\image-20200607150525838.png)

> 多线程时，实际执行不一定时按照白那些顺序

- 一条指令的执行是可以分为很多步骤的
	- 取指 IF
	
	- 译码和取寄存器操作数 ID
	
	- 执行或者有效地址计算 EX
	
	- 存储器访问 MEM
	
	- 写回WB
	

![image-20200607151146235](.\md-images\image-20200607151146235.png)

![image-20200607151552000](.\md-images\image-20200607151552000.png)

![image-20200607151743630](.\md-images\image-20200607151743630.png)

![image-20200607151845858](.\md-images\image-20200607151845858.png)

### 可见性
- 可见性是指当一个线程修改了某一个共享变量的值，其他线程是否能够立即知道这个修改。
	- 编译器优化
	- 硬件优化（如写吸收，批操作）

![image-20200607152126701](.\md-images\image-20200607152126701.png)

- Java虚拟机层面的可见性
	- http://hushi55.github.io/2015/01/05/volatile-assembly

- -server模式运行上述代码，永远不会停止

![image-20200607153025112](.\md-images\image-20200607153025112.png)

![image-20200607153140834](.\md-images\image-20200607153140834.png)


### Happen-Before规则（先行发生）
- 程序顺序原则：一个线程内保证语义的穿行行
```java
a = 1;
b = a + 1;
```

- volatile规则：volatile变量的写，先发生于读，这保证了volatile变量的可见性
- 锁规则：解锁（unlock）必然发生在随后的加锁（lock）前
- 传递性：A先于B，B先于C，那么A必然先于C
- 线程的start()方法先于它的每一个动作
- 线程的所有操作先于线程的终结（Thread.join()）
- 线程的中断（interrupt()）先于被中断线程的代码
- 对象的构造函数执行结束先于finalize()方法


### 线程安全的概念
- 指某个函数、函数库在多线程中被调用时，能够正确地处理各个线程的局部变量，使程序功能正确完成。
> i++在多线程下访问的情况

![image-20200607154317930](.\md-images\image-20200607154317930.png)

```java
public class AccountingSync implements Runnable {
	static AccountingSync instance = new AccountingSync();
	static int i = 0;
	
	@Override
	public void run() {
		for(int j=0; j<10000000; j++) {
			synchronized(instance) {
				i++;
			}
		}
	}
}
```

## 无锁
### 无锁类的原理详解
#### CAS（Compare And Swap）
> CAS算法的过程是这样：它包括3个参数CAS(V, E, N)，V表示要更新的变量，E表示预期值，N表示新值。仅当V值定于E值时，才会将V的值设为N，如果V值和E值不同，则说明已经有其它线程做了更新，则当前线程什么都不做。最后，CAS返回当前V的真实值。CAS操作时抱着乐观的态度进行的，它总是认为自己可以成功完成操作。当多个线程同时使用CAS操作一个变量时，只有一个会胜出，并成功更新，其余均会失败，失败的线程不会被挂起，仅是被告知失败，并且允许再次尝试，当然也允许失败的线程放弃操作。基于这样的原理，CAS操作即使没有锁，也可以发现其它线程对当前线程的干扰，并进行恰当的处理。

#### CPU指令
- 虽然CAS有多个步骤，但是在CPU层面是原子操作，一条CPU指令执行的
```java
cmpxchg
/*
accumulator = AL, AX or EAX, depending on whether a byte, word, or doubleword comparison is being performed
*/
if(accumulator == Destination) {
	ZF = 1;
	Destination = Source;
} else {
	ZF = 0;
	accumulator = Destination;
}
```
### 无锁类的使用
#### AtomicInteger
##### 概述
Number
##### 主要接口
```java
public final int get() // 取得当前值
public final void set(int var1) // 设置当前值
public final int getAndSet(int var1) // 设置新值，并返回旧值
public final boolean compareAndSet(int expect, int u) // 如果当前值为expect,则设置为u
public final int getAndIncrement() // 当前值加1，返回旧值
public final int getAndDecrement() // 当前值减1，返回旧值
public final int getAndAdd(int var1) // 当前值加var1，返回旧值
public final int incrementAndGet() // 当前值加1，返回新值
public final int decrementAndGet() // 当前值减1，返回新值
public final int addAndGet(int var1) // 当前值加var1，返回新值
```
##### 主要接口的实现

#### Unsafe
##### 概述
非安全的操作，比如：
根据偏移量设置值
park()
底层的CAS操作
非公开的API，在不同版本的JDK中，可能有较大差异
##### 主要接口
```java
// 获取给定对象偏移量上的int值
public native int getInt(Object o, long offset);
// 设置给定对象偏移量上的int值
public native int putInt(Object o, long offset， int x);
// 获得字段在对象中的偏移量
public native long objectFieldOffset(Field f);
// 获得给定对象的int值，使用volatile语义
public native void putIntVolatile(Object o, long offset);
// 设置给定对象的int值，使用volatile语义
public native void getIntVolatile(Object o, long offset, int x);
// 和putIntVolatile()一样，但是它要求被操作字段就是volatile类型的
public native void putOrderedInt(Object o, long offset, int x);
```
#### AtomicReference
##### 概述
对引用进行修改
是一个模板类，抽象化了数据类型
##### 主要接口

#### AtomicStampedReference
##### 概述
有唯一标识的引用

![image-20200607163614333](.\md-images\image-20200607163614333.png)

##### 主要接口

#### AtomicIntegerArray
##### 概述
##### 主要接口
```java
// 获得数组第i个下标的元素
public final int get(int i)
// 获得数组的长度
public final int length()
// 将数组第i个下标对应的值设置为newValue，并返回就值
public final int getAndSet(int i, int newValue)
// 进行CAS操作，如果第i个下标对应的值等于expect，则更新为update，设置成功返回true
public final boolean compareAndSet(int i, int expect, int update)
// 将数组第i个下标对应的值加1
public final int getAndIncrement(int i)
// 将数组第i个下标对应的值减1
public final int getAndDecrement(int i)
// 将数组第i个下标对应的值加delta(delta可以是负数)
public final int getAndAdd(int delta)
```

> Integer.numberOfLeadingZeros(4)

![image-20200607165013645](.\md-images\image-20200607165013645.png)

#### AtomicIntegerFieldUpdater
##### 概述
让普通变量也享受原子操作
##### 主要接口
```java
AtomicIntegerFieldUpdater.newUpdater()
incrementAndGet()
```

##### 小说明
1. Updater只能修改它可见范围内的变量，因为Updater使用反射得到的变量，如果变量不可见，就会出错。比如如果score申明为private，就是不可见d。
2. 为了确保变量被正确的读取，它必须是volatile类型的。如果我们原有代码中为申明这个类型，那么简单的申明一下就行，这不会引起什么问题。
3. 由于CAS操作会通过对象实例中的偏移量直接进行赋值，因此，它不支持static字段（Unsafe.objectFieldOffset()不支持静态变量）。

### 无锁算法详解
Vector
LockFreeVector
## JDK并发包
### 各种同步控制工具的使用
#### ReentrantLock
- 可重入
- 可中断
- 可限时
- 公平锁
#### Condition
#### Semaphore
#### ReadWriteLock
##### 概述
- 读写分离锁
##### 访问情况
- 读-读不互斥：读读之间不阻塞
- 读-写互斥：读阻塞写，写也会阻塞读
- 写-写互斥：写写阻塞

#### CountDownLatch

![image-20200607174133262](.\md-images\image-20200607174133262.png)

#### CyclicBarrier
##### 概述
循环栅栏
Cyclic意为循环，也就是说这个计数器可以反复使用，比如，假设我们将计数器设置为10，那么一批10个线程后，计数器归零，然后接着凑齐下一批10个线程

![image-20200607174624353](.\md-images\image-20200607174624353.png)

#### LockSupport
##### 概述
提供线程阻塞原语
##### 主要接口
```java
LockSupport.park();
LockSupport.unpark(t1);
```
##### 与suspend()比较
不容易引起线程冻结
##### 中断响应


#### ReentrantLock的实现
##### CAS状态
##### 等待队列
##### park()

### 并发容器及典型源码分析
#### 集合包装
##### HashMap
```java
public static Map m = Collections.synchronizedMap(new HashMap())
```
##### List
##### Set
#### ConcurrentHashMap
高性能并发
#### BlockingQueue

![image-20200607181732792](.\md-images\image-20200607181732792.png)

> 适合生产者消费者模式

#### ConcurrentLinkedQueue
类似ConcurrentHashMap，都是高性能的

### 线程池的基本使用
#### 为什么需要线程池
- 原因
	- 线程的创建和销毁的代价比较高，并且是无效的
	- 创建和销毁和业务是没有关系的，希望CPU尽量用在业务上面而不是创建和销毁上
	- 线程服用
#### JDK为我们提供了哪些支持

![image-20200607183312906](.\md-images\image-20200607183312906.png)


#### 线程池的使用
##### 线程池的种类
```java
newFixedThreadPool
newSingleThreadExecutor
newCachedThreadPool
newScheduledThreadPool
```
#### 线程池使用的小例子
##### 简单线程池
##### ScheduledThreadPool

### 扩展和增强线程池
##### 回调接口
##### 拒绝策略
##### 自定义ThreadFactory

### 线程池及其核心代码分析

### ForkJoin
#### 思想

![image-20200607190004178](.\md-images\image-20200607190004178.png)

#### 使用接口
- RecursiveAction
	- 无返回值
- RecursiveTask
	- 有返回值


#### 简单例子

#### 实现要素
- 工作窃取

![image-20200607191520449](.\md-images\image-20200607191520449.png)

![image-20200607191430596](.\md-images\image-20200607191430596.png)

## 并行设计模式
### 什么是设计模式

![image-20200607193350565](.\md-images\image-20200607193350565.png)

![image-20200607193517036](.\md-images\image-20200607193517036.png)





### 单例模式

![image-20200607193657422](.\md-images\image-20200607193657422.png)





![image-20200607193718637](.\md-images\image-20200607193718637.png)

![image-20200607193813874](.\md-images\image-20200607193813874.png)

![image-20200607193916708](.\md-images\image-20200607193916708.png)

![image-20200607193943808](.\md-images\image-20200607193943808.png)

### 不变模式

![image-20200607194054581](.\md-images\image-20200607194054581.png)

![image-20200607194150281](.\md-images\image-20200607194150281.png)

![image-20200607194327506](.\md-images\image-20200607194327506.png)







### Future模式

![image-20200607194523178](.\md-images\image-20200607194523178.png)

![image-20200607194733531](.\md-images\image-20200607194733531.png)

![image-20200607194840697](.\md-images\image-20200607194840697.png)

![image-20200607195005333](.\md-images\image-20200607195005333.png)

![image-20200607195048615](.\md-images\image-20200607195048615.png)

<img src=".\md-images\image-20200607195125475.png" alt="image-20200607195125475" style="zoom:80%;" />

![image-20200607195306717](.\md-images\image-20200607195306717.png)

![image-20200607195326506](.\md-images\image-20200607195326506.png)

![image-20200607195422726](.\md-images\image-20200607195422726.png)



### 生产者消费者

![image-20200607195450441](.\md-images\image-20200607195450441.png)













[TOC]

# 1、单一职责原则

单一职责原则（Simple Responsibility Principle，SRP）是指不要存在多于一个导致类变更的原因。即一个类只负责一项职责，应该仅有一个引起它变化的原因。

也就是一个类不要承担过多职责，如果多职责，这些职责耦合性就可能比较强，一个职责变化可能会削弱或者抑制这个类完成其它职责的能力。



# 2、开闭原则

**开闭原则**（Open-Closed Principle, OCP）是指 一个软件实体类、模块、函数应该**对扩展开放，对修改关闭** 。所谓的开闭，也正是对 ***扩展*** 和 ***修改***  两个行为的一个原则。强调的是 **用抽象构建框架，用实现扩展细节**。

也就是说，你在设计的时候要时刻考虑尽量让这个类是足够好的，写好之后就不要去修改了，如果新需求来了，我们增加一些类就完事了，原来的代码能不动就不动。为达到目的，需对系统进行抽象化设计（关键）。



## 传统方法示例

```java
/**
 * Java图书
 */
public class JavaBook {

    private long id;

    private String name;

    private double price;

    public JavaBook(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // 打折
    public double getDiscountPrice() {
        return this.getPrice() * 0.8;
    }
}
```



# 3、依赖倒置原则

依赖倒置原则（Dependence Inversion Principle，DIP）是指设计代码时，高层模块不应该依赖底层模块，二者应该依赖其抽象。抽象不应该依赖细节，细节应该依赖抽象。

依赖倒转的核心思想是：面向接口编程。层与层之间通过接口来访问。不要在类内部去new所依赖的对象，而是通过依赖关系传递到内部来。



依赖关系传递的三种方式：

* 接口传递
* 构造方法传递
* setter传递



注意事项和细节

* ​	底层模块（被依赖或者被调用的组件）尽量使用抽象类或者接口，或者两者都有，程序稳定性会更好。
* 变量声明的类型尽量是抽象类或者接口，这样我们的变量引用和实体对象间，就存在一个缓冲层，利用实现接口的实体类进行扩展和优化。
* 继承时遵循里氏替换原则。



## 传统方法示例

```java
/**
 * 图表支持展示折线图（LineChart）和饼图（PieChart）
 * 要想展示棒形图（BarChart）只能修改下面的代码
 */
public class Chart {

    public void displayLineChart() {
        System.out.println("Display LineChart");
    }

    public void displayPieChart() {
        System.out.println("Display PieChart");
    }
    
	// 后来加的
    public void displayBarChart() {
        System.out.println("Display BarChart");
    }

    public static void main(String[] args) {
        Chart chart = new Chart();
        chart.displayBarChart();
        chart.displayPieChart();
    }

}
```

## 依赖倒置的示例

> 接口IChart.java和接口的实现LineChart.java、PieChart.java、BarChart.java参照***开闭原则***。

```java
/**
 * 目前只有LineChart、PieChart和BarChart
 */
public class ChartManager {

    private IChart chart;
    
    public ChartManager() {
        
    }

    // 构造方法传递
    public ChartManager(IChart chart) {
        this.chart = chart;
    }

    public IChart getChart() {
        return chart;
    }

    // setter传递
    public void setChart(IChart chart) {
        this.chart = chart;
    }

    public void display() {
        chart.display();
    }

    // 接口传递
    public void display(IChart chart) {
        chart.display();
    }

    public static void main(String[] args) {
        // 1. 接口传递
        ChartManager chartManager = new ChartManager();
        chartManager.display(new LineChart());
        // 2. 构造方法传递
        ChartManager chartManager1 = new ChartManager(new PieChart());
        chartManager1.display();
        // 3. setter传递
        chartManager1.setChart(new BarChart());
        chartManager1.display();
    }

}
```





# 4、接口隔离原则

接口隔离原则（Interface Segregation Principle）是指用多个专门的接口，而不使用单一的总接口，客户端不应该依赖它不需要的接口。这个原则指导我们在设计接口时要注意一下几点：

1、一个类对一类的依赖应该建立在最小的接口之上；

2、一个接口不能过于臃肿；

3、使用多个专一功能的接口比使用一个总接口总要好，但不能过渡；

4、尽量细化接口，接口中的方法尽量少（不是越少越好，要适度）。

接口隔离原则符合我们常说的高内聚低耦合的设计思想，从而使得类具有很好的可读性、可扩展性和可维护性。



## 传统方法示例

```java
/**
 * 定义一个总接口
 */
public interface InterfaceA {

    void methodA();

    void methodB();

    void methodC();

    void methodD();

}
```

```java
/**
 * InterfaceA 的一个实现类A
 */
public class ImplClassA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplClassA.methodA() called");
    }

    @Override
    public void methodB() {
        System.out.println("ImplClassA.methodB() called");
    }

    @Override
    public void methodC() {
        System.out.println("ImplClassA.methodC() called");
    }

    @Override
    public void methodD() {
        System.out.println("ImplClassA.methodD() called");
    }
}
```

```java
/**
 * InterfaceA 的一个实现类B
 */
public class ImplClassB implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplClassB.methodA() called");
    }

    @Override
    public void methodB() {
        System.out.println("ImplClassB.methodB() called");
    }

    @Override
    public void methodC() {
        System.out.println("ImplClassB.methodC() called");
    }

    @Override
    public void methodD() {
        System.out.println("ImplClassB.methodD() called");
    }
}
```

```java
/**
 * 业务类A
 */
public class ClassA {

    private InterfaceA interfaceA;

    // 对于ClassA来说InterfaceA的实现类中的methodC和methodD方法是无用的
    // 对于ClassA来说InterfaceA的实现类中必须要实现methodC和methodD方法
    public ClassA(InterfaceA interfaceA) {
        this.interfaceA = interfaceA;
        this.interfaceA.methodA();
        this.interfaceA.methodB();
    }

    public static void main(String[] args) {
        InterfaceA interfaceA = new ImplClassA();
        ClassA classA = new ClassA(interfaceA);
    }
}
```

```java
/**
 * 业务类B
 */
public class ClassB {

    private InterfaceA interfaceA;

    // 对于ClassB来说InterfaceA的实现类中的methodA和methodB方法是无用的
    // 对于ClassB来说InterfaceA的实现类中必须要实现methodA和methodB方法
    public ClassB(InterfaceA interfaceA) {
        this.interfaceA = interfaceA;
        this.interfaceA.methodC();
        this.interfaceA.methodD();
    }

    public static void main(String[] args) {
        InterfaceA interfaceA = new ImplClassB();
        ClassB classB = new ClassB(interfaceA);
    }
}
```

## 接口隔离示例

```java
/**
 * 接口A
 */
public interface InterfaceA {

    void methodA();

    void methodB();

}
```

```java
/**
 * 接口B
 */
public interface InterfaceB {

    void methodC();

    void methodD();

}
```

```java
/**
 * InterfaceA的实现类
 */
public class ImplClassA implements InterfaceA {

    @Override
    public void methodA() {
        System.out.println("ImplClassA.methodA() called");
    }

    @Override
    public void methodB() {
        System.out.println("ImplClassA.methodB() called");
    }

}
```

```java
/**
 * InterfaceB的实现类
 */
public class ImplClassB implements InterfaceB {

    @Override
    public void methodC() {
        System.out.println("ImplClassB.methodC() called");
    }

    @Override
    public void methodD() {
        System.out.println("ImplClassB.methodD() called");
    }

}
```

```java
/**
 * 业务A
 */
public class ClassA {

    private InterfaceA interfaceA;

    public ClassA(InterfaceA interfaceA) {
        this.interfaceA = interfaceA;
        this.interfaceA.methodA();
        this.interfaceA.methodB();
    }

    public static void main(String[] args) {
        InterfaceA interfaceA = new ImplClassA();
        ClassA classA = new ClassA(interfaceA);
    }
}
```

```java
/**
 * 业务B
 */
public class ClassB {

    private InterfaceB interfaceB;

    public ClassB(InterfaceB interfaceB) {
        this.interfaceB = interfaceB;
        this.interfaceB.methodC();
        this.interfaceB.methodD();
    }

    public static void main(String[] args) {
        InterfaceB interfaceB = new ImplClassB();
        ClassB classB = new ClassB(interfaceB);
    }
}

```





## 开闭原则的示例

```java
/**
 * 商品接口
 */
public interface IGoods {

    long getId();

    String getName();

    double getPrice();

}
```

```java
/**
 * Java图书
 */
public class JavaBook implements IGoods {

    private long id;

    private String name;

    private double price;

    public JavaBook(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```

```java
// 打折
public class JavaBookDiscount extends JavaBook {

    public JavaBookDiscount(long id, String name, double price) {
        super(id, name, price);
    }

    public double getOriginPrice() {
        return super.getPrice();
    }

    public double getPrice() {
        return super.getPrice() * 0.6;
    }
}
```









# 5、里氏替换原则

里氏替换原则（Liskov Substitution Principle，LSP）是指如果对一个类型为T1的对象o1，都有类型T2的对象o2，使得以T1定义的多有程序P在所有的对象o1都替换成o2时，程序P的行为没有发生变化，那么类型T2是类型T1的子类型。也就是说所有引用基类的地方都必须能透明的使用其子类的对象。即子类继承于父类的方法，行为保持一致，不要去重写覆盖。

通俗的讲就是：**子类能扩展父类的功能，但不能改变父类原有的功能**。

它包含以下四层含义：

* 子类可以实现父类的抽象方法，但是不能覆盖父类的非抽象方法。
* 子类中可以增加自己特有的方法。
* 当子类的方法重载父类的方法时，方法的前置条件（即方法的输入\入参）要比父类方法的输入参数更宽松。
* 当子类的方法实现父类的方法时（重写/重载或实现抽象方法），方法的后置条件（即方法的输出/返回值）要不父类更严格或相等。



**针对继承父类方法，又不得不重写父类方法的处理方案**

* 可以解除继承关系，而是通过聚合、组合方式来解决问题。
* 也可以让原有的父子继承关系解除，让不要重写的方法提取到一个公共基类（编程一个虚拟类），让需要重写的方法在原来的父子类中分别实现。



## 传统方法示例

> 子类重写父类的情形

```java
/**
 * 定义一个总接口
 */
public interface InterfaceA {

    void methodA();

    void methodB();

    void methodC();

    void methodD();

}
```

```java
/**
 * InterfaceA 的一个实现类
 */
public class ImplClassA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplClassA.methodA() called");
    }

    @Override
    public void methodB() {
        System.out.println("ImplClassA.methodB() called");
    }

    @Override
    public void methodC() {
        System.out.println("ImplClassA.methodC() called");
    }

    @Override
    public void methodD() {
        System.out.println("ImplClassA.methodD() called");
    }
}
```

```java

/**
 * 业务类A
 */
public class ClassA extends ImplClassA {

    public void methodA() {
        System.out.println("ImplClassA.methodA() called");
    }

    public void methodB() {
        System.out.println("ImplClassA.methodB() called");
    }

    public void methodC() {
        System.out.println("ClassA.methodC() called");
    }

    public void methodD() {
        System.out.println("ClassA.methodD() called");
    }

}
```



## 里氏替换的示例

```java
/**
 * 抽象类，公共的methodA和methodB在抽象类中实现，非公共的定义为抽象方法。
 */
public abstract class AbstractClassA {

    public void methodA() {
        System.out.println("AbstractClassA.methodA() called");
    }

    public void methodB() {
        System.out.println("AbstractClassA.methodB() called");
    }

    public abstract void methodC();

    public abstract void methodD();
}
```

```java
/**
 * 业务类A，实现类抽象方法methodC和methodD
 */
public class ClassA extends AbstractClassA {

    public void methodC() {
        System.out.println("ClassA.methodC() called");
    }

    public void methodD() {
        System.out.println("ClassA.methodD() called");
    }

}
```

```java
/**
 * 业务类B，实现类抽象方法methodC和methodD
 */
public class ClassB extends AbstractClassA {

    @Override
    public void methodC() {
        System.out.println("ClassB.methodC() called");
    }

    @Override
    public void methodD() {
        System.out.println("ClassB.methodC() called");
    }
}
```

```java
public static void main(String[] args) {
    ClassA classA = new ClassA();
    classA.methodA(); // 此处调用的是AbstractClassA的methodA
    classA.methodB(); // 此处调用的是AbstractClassA的methodB
    classA.methodC(); 
    classA.methodD();

    ClassB classB = new ClassB();
    classB.methodA(); // 此处调用的是AbstractClassA的methodA
    classB.methodB(); // 此处调用的是AbstractClassA的methodB
    classB.methodC();
    classB.methodD();
}
```



# 6、迪米特法则（最少知道原则）

迪米特法则（Low of Demeter，LoD）是指一个对象应该对其它对象保持最少的了解，有叫最少知道原则（Least Knowledge Principle，LKP），尽量降低类与类之间的耦合。

对象和对象之间应该使用尽可能少的方法来关联，避免千丝万缕的关系。

迪米特法则主要强调只和直接朋友通信，且直接朋友也只提供public方法，不暴露内部逻辑。

关于直接朋友：任何两个对象之间都有耦合关系，只要有耦合关系就是朋友。耦合的方式有很多：**依赖**、**关联**、**组合**、**聚合**等。其中出现在**成员变量、方法参数、方法返回值中的类称为直接朋友**。出现在方法中的局部变量中的类不是直接朋友。（陌生类最好不要以局部变量的形式出现在类的内部）





# 7、合成复用原则

合成复用原则（Composite/Aggregate Reuse Principle，CARP）是指尽量使用对象组合（has-a）/聚合（contanis-a），而不是继承关系达到软件复用的目的。可以使系统更加灵活，降低类与类之间的耦合度，一个类的变化对其它类造成的影响相对较少。

继承我们叫做白箱复用，相当于把所有的实现细节暴露给子类。

组合/集合也称为黑箱复用，对类以为的对象是无法获取到实现细节的。





**参考**

1. [Java设计模式学习笔记---七大设计原则](https://blog.csdn.net/chao430/article/details/88708753)
2. [java设计模式 七大设计原则](https://blog.csdn.net/qq_36723759/article/details/104090485)
3. [java七大设计原则](https://blog.csdn.net/weixin_30483013/article/details/95376118)
4. [java开发设计六大基本原则](https://blog.csdn.net/IT_faquir/article/details/65938996)


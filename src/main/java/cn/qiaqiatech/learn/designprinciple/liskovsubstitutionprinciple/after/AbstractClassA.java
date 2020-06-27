package cn.qiaqiatech.learn.designprinciple.liskovsubstitutionprinciple.after;

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

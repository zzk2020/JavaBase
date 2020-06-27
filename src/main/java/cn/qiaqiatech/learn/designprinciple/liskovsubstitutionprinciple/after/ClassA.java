package cn.qiaqiatech.learn.designprinciple.liskovsubstitutionprinciple.after;

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

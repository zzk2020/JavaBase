package cn.qiaqiatech.learn.designprinciple.liskovsubstitutionprinciple.after;

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
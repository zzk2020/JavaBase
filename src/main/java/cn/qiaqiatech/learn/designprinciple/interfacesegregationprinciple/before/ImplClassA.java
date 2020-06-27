package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.before;

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

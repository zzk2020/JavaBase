package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.before;

/**
 * InterfaceA 的一个实现类
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

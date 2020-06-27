package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.after;

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

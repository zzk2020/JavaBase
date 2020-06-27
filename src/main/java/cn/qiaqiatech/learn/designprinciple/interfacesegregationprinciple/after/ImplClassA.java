package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.after;

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

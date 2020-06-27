package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.before;

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

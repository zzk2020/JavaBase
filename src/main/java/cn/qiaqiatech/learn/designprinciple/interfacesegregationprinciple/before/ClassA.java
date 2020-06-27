package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.before;

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

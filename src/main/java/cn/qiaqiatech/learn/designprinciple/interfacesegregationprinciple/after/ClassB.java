package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.after;

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

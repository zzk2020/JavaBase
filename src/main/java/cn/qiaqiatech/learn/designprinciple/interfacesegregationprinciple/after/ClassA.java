package cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.after;

/**
 * 业务A
 */
public class ClassA {

    private InterfaceA interfaceA;

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

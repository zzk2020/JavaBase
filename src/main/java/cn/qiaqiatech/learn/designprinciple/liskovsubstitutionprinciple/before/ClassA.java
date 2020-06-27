package cn.qiaqiatech.learn.designprinciple.liskovsubstitutionprinciple.before;

import cn.qiaqiatech.learn.designprinciple.interfacesegregationprinciple.after.ClassB;

/**
 * 业务类A
 */
public class ClassA extends ImplClassA {

    public void methodA() {
        System.out.println("ImplClassA.methodA() called");
    }

    public void methodB() {
        System.out.println("ImplClassA.methodB() called");
    }

    public void methodC() {
        System.out.println("ClassA.methodC() called");
    }

    public void methodD() {
        System.out.println("ClassA.methodD() called");
    }

    public static void main(String[] args) {
        ImplClassA implClassA = new ImplClassA();
        implClassA.methodA();
        implClassA.methodB();
        implClassA.methodC();
        implClassA.methodD();
        ClassA classA = new ClassA();
        classA.methodA();
        classA.methodB();
        classA.methodC();
        classA.methodD();
    }
}

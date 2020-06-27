package cn.qiaqiatech.learn.designprinciple.liskovsubstitutionprinciple.after;

public class Test {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.methodA(); // 此处调用的是AbstractClassA的methodA
        classA.methodB(); // 此处调用的是AbstractClassA的methodB
        classA.methodC();
        classA.methodD();

        ClassB classB = new ClassB();
        classB.methodA(); // 此处调用的是AbstractClassA的methodA
        classB.methodB(); // 此处调用的是AbstractClassA的methodB
        classB.methodC();
        classB.methodD();
    }
}

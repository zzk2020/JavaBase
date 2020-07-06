package cn.qiaqiatech.learn.pattern.factory.factorymethod;

import cn.qiaqiatech.learn.pattern.factory.ICourse;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        ICourse course = factory.create();
        course.record();
    }
}

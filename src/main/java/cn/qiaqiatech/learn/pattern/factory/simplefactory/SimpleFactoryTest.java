package cn.qiaqiatech.learn.pattern.factory.simplefactory;

import cn.qiaqiatech.learn.pattern.factory.ICourse;
import cn.qiaqiatech.learn.pattern.factory.JavaCourse;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
//        ICourse course = courseFactory.create("python");
//        ICourse course = courseFactory.create("cn.qiaqiatech.learn.pattern.factory.JavaCourse");
        ICourse course = courseFactory.create(JavaCourse.class);
        course.record();
    }
}

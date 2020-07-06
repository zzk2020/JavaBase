package cn.qiaqiatech.learn.pattern.factory.factorymethod;

import cn.qiaqiatech.learn.pattern.factory.ICourse;
import cn.qiaqiatech.learn.pattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}

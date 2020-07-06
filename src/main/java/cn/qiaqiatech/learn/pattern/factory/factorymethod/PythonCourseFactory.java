package cn.qiaqiatech.learn.pattern.factory.factorymethod;

import cn.qiaqiatech.learn.pattern.factory.ICourse;
import cn.qiaqiatech.learn.pattern.factory.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}

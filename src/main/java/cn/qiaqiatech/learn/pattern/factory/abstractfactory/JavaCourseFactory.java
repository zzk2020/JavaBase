package cn.qiaqiatech.learn.pattern.factory.abstractfactory;

import cn.qiaqiatech.learn.pattern.factory.*;

public class JavaCourseFactory implements ICourseFactory{
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public INote createNode() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }

    @Override
    public ISource createSource() {
        return new JavaSource();
    }
}

package cn.qiaqiatech.learn.pattern.factory;

public class PythonCourse implements ICourse {
    @Override
    public void record() {
        System.out.printf("Python record");
    }
}

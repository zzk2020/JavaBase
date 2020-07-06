package cn.qiaqiatech.learn.pattern.factory;

public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.printf("Java Video");
    }
}

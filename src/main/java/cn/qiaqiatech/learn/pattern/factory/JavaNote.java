package cn.qiaqiatech.learn.pattern.factory;

public class JavaNote implements INote {
    @Override
    public void record() {
        System.out.printf("Java Note");
    }
}

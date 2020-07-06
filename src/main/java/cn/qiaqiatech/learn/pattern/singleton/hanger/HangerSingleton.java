package cn.qiaqiatech.learn.pattern.singleton.hanger;

public class HangerSingleton {

    private static final HangerSingleton instance = new HangerSingleton();

    private HangerSingleton() {}

    public static HangerSingleton getInstance() {
        return instance;
    }
}

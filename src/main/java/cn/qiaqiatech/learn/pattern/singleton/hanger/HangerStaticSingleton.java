package cn.qiaqiatech.learn.pattern.singleton.hanger;

public class HangerStaticSingleton {

    private static final HangerStaticSingleton instance;

    static {
        instance = new HangerStaticSingleton();
    }
    private HangerStaticSingleton() {}

    public static HangerStaticSingleton getInstance() {
        return instance;
    }
}

package cn.qiaqiatech.learn.pattern.singleton.seriable;

import java.io.Serializable;

public class SeriableSingleton implements Serializable {

    private static final SeriableSingleton instance = new SeriableSingleton();

    private SeriableSingleton() {}

    public static SeriableSingleton getInstance() {
        return instance;
    }

    // 重写readResolve方法，只不过是覆盖了反序列化出来的对象
    // 还是创建了2次，发生在JVM层面，相对来说比较安全
    // 之前反序列化的对象会被GC回收
    private Object readResolve() {
        return instance;
    }
}

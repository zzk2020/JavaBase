package cn.qiaqiatech.learn.pattern.singleton.lazy;

public class LazySimpleSingleton {

    private volatile static LazySimpleSingleton instance;

    private LazySimpleSingleton() {}

    // JDK1.6之后对synchronized性能优化了不少
    // 不可避免还是存在一定的性能问题
    public static synchronized LazySimpleSingleton getInstance() {
        if(instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}

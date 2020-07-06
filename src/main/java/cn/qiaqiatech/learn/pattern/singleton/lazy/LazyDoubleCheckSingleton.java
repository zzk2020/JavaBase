package cn.qiaqiatech.learn.pattern.singleton.lazy;

public class LazyDoubleCheckSingleton {

    private static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {}

    public static LazyDoubleCheckSingleton getInstance() {
        if(instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if(instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

package cn.qiaqiatech.learn.pattern.singleton.lazy;

// 全程没有用到synchronized
// 性能最优的写法
// 理解内部类的执行逻辑
public class LazyInnerClassSingleton {

    // 虽然构造方法私有化，但是逃不过反射的法眼
    private LazyInnerClassSingleton() {
        if(LazyHandler.LAZY != null) {
            throw new RuntimeException("不允许实例化多个实例");
        }
    }

    // 懒汉式单例
    // LazyHandler里面的逻辑需要等外部方法调用时才执行
    // 巧妙的利用了内部类的特性
    // JVM底层执行逻辑，完美地避免了线程安全问题
    public static LazyInnerClassSingleton getInstance() {
        return LazyHandler.LAZY;
    }

    private static class LazyHandler {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}

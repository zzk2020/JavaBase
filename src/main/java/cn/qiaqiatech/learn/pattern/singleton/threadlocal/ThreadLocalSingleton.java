package cn.qiaqiatech.learn.pattern.singleton.threadlocal;

public class ThreadLocalSingleton {
    private static ThreadLocal<ThreadLocalSingleton> threadLocalInstance = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    public static ThreadLocalSingleton getInstance() {
        return threadLocalInstance.get();
    }
}

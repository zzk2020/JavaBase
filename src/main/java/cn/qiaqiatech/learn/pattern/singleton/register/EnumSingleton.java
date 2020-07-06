package cn.qiaqiatech.learn.pattern.singleton.register;

// JVM层面保证了反序列化和反射
public enum EnumSingleton {
    INSTANCE;

    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}

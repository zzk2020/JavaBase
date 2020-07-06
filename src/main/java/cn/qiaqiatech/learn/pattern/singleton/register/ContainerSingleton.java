package cn.qiaqiatech.learn.pattern.singleton.register;

import java.io.ObjectInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    private  ContainerSingleton() {
    }

    public static Object getBean(String className) {
        if(!ioc.containsKey(className)) {
            Object obj = null;
            try {
                obj = Class.forName(className).newInstance();
                ioc.put(className, obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }
        return ioc.get(className);
    }
}

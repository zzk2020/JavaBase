package cn.qiaqiatech.learn.pattern.singleton.lazy;

import cn.qiaqiatech.learn.pattern.singleton.register.EnumSingleton;
import cn.qiaqiatech.learn.pattern.singleton.seriable.SeriableSingleton;

import java.io.*;

public class EnumSingletonTest {
    public static void main(String[] args) {
        EnumSingleton s1 = null;
        EnumSingleton s2 = EnumSingleton.getInstance();
        s2.setData(new Object());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (EnumSingleton) ois.readObject();
            ois.close();
            fis.close();

            System.out.println(s1.getData());
            System.out.println(s2.getData());
            System.out.println(s1.getData() == s2.getData());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

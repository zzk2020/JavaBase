package cn.qiaqiatech.learn.delegate.simple;

/**
 * @author zzk
 */
public class DelegateTest {

    public static void main(String[] args) {
        new Boss().command("架构", new Leader());
    }

}

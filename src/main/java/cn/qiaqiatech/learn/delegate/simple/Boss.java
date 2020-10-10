package cn.qiaqiatech.learn.delegate.simple;

/**
 * @author zzk
 */
public class Boss {

    public void command(String commond, Leader leader) {
        leader.doing(commond);
    }


}

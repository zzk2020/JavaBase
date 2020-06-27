package cn.qiaqiatech.learn.designprinciple.dependenceinversionprinciple.after;

/**
 * 棒形图
 */
public class BarChart implements IChart {
    @Override
    public void display() {
        System.out.println("BarChart");
    }
}

package cn.qiaqiatech.learn.designprinciple.dependenceinversionprinciple.after;

/**
 * 折线图
 */
public class LineChart implements IChart {
    @Override
    public void display() {
        System.out.println("LineChart");
    }
}

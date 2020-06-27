package cn.qiaqiatech.learn.designprinciple.dependenceinversionprinciple.after;

/**
 * 饼图
 */
public class PieChart implements IChart {
    @Override
    public void display() {
        System.out.println("PieChart");
    }
}

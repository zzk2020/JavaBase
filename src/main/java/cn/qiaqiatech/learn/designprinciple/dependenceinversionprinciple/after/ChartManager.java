package cn.qiaqiatech.learn.designprinciple.dependenceinversionprinciple.after;

/**
 * 目前只有LineChart和PieChart
 * 如果想要棒形图，只需要新建一个BarChart即可，不影响LineChart和PieChart
 */
public class ChartManager {

    private IChart chart;
    public ChartManager() {

    }

    // 构造方法传递
    public ChartManager(IChart chart) {
        this.chart = chart;
    }

    public IChart getChart() {
        return chart;
    }

    // setter传递
    public void setChart(IChart chart) {
        this.chart = chart;
    }

    public void display() {
        chart.display();
    }

    // 接口传递
    public void display(IChart chart) {
        chart.display();
    }

    public static void main(String[] args) {
        // 1. 接口传递
        ChartManager chartManager = new ChartManager();
        chartManager.display(new LineChart());
        // 2. 构造方法传递
        ChartManager chartManager1 = new ChartManager(new PieChart());
        chartManager1.display();
        // 3. setter传递
        chartManager1.setChart(new BarChart());
        chartManager1.display();
    }

}

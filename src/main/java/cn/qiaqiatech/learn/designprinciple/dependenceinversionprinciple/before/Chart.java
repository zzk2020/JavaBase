package cn.qiaqiatech.learn.designprinciple.dependenceinversionprinciple.before;

/**
 * 图表支持展示折线图（LineChart）和饼图（PieChart）
 * 要想展示棒形图（BarChart）只能修改下面的代码
 */
public class Chart {

    public void displayLineChart() {
        System.out.println("Display LineChart");
    }

    public void displayPieChart() {
        System.out.println("Display PieChart");
    }

    public void displayBarChart() {
        System.out.println("Display BarChart");
    }

    public static void main(String[] args) {
        Chart chart = new Chart();
        chart.displayBarChart();
        chart.displayPieChart();
    }

}

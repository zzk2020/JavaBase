package cn.qiaqiatech.learn.designprinciple.opencloseprinciple.after;

// 打折
public class JavaBookDiscount extends JavaBook {

    public JavaBookDiscount(long id, String name, double price) {
        super(id, name, price);
    }

    public double getOriginPrice() {
        return super.getPrice();
    }

    public double getPrice() {
        return super.getPrice() * 0.6;
    }
}

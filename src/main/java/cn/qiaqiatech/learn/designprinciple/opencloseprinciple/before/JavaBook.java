package cn.qiaqiatech.learn.designprinciple.opencloseprinciple.before;

/**
 * Java图书
 */
public class JavaBook {

    private long id;

    private String name;

    private double price;

    public JavaBook(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // 打折
    public double getDiscountPrice() {
        return this.getPrice() * 0.8;
    }
}

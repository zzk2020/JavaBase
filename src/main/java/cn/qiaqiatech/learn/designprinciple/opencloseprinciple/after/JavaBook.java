package cn.qiaqiatech.learn.designprinciple.opencloseprinciple.after;

/**
 * Java图书
 */
public class JavaBook implements IGoods {

    private long id;

    private String name;

    private double price;

    public JavaBook(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
}

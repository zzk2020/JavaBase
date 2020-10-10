package cn.qiaqiatech.thread;

public class Request {

    private String name;

    public Request(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

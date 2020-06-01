package cn.qiaqiatech.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Waitress implements Runnable {
    private CountDownLatch latch;

    private String name;

    public Waitress(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Random random = new Random();
            System.out.println(sdf.format(new Date()) + " " + name + "等待顾客");
            latch.await(3, TimeUnit.SECONDS);
            System.out.println(sdf.format(new Date()) + " " + name + "开始上菜");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cn.qiaqiatech.thread;

import java.util.concurrent.TimeUnit;

public class ThreadResetDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Before : " + Thread.currentThread().isInterrupted());
                    Thread.interrupted();
                    System.out.println("After : " + Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}

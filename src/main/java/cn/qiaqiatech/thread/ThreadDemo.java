package cn.qiaqiatech.thread;

import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {
        new Thread(()->{
            while (true) {
                synchronized (ThreadDemo.class) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Time_Waiting_Thread").start();

        new Thread(()->{
            while (true) {
                synchronized (ThreadDemo.class) {
                    try {
                        ThreadDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting_Thread").start();


        new Thread(new BlockedDemo(), "Blocked1_Thread").start();
        new Thread(new BlockedDemo(), "Blocked2_Thread").start();
    }

    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (BlockedDemo.class) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

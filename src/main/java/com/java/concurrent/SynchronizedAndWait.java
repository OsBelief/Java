package com.java.concurrent;

public class SynchronizedAndWait {
    private volatile boolean done;

    public static void main(String[] args) {
        SynchronizedAndWait test = new SynchronizedAndWait();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.get();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    test.doNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

    public void get() {
        System.out.println("---获得锁");
        synchronized (this) {
            System.out.println("---进入临界区");
            while (isDone()) {
                System.out.println("---进入条件等待队列, 会释放锁");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---被唤醒, 重新被调度获得锁执行");
            }
            System.out.println("---进行满足条件时的业务处理");
        }
    }

    private boolean isDone() {
        System.out.println("---条件判断");
        return !done;
    }

    public void doNotify() {
        synchronized (this) {
            done = true;
            System.out.println("---唤醒条件等待队列中的线程-1");
            notifyAll();
            System.out.println("---唤醒条件等待队列中的线程-2");
        }
    }
}

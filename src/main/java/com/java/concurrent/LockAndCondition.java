package com.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndCondition {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile boolean done;

    public static void main(String[] args) {
        LockAndCondition lockAndCondition = new LockAndCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockAndCondition.get();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    lockAndCondition.doSignal();
//                    t1.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

    public void get() {
        System.out.println("---获得锁");
        lock.lock();
        System.out.println("---进入临界区");
        try {
            while (!done) {
                System.out.println("---进入条件等待队列");
                condition.await();
                System.out.println("---被唤醒, 重新被调度获得锁执行");
            }
            System.out.println("---进行满足条件时的业务处理");
        } catch (InterruptedException e) {
            System.out.println("---被中断了");
            e.printStackTrace();
        } finally {
            System.out.println("---释放锁");
            lock.unlock();
        }
    }

    public void doSignal() {
        lock.lock();
        try {
            done = true;
            System.out.println("---唤醒条件等待队列中的线程-1");
            condition.signal();
            System.out.println("---唤醒条件等待队列中的线程-2");
        } finally {
            lock.unlock();
        }
    }
}

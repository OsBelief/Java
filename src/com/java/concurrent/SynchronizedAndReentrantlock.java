package com.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;
/**
 * synchronized锁住的是对象，而不是代码
 * 一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 * 二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 * 三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
 * 四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
 * 
 * 不要同时使用ReentrantLock类与synchronized关键字锁定会修改同一个资源的不同方法,
 * 因为ReentrantLock与synchronized是不同的锁,不同的锁是不会互斥的,即不会起线程同步的作用,也要注意是否是同一个对象
 * @author yicha
 */
public class SynchronizedAndReentrantlock {
	
	ReentrantLock lock1 = new ReentrantLock();
	ReentrantLock lock2 = new ReentrantLock();
	
	public void method1(int x) {
		lock1.lock();
//		synchronized (this) {
			System.out.println("method1()," + "x = " + x);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
		lock1.unlock();
	}
	
	public void method2(int x) {
		lock1.lock();
//		synchronized (this) {
		System.out.println("method2()," + "x = " + x);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//	}
		lock1.unlock();
	}
	
	class SynchronizedTest1 implements Runnable {
		@Override
		public void run() {
			method1(1);
		}
	}
	class SynchronizedTest2 implements Runnable {
		@Override
		public void run() {
			method2(2);
		}
	}
	public static void main(String[] args) {
		SynchronizedAndReentrantlock sar1 = new SynchronizedAndReentrantlock();
		//SynchronizedAndReentrantlock sar2 = new SynchronizedAndReentrantlock();
		Thread t1 = new Thread(sar1.new SynchronizedTest1());
		Thread t2 = new Thread(sar1.new SynchronizedTest2());
		t1.start();
		t2.start();
	}
}


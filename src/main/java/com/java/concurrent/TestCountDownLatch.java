package com.java.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 在时序测试中,使用CountDownLatch来启动和停止线程
 * 闭锁的状态包括一个计数器，用来表示需要等待的事件数
 * countDown方法对计数器做减操作，表示一个事件已经发生了
 * await方法等待计数器达到零，此时所有需要等待的事件都已发生
 * 
 * 计算一个线程在n倍并发的情况下执行一个任务的时间
 * @author Belief
 */
public class TestCountDownLatch {
	public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);	//开始阀门
		final CountDownLatch endGate = new CountDownLatch(nThreads);	//结束阀门
		for(int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		long start = System.nanoTime();
		startGate.countDown();	//直到所有线程都做好准备时，才开始工作
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}
}

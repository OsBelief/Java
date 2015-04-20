package com.java.concurrent.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThread {
	private ScheduledThreadPoolExecutor sthe = null;

	public ScheduledThread() {
		this.sthe = new ScheduledThreadPoolExecutor(2);
	}

	Runnable task = new Runnable() {
		public void run() {
			System.out.println("Hello World!");
		}
	};

	public void schduleExe() {
		sthe.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);	// 执行延时任务和周期任务
	}

	public static void main(String[] args) {
		ScheduledThread st = new ScheduledThread();
		st.schduleExe();
	}
}

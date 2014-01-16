package com.java.concurrent.executor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	private Date initDate;
	private String name;
	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}
	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
		long duration = (long) (Math.random() * 10);
		System.out.printf("%s: Task %s: doing a task in %s duration\n", Thread.currentThread().getName(), name, duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: finished\n", Thread.currentThread().getName(), name, new Date());
	}
}

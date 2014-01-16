package com.java.concurrent.executor;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
	private ThreadPoolExecutor executor;
	public Server() {
//		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}
	public void executeTask(Task task) {
		System.out.println("Server: A new task has arrived");
		executor.execute(task);	// 提交任务
		System.out.println("线程池实际的线程数: " + executor.getPoolSize());
		System.out.println("正在执行任务的线程数: " + executor.getActiveCount());
		System.out.println("完成的任务数: " + executor.getCompletedTaskCount());
		
		System.out.println("已经提交给执行者的任务数: " + executor.getTaskCount());
	}
	public void endServer() {
		executor.shutdown();	// 不再接受新的任务的提交,正在执行的任务继续执行
	}
	public List<Runnable> endServerNow() {
		return executor.shutdownNow(); // 不再执行待处理的任务,并返回待处理的任务列表,正在执行的任务继续执行
	}
	public static void main(String[] args) {
		Server server = new Server();
		for(int i = 0; i < 100; i++) {
			Task task = new Task("Task" + i);
			server.executeTask(task);
		}
		server.endServer();
//		List<Runnable> list = server.endServerNow();
//		for(Runnable r : list) {
//			System.out.println("待处理任务: " + r);
//		}
		try {
			server.executor.awaitTermination(1, TimeUnit.DAYS);	// 阻塞调用线程,直到执行者的所有任务结束或超时
			System.out.println("所有的任务都完成了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

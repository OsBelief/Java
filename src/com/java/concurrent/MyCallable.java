package com.java.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable可返回结果的Runnable
 * Callable-Future组合使用
 * Future的get()方法,等待(阻塞)Callable任务的完成,并返回结果
 * @author Belief
 *
 */
public class MyCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(10000);
		//返回执行这个Callable任务的线程名
		return Thread.currentThread().getName();
	}
	public static void main(String[] args) {
		//线程池大小是10的执行器对象
		ExecutorService executor = Executors.newFixedThreadPool(10);
		//与Callable关联的Future对象集合
		List<Future<String>> list = new ArrayList<Future<String>>();
		
		Callable<String> callable = new MyCallable();
		for(int i = 0; i < 100; i++) {
			//为线程池添加Callable对象
			Future<String> future = executor.submit(callable);
			//使用Future获得返回值
			list.add(future);
		}
		for(Future<String> fut : list) {
			try {
				//打印future的返回值
				System.out.println(new Date() + "::" + fut.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			//停止执行器执行
			executor.shutdown();
		}
	}
}

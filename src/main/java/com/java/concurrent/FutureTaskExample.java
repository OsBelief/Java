package com.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * FutureTask用来执行异步任务
 * @author Belief
 *
 */
public class FutureTaskExample {
	public static void main(String[] args) {
		MyCallable2 callable1 = new MyCallable2(1000);
		MyCallable2 callable2 = new MyCallable2(2000);
		FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);
		executor.execute(futureTask2);
		
		while(true) {
			try {
				if(futureTask1.isDone() && futureTask2.isDone()) {
					System.out.println("Done");
					executor.shutdown();
					return;
				}
				if(!futureTask1.isDone()) {
					System.out.println("FutureTask1 output = " + futureTask1.get());
				}
				System.out.println("Waiting for FutureTask2 to complete");
				String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
				if(s != null) {
					System.out.println("FutureTask2 output = " + s);
				}
            } catch(TimeoutException e){
                //do nothing
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class MyCallable2 implements Callable<String> {
	private long waitTime;
	public MyCallable2(int timeInMillis) {
		this.waitTime = timeInMillis;
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);
		return Thread.currentThread().getName();
	}
	
}
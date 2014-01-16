package com.java.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Java多线程求和
 * 
 * @author yicha
 * 
 */
public class SumMuliThread {
	private ThreadPoolExecutor executor;
	private int start;
	private int end;
	private int nThreads;
	private List<FutureTask<Integer>> futureTasks;

	public SumMuliThread(int nThreads, int start, int end) {
		this.nThreads = nThreads;
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
		this.start = start;
		this.end = end;
		futureTasks = new ArrayList<FutureTask<Integer>>();
	}

	public void dispatchTask() {
		int length = (end - start) / nThreads;
		int p1 = start;
		int p2 = start;
		for (int i = 1; i < this.nThreads; i++) {
			p2 = p1 + length;
			System.out.println(p1 + " : " + p2);
			FutureTask<Integer> futureTask = new FutureTask<Integer>(
					new SubSumTask(p1, p2));
			executor.execute(futureTask);
			futureTasks.add(futureTask);
			p1 = p2 + 1;
		}
		System.out.println(p1 + " :: " + end);
		FutureTask<Integer> futureTask = new FutureTask<Integer>(
				new SubSumTask(p1, end));
		executor.execute(futureTask);
		futureTasks.add(futureTask);
	}
	public void endExecutor() {
		executor.shutdown();
	}

	public int CalcuteResult() {
		int result = 0;
		for (int i = 0; i < futureTasks.size(); i++) {
			FutureTask<Integer> futureTask = futureTasks.get(i);
			try {
				result += futureTask.get(); // 阻塞直到该任务完成
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		SumMuliThread sumMuliThread = new SumMuliThread(2, 0, 100000);
		sumMuliThread.dispatchTask();
		sumMuliThread.endExecutor();
		int result = sumMuliThread.CalcuteResult();
		System.out.println("和: " + result);
	}
}

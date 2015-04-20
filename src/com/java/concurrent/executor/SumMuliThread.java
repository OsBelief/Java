package com.java.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
	private List<SubSumTask> tasks;

	public SumMuliThread(int nThreads, int start, int end) {
		this.nThreads = nThreads;
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
		this.start = start;
		this.end = end;
		tasks = new ArrayList<SubSumTask>();
	}

	public void dispatchTask() {
		int length = (end - start) / nThreads;
		int p1 = start;
		int p2 = start;
		for (int i = 1; i < this.nThreads; i++) {
			p2 = p1 + length;
			SubSumTask task = new SubSumTask(p1, p2);
			tasks.add(task);
			p1 = p2 + 1;
		}
		SubSumTask task = new SubSumTask(p1, end);
		tasks.add(task);
	}

	public void endExecutor() {
		executor.shutdown();
	}

	public int CalcuteResult() {
		List<Future<Integer>> futures;
		int result = 0;
		try {
			futures = executor.invokeAll(tasks);	// 等待所有任务完成,并返回Callable集合所对应的Future集合
			for (int i = 0; i < futures.size(); i++) {
				Future<Integer> future = futures.get(i);
				result += future.get();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		SumMuliThread sumMuliThread = new SumMuliThread(1, 0, 1000000);
		sumMuliThread.dispatchTask();
		int result = sumMuliThread.CalcuteResult();
		System.out.println("和: " + result);
		sumMuliThread.endExecutor();
	}
}

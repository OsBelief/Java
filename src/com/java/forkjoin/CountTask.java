package com.java.forkjoin;

import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
//import java.util.concurrent.RecursiveTask;
/**
 * Fork/Join框架(分治算法的并行实现)
 * 递归
 * @author yicha
 *
 */
public class CountTask 
//extends RecursiveTask<Integer> 
{
//	private static final long serialVersionUID = 1L;
//	private static final int THRESHOLD = 100;
//	private final int start;
//	private final int end;
//	public CountTask(int start, int end) {
//		this.start = start;
//		this.end = end;
//		System.out.println("start: " + start + ",    end: " + end);
//	}
//
//	@Override
//	protected Integer compute() {
//		int sum = 0;
//		boolean cancompute = (end - start) <= THRESHOLD;
//		if(cancompute) {
//			for(int i = start; i <= end; i++) {
//				sum += i;	// 小任务直接计算
//			}
//		} else {
//			// 将大任务分解成两个子任务执行
//			int middle = (end - start) / 2;
//			CountTask firstTask = new CountTask(start, middle);
//			CountTask secondTask = new CountTask(middle + 1, end);
//			firstTask.fork();
//			secondTask.fork();
//			// 等待子任务执行完并合并结果
//			int firstResult = firstTask.join();
//			int secondResult = secondTask.join();
//			sum = firstResult + secondResult;
//		}
//		return sum;
//	}
//	public static void main(String[] args) {
//		ForkJoinPool forkJoinPool = new ForkJoinPool();
//		CountTask countTask = new CountTask(1, 230);
//		Future<Integer> future = forkJoinPool.submit(countTask);
//		try {
//			System.out.println("计算结果: " + future.get());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//	}
}

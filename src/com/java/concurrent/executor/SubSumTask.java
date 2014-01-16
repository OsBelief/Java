package com.java.concurrent.executor;

import java.util.concurrent.Callable;

public class SubSumTask implements Callable<Integer> {
	private int start;
	private int end;
	public SubSumTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public Integer call() throws Exception {
		synchronized (this) {
			int result = 0;
			for(int i = this.start; i <= this.end; i++) {
				result += i;
			}
			return result;
		}
	}
}

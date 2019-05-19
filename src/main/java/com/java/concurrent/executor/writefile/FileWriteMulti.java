package com.java.concurrent.executor.writefile;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileWriteMulti {
	private List<String> list = null;
	private ThreadPoolExecutor executor = null;
	private FileUtil file = null;

	public FileWriteMulti(String filepath, List<String> list, int nThreads) {
		this.list = list;
		this.executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(nThreads);
		file = new FileUtil(filepath);
	}

	public void dispatchWriteTask() {
		int n = this.executor.getPoolSize();
		int len = list.size() / n;
		int p1 = 0;
		int p2 = 0;
		int i = 0;
		for (i = 1; i < n; i++) {
			p2 = p1 + len;
			HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
			map.put(i, list.subList(p1, p2));
			WriteFileTask wft = new WriteFileTask(map, file);
			executor.execute(wft);
			p1 = p2 + 1;
		}
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		map.put(i, list.subList(p1, list.size() - 1));
		WriteFileTask wft = new WriteFileTask(map, file);
		executor.execute(wft);
	}
	
	public void checkIsDone() {
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void endExecutor() {
		executor.shutdown();
		file.closeFile();
	}
}

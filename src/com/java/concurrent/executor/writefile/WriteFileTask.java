package com.java.concurrent.executor.writefile;

import java.util.HashMap;
import java.util.List;

public class WriteFileTask implements Runnable {
	private HashMap<Integer, List<String>> map = null;
	private FileUtil file = null;
	public WriteFileTask( HashMap<Integer, List<String>> map, FileUtil file) {
		this.map = map;
		this.file = file;
	}
	@Override
	public void run() {
		file.writeFileLine(map);
	}
}

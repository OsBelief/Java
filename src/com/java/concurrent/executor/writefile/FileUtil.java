package com.java.concurrent.executor.writefile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;

public class FileUtil {
	public String filepath = null;
	public FileChannel fc = null;
	
	public FileUtil(String filepath) {
		this.filepath = filepath;
	}
	public void createFileMap() {
		try {
			fc = new FileOutputStream(filepath).getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void writeFileLine(HashMap<Integer, List<String>> hashMap) {
		
	}
	public void closeFile() {
		try {
			this.fc.force(true);
			this.fc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.java.concurrent.executor.writefile;

import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		list.add("李午俊");
		FileWriteMulti fwm =  new FileWriteMulti("E:\\test.txt", list, 3);
		fwm.dispatchWriteTask();
		fwm.checkIsDone();
		fwm.endExecutor();
	}
}

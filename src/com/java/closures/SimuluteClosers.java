package com.java.closures;

import java.io.File;
import java.io.FilenameFilter;
/**
 * Java模拟闭包
 * @author yicha
 *
 */
public class SimuluteClosers {
	public static void main(String[] args) {
		File file = new File(".");
		String[] files = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		});
		for(String s : files) {
			System.out.println("Java文件名: " + s);
		}
	}
}

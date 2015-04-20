package com.java.guara;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
/**
 * 多个键对应一个值
 * @author yicha
 *
 */
public class TableTest {
	public static void main(String[] args) {
		Table<Integer, Integer, String> t1 = HashBasedTable.create();
		t1.put(0, 0, "A");
		t1.put(0, 1, "B");
		t1.put(1, 0, "C");
		t1.put(1, 1, "D");
		
		Map<Integer, String> t2 = t1.row(0);
		System.out.println(t2.toString());
		
		Map<Integer, String> t3 = t1.column(1);
		System.out.println(t3.toString());
	}
}

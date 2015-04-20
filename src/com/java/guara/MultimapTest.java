package com.java.guara;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;

/**
 * Multimap将键映射到多个非null值
 * @author yicha
 *
 */
public class MultimapTest {
	public static void main(String[] args) {
		// 值为List的Multimap
		ListMultimap<String, String> t1 = ArrayListMultimap.create();
		SetMultimap<String, String> t2 = HashMultimap.create();
	}
}

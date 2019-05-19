package com.java.guara;

import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * BiMap键值对的双向映射(键和值都唯一)
 * @author yicha
 *
 */
public class BiMapTest {
	public static void main(String[] args) {
		BiMap<String, Integer> t1 = HashBiMap.create();
		t1.put("A", 12);
		Set<String> key = t1.keySet();
		System.out.println(t1.size() + " : " + key.size());
		
		BiMap<Integer, String> t2 = t1.inverse();	// 双向映射的两个map是同步的,改变其中的一个会影响另一个
		t2.put(15, "B");
		System.out.println(t1.size() + " : " + t2.size());
	}
}

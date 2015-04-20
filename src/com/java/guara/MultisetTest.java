package com.java.guara;

import java.util.Set;

import com.google.common.collect.BoundType;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
/**
 * google guara包中集合类的用法
 * @author yicha
 *
 */
public class MultisetTest {
	public static void main(String[] args) {
		/**
		 * 没有元素顺序限制的ArrayList<E>
		 * Map<E, Integer>，键为元素，值为计数
		 * 可用于统计信息，比如按不同条件统计字符串的个数等
		 */
		Multiset<String> t1 = HashMultiset.create();
		t1.add("hello", 5);
		t1.add("world", 10);
		System.out.println(t1.count("hello"));
		t1.setCount("hello", 12);
		System.out.println(t1.count("hello") + " : " + t1.size());
		Set<String> t2 = t1.elementSet();
		System.out.println(t2.size());
		/**
		 * 统计SortedMultiset中某一范围的信息
		 */
		SortedMultiset<Integer> t3 = TreeMultiset.create();
		t3.add(4, 5);
		t3.add(6, 10);
		int number = t3.subMultiset(0, BoundType.OPEN, 5, BoundType.CLOSED).size();
		System.out.println("SortedMultiset中0-5之间的数的个数: " + number);
	}
}

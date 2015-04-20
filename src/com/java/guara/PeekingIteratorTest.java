package com.java.guara;

import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;

public class PeekingIteratorTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("B","A", "A", "A", "B");
		List<String> result = Lists.newArrayList();
		PeekingIterator<String> iter = Iterators.peekingIterator(list
				.iterator());
		while (iter.hasNext()) {
			String current = iter.next();
			while (iter.hasNext() && iter.peek().equals(current)) {
				// 跳过重复的元素
				iter.next();
			}
			result.add(current);
		}
		for (String s : result) {
			System.out.println(s);
		}
	}
}

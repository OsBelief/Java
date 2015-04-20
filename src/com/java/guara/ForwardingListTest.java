package com.java.guara;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.ForwardingList;

/**
 * guara集合类的装饰者模式应用
 * @author yicha
 * 
 */
public class ForwardingListTest {
	public static void main(String[] args) {
		List<String> names = new ListWithDefault<String>(Arrays.asList("Alice",
				null, "Bob", "Carol", null), "UNKNOWN");

		for (String name : names) {
			System.out.println(name);
		}
		System.out.println(names);	// 由于没有重写toString方法,将调用被装饰者的toString方法
	}
}

class ListWithDefault<String> extends ForwardingList<String> {
	final List<String> delegate;	// 被装饰者的引用
	final String defaultValue;

	public ListWithDefault(List<String> delegate, String defalutValue) {
		this.defaultValue = defalutValue;
		this.delegate = delegate;
	}

	@Override
	protected List<String> delegate() {
		return delegate;	// 返回被装饰者的实例
	}

	@Override
	public String get(int index) {
		String s = super.get(index);
		return (s == null ? defaultValue : s);
	}

	@Override
	public Iterator<String> iterator() {
		final Iterator<String> iter = super.iterator();
		return new ForwardingIterator<String>() {
			@Override
			protected Iterator<String> delegate() {
				return iter;
			}
			@Override
			public String next() {
				String v = super.next();
				return (v == null ? defaultValue : v);
			}
		};
	}
}
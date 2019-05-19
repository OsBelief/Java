package com.java.guara;

import com.google.common.collect.ImmutableSet;
/**
 * 不可变集合
 * @author yicha
 *
 */
public class ImmutableCollection {
	public static void main(String[] args) {
		ImmutableSet<String> is = ImmutableSet.<String>of("a", "b");
		ImmutableSet<String> is2 = ImmutableSet.<String>copyOf(is);
	}
}

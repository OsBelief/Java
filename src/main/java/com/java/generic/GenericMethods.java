package com.java.generic;

import java.util.Arrays;

public class GenericMethods {
	/**
	 * 泛型方法的定义
	 * 将类型参数置于返回值之前
	 * @param t
	 */
	public <T> void f(T t) {
		System.out.println(t.getClass().getName());
	}
	public static void main(String[] args) {
		GenericMethods gm = new GenericMethods();
		// 使用泛型类时必须指明参数类型,使用泛型方法时不必指明参数类型,编译器通过类型推断找出具体的类型
		gm.f(1);
		gm.f(1.2f);
		gm.f("haha");
		gm.f(gm);
	}
}

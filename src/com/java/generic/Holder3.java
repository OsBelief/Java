package com.java.generic;

/**
 * 泛型类的定义(类型参数化) 
 * 将类型参数用尖括号括住,放在类型后面 
 * 实例化时指明持有的类型参数,将其置于尖括号内 容器同时只能持有一种类型的对象
 * 
 * @author chengzhenhua
 * 
 */
public class Holder3<T> {
	private T t;

	public Holder3(T t) {
		this.t = t;
	}

	public T get() {
		return this.t;
	}

	public void set(T t) {
		this.t = t;
	}

	public static void main(String[] args) {
		Holder3<String> holder3 = new Holder3<String>(new String());
		holder3.set("haha");
		System.out.println(holder3.get());
	}
}

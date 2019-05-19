package com.java.inner_class;

/**
 * 内部类是面向对象的闭包<br>
 * 和JavaScript中闭包的概念差不多,只不过内部函数是函数式编程的闭包<br>
 * 闭包提供了访问创建它的作用域内的数据,为作用域内不想让外界访问的数据提供了安全性
 * 
 * @author chengzhenhua
 * 
 */
interface Increatable {
	void increate();
}

public class Callee {
	private int i = 0;

	private void increate() {
		i++;
		System.out.println("外部类的i=" + i);
	}

	private class Closure implements Increatable {

		@Override
		public void increate() {
			Callee.this.increate();
		}

	}

	public Closure getClosure() {
		return new Closure();
	}

	public static void main(String[] args) {
		Callee callee = new Callee();
		Closure closure = callee.getClosure();
		closure.increate();
	}
}

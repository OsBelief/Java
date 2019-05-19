package com.java.inner_class;

/**
 * 静态内部类<br>
 * 要创建静态内部类的对象并不需要外部类对象<br>
 * 静态内部类的对象不能访问非静态的外围类的成员,访问规则有点像static方法<br>
 * 
 * @author chengzhenhua
 * 
 */
public class StaticOuter {
	private static String s = "Hello";

	public static class StaticInner {
		public void show() {
			System.out.println("我是静态内部类！" + StaticOuter.s);
		}
	}

	public static void main(String[] args) {
		StaticInner staticInner = new StaticInner();
		staticInner.show();
	}
}

package com.java.inner_class;

/**
 * 非静态内部类的定义<br>
 * 内部类拥有外围类所有成员的访问权<br>
 * 一般来说,内部类继承自某个类或实现某个接口,内部类的代码操作创建它的外围类的对象,内部类提供了进入其外围类的窗口。 <br>
 * @author chengzhenhua
 * 
 */
public class Outer {
	private String s;

	public Outer(String s) {
		this.s = s;
	}

	class Inner {
		public void show() {
			// 外部类.this.成员名 访问外部类成员
			System.out.println("我是内部类！" + Outer.this.s);
		}
	}

	public Inner getInner() {
		return new Inner();
	}

	public static void main(String[] args) {
		Outer outer = new Outer("我是外围类的成员！");
		Inner inner = outer.getInner();
		inner.show();
		// 外部类对象.new创建内部类对象(必须先创建外部类对象才能再创建内部类对象,因为内部类对象会连接到创建它的外部类对象上)
		Inner inner2 = outer.new Inner();
		inner2.show();
	}
}

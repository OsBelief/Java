package com.java.inner_class;

/**
 * 匿名内部类<br>
 * 
 * @author chengzhenhua
 * 
 */
interface Introduce {
	void introduce();
}

public class AnonymousInnerClass {
	// 匿名内部类的实例化,相当于构造器,传递的参数必须是final类型的
	public Introduce createIntroduce(final String s) {
		// 创建一个实现Introduce接口的匿名类对象
		return new Introduce() {
			private String ss = s;

			@Override
			public void introduce() {
				System.out.println(ss);
			}
		};
	}

	public static void main(String[] args) {
		AnonymousInnerClass a = new AnonymousInnerClass();
		Introduce introduce = a.createIntroduce("我是匿名内部类！");
		introduce.introduce();
	}
}

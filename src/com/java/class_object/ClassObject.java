package com.java.class_object;

/**
 * Class对象 类第一次被使用时,会被类加载器加载到JVM中创建Class对象,此时会初始化静态成员变量,执行静态代码块
 * 
 * @author chengzhenhua
 * 
 */
class Dog {
	private static String name;
	static {
		System.out.println("初始化静态成员变量: " + name);
		name = "Hello World";
		System.out.println("静态成员变量: " + name);
	}
}

public class ClassObject {
	public static void main(String[] args) {
		Dog dog = new Dog();
		// 创建Class对象的三种方式
		// 1.Class.forname()
		try {
			Class<Dog> c1 = (Class<Dog>) Class
					.forName("com.java.class_object.Dog");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2.对象的getClass方法
		Class<?> c2 = dog.getClass();
		// 3.类.class(推荐,在编译时就会受到检查)
		Class<?> c3 = Dog.class;
	}
}

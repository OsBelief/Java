package com.java.basic;

/**
 * Java是按值传递的 
 * Java传递的是对象的引用,但这些引用是按值传递的
 * 
 * @author chengzhenhua
 * 
 */
class Dog {
	private String name;

	public Dog(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

public class PassByValue {
	public static void foo(Dog d) {
		d.setName("BBB");	// 设置内存地址是42的对象引用d的name属性
		d = new Dog("CCC");	// d指向新的对象,假设其内存地址是45
		d.setName("DDD");	// 设置d新指向的对象的name属性
	}

	public static void main(String[] args) {
		Dog dog = new Dog("AAA"); // 对象的引用dog存储的是对象的内存地址,假设是42
		foo(dog);	// 至始至终dog指向的是内存地址是42的对象
		System.out.println(dog.getName());
	}
}

package com.java.class_object;

import java.util.Random;
/**
 * Java类的加载、链接、初始化
 * 加载: 由类加载器加载字节码文件,创建Class对象
 * 链接: 验证类中的字节码,为静态域分配存储空间,如果必须的话,将解析这个类创建的对其他类的所有引用
 * 初始化: 如果有父类,则先初始化父类,然后是静态成员变量,再执行静态代码块
 * Class.forName()加载、链接完成后会立即进行初始化
 * .class初始化被延迟到了对静态方法(构造器隐式的是静态的)或非常数静态域首次引用时才初始化
 * @author chengzhenhua
 *
 */
class Initable {
	static final int staticFinal = 47;
	// 虽然是static final,但Random不是编译期常量
	static final int staticFinal2 = ClassObject2.random.nextInt(1000);
	static {
		System.out.println("Initable");
	}
}

class Initable2 {
	static int staticNonFinal = 147;
	static {
		System.out.println("Initable2");
	}
}

class Initable3 {
	static int staticNonFinal = 247;
	static {
		System.out.println("Initable3");
	}
}

public class ClassObject2 {
	static Random random = new Random(47);
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("静态成员变量和静态代码块初始化的时间顺序不同");
		Class<Initable> initable = Initable.class;
		System.out.println(".class创建Class对象之后不会引发初始化");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);
		System.out.println("-----------------------------------------------");
		System.out.println(Initable2.staticNonFinal);
		System.out.println("-----------------------------------------------");
		Class inClass = Class.forName("com.java.class_object.Initable3");
		System.out.println("Class.forName()创建的Class对象会立即进行初始化");
		System.out.println(Initable3.staticNonFinal);
	}
}

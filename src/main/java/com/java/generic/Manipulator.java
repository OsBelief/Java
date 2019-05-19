package com.java.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 泛型的擦除问题 为了兼容旧的版本,在泛型代码的内部,参数类型的信息都被擦除了 表面上看类型T被替换,实际上它只是一个Object
 * List<T>被擦除为List,Class<T>被擦除为Class
 * 但可以通过其Class对象调用方法
 * @author chengzhenhua
 * 
 */
public class Manipulator<T> {
	private T obj;
	private Class<T> kind;

	public Manipulator(T t) {
		obj = t;
	}

	public Manipulator(Class<T> t) {
		this.kind = t;
	}

	public void manipulate() {
		// 在泛型代码内部,无法获得任何有关泛型参数类型的信息
		// this.obj.f();
	}

	public void manipulate2() {
		try {
			Method method = this.kind.getMethod("f");
			try {
				method.invoke(this.kind.newInstance());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HsF hsf = new HsF();
		Manipulator<HsF> manipulator = new Manipulator<HsF>(HsF.class);
		manipulator.manipulate();
		manipulator.manipulate2();
	}
}

class HsF {
	public void f() {
		System.out.println("HsF.f()");
	}
}

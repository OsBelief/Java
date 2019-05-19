package com.java.annotation;

import java.lang.reflect.Method;

/**
 * 注解处理器
 * 主要是两个反射的方法getDeclaredMethods和getAnnotation
 * @author chengzhenhua
 *
 */
public class AnnotationProcessor {
	public static void main(String[] args) {
		Class<?> cl = Test2.class;
		Method[] methods = cl.getDeclaredMethods();
		for (Method m : methods) {
			UserCase uc = m.getAnnotation(UserCase.class);
			System.out.println("id: " + uc.id() + ", " + uc.description());
		}
	}
}
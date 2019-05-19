package com.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解
 * 1.注解和接口一样也会被编译成Class文件
 * @author chengzhenhua
 * 2.元注解
 * 	@Target:注解将应用于什么地方(一个方法或者一个域)
 *  @Retention:注解在哪一个级别可用(SOURCE、CLASS、RUNTIME)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 标记注解:没有元素的注解
public @interface Test {
};

class Testable {
	@Test
	public void test() {

	}
}

package com.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 定义注解
public @interface UserCase{;
	public int id();
	public String description() default "我是注解";
}
// 使用注解
class Test2{
	@UserCase(id=12, description="I am annotation")
	public void test1(){
		
	}
	@UserCase(id=12)
	public void test2() {
		
	}
}

package com.java.designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 
 * 动态代理相比静态代理是在运行时动态的创建出代理类及其对象,而且代码量少
 * 
 * @author chengzhenhua
 * 
 */
interface SameInterface2 {
	public void doSomething();

	public void doElseThing();
}

class RealObject2 implements SameInterface2 {

	@Override
	public void doSomething() {
		System.out.println("RealObject doSomething");
	}

	@Override
	public void doElseThing() {
		System.out.println("RealObject doElseThing");
	}

}

/**
 * 代理类实现InvocationHandler的invoke方法
 * 
 * @author chengzhenhua
 * 
 */
class DynamicProxyObject implements InvocationHandler {
	private SameInterface2 proxied;

	public DynamicProxyObject(SameInterface2 proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("代理对象: " + proxy.getClass() + ", 代理方法: "
				+ method.getName() + ", 方法参数: " + args);
		return method.invoke(proxied, args);
	}

}

public class DynamicProxy {
	public static void main(String[] args) {
		SameInterface2 realObject = new RealObject2();
		SameInterface2 proxyObject = (SameInterface2) Proxy.newProxyInstance(
				SameInterface2.class.getClassLoader(),
				new Class[] { SameInterface2.class }, new DynamicProxyObject(
						realObject));
		proxyObject.doSomething();
		proxyObject.doElseThing();
	}
}

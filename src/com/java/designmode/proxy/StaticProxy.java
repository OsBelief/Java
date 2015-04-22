package com.java.designmode.proxy;
/**
 * 代理模式
 * 特征:代理对象和真实对象实现同一个接口
 * 作用:为真实对象增加额外的功能,增强真实对象的方法
 * 静态代理的缺点:如果接口的方法比较多,则代码量比较大
 * @author chengzhenhua
 *
 */
interface SameInterface{
	public void doSomething();
	public void doElseThing();
}
class RealObject implements SameInterface{

	@Override
	public void doSomething() {
		System.out.println("RealObject doSomething");
	}

	@Override
	public void doElseThing() {
		System.out.println("RealObject doElseThing");
	}
	
}
class StaticProxyObject implements SameInterface {
	private SameInterface sameInterface;
	public StaticProxyObject(SameInterface sameInterface) {
		this.sameInterface = sameInterface;
	}
	@Override
	public void doSomething() {
		this.sameInterface.doSomething();
		System.out.println("干点其他的事,扩展真实对象的功能");
	}

	@Override
	public void doElseThing() {
		this.sameInterface.doElseThing();
		System.out.println("干点其他的事,扩展真实对象的功能");
	}
	
}
public class StaticProxy {
	public static void main(String[] args) {
		SameInterface realObject = new RealObject();
		SameInterface proxyObject = new StaticProxyObject(realObject);
		proxyObject.doSomething();
		proxyObject.doElseThing();
	}
}

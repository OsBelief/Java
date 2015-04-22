package com.java.designmode;

import java.lang.reflect.Proxy;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
/**|
 * Guava测试EventBus观察者模式
 * @author yicha
 *
 */
public class ObserverMode {
	private static Message message = new Message();
	public static void main(String[] args) {
		EventBus eventBus = new EventBus();	//发布者
		EventObserver e1 = new EventObserver(1);
		EventObserver e2 = new EventObserver(2);
		EventObserver e3 = new EventObserver(3);
		eventBus.register(e3);
		eventBus.register(e2);
		eventBus.register(e2);
		eventBus.register(e1); //注册订阅者
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入消息:");
		message.setStr(scanner.next());
		eventBus.post(message);	//发布消息
		
		System.out.println("********************************");
		eventBus.unregister(e1);	//取消注册
		eventBus.post(message);
		
		System.out.println("################################");
		Executor executor = Executors.newCachedThreadPool();
		AsyncEventBus asyncEventBus = new AsyncEventBus(executor);	//异步发布
		asyncEventBus.register(e1);
		asyncEventBus.post(message);
		System.out.println("异步发布消息！");
	}
}
/**|
 * 订阅者
 * @author yicha
 *
 */
class EventObserver {
	int id;
	public EventObserver(int id) {
		this.id = id;
	}
	//订阅者方法
	@Subscribe
	public void onMessage(Message message) {
		System.out.println(this.id + " Hello: " + message.getStr() + " " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
/**
 * 消息对象
 * @author yicha
 *
 */
class Message {
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
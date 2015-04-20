package com.java.designmode.decorator;
/**
 * 装饰者模式测试
 * @author yicha
 *
 */
public class Test {
	public static void main(String[] args) {
		Beverage b1 = new HouseBlend();
		Beverage b2 = new Espresso();
		System.out.println(b1.getDescription() + " $" + b1.cost());
		System.out.println(b2.getDescription() + " $" + b2.cost());
		b1 = new Mocha(b1);
		b2 = new Mocha(b2);
		System.out.println(b1.getDescription() + " $" + b1.cost());
		System.out.println(b2.getDescription() + " $" + b2.cost());
	}
}

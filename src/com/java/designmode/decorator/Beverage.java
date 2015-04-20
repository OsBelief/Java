package com.java.designmode.decorator;
/**
 * 装饰者和被装饰者的共同父类
 * @author yicha
 *
 */
public abstract class Beverage {
	String description = "unknown Berage";
	public String getDescription() {
		return description;
	}
	public abstract double cost();
}

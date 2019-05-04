package com.java.designpattern.decorator;
/**
 * 具体被装饰者
 * @author yicha
 *
 */
public class Espresso extends Beverage {
	public Espresso() {
		description = "Espresso";
	}

	@Override
	public double cost() {
		return 1.99;
	}
}

package com.java.designpattern.decorator;
/**
 * 具体装饰者
 * @author yicha
 *
 */
public class Mocha extends CondimentDecorator {
	Beverage beverage;
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	@Override
	public String getDescription() {
		return beverage.getDescription() + ",Mocha";
	}

	@Override
	public double cost() {
		return 0.22 + beverage.cost();
	}

}

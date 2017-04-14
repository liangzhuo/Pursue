package com.liang.decorator.way1;

/**
 * 低咖啡因咖啡
 * @author Administrator
 *
 */
public class Decaf extends Beverage {
	
	public Decaf(){
		description = "Decaf Coffee";
	}

	@Override
	public double cost() {
		return 1.09;
	}

}

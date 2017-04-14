package com.liang.decorator.way1;

/**
 * 黑咖啡
 * @author Administrator
 *
 */
public class DarkRoast extends Beverage {
	
	public DarkRoast(){
		description = "DarkRoast Coffee";
	}

	@Override
	public double cost() {
		return 1.49;
	}

}

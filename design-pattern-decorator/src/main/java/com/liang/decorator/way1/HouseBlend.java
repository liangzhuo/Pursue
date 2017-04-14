package com.liang.decorator.way1;

/**
 * 普通咖啡
 * @author Administrator
 *
 */
public class HouseBlend extends Beverage {
	
	public HouseBlend() {
		description = "House Blend Coffee";
	}

	@Override
	public double cost() {
		return 0.89;
	}

}

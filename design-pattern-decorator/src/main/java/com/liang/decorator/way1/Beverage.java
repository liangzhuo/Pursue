package com.liang.decorator.way1;

/**
 * 饮品
 * @author Administrator
 *
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
	
	public String getDescription(){
		return description;
	}
	
	public abstract double cost();
}

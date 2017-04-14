package com.liang.decorator;

/**
 * 饮料
 * @author Administrator
 *
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
	boolean milk;
	boolean soy;
	boolean mocha;
	boolean whip;
	
	
	public String getDescription(){
		return description;
	}
	
	public abstract float cost();

	public boolean isMilk() {
		return milk;
	}

	public void setMilk(boolean milk) {
		this.milk = milk;
	}

	public boolean isSoy() {
		return soy;
	}

	public void setSoy(boolean soy) {
		this.soy = soy;
	}

	public boolean isMocha() {
		return mocha;
	}

	public void setMocha(boolean mocha) {
		this.mocha = mocha;
	}

	public boolean isWhip() {
		return whip;
	}

	public void setWhip(boolean whip) {
		this.whip = whip;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

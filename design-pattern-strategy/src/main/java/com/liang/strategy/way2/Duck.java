package com.liang.strategy.way2;

public abstract class Duck {
	public void swim(){
		System.out.println("I can swim!");
	}
	
	protected abstract void display();
}

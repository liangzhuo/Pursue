package com.liang.strategy.way1;

public abstract class Duck {
	public void quack(){
		System.out.println("I can dancin!");
	}
	
	public void swim(){
		System.out.println("I can swim!");
	}
	
	public void fly(){
		System.out.println("I can fly!");
	}
	
	protected abstract void display();
}

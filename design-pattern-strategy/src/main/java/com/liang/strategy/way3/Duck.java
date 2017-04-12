package com.liang.strategy.way3;

public abstract class Duck {
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
	
	public void swim(){
		System.out.println("I can swim!");
	}
	
	public void performQuack(){
		flyBehavior.fly();
	}
	
	public void performFly(){
		quackBehavior.quack();
	}
	
	public void setFlyBehavior(FlyBehavior flyBehavior){
		this.flyBehavior = flyBehavior;
	}
	
	public void setQuackBehavior(QuackBehavior quackBehavior){
		this.quackBehavior = quackBehavior;
	}
	
	protected abstract void display();
}

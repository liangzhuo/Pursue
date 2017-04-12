package com.liang.strategy.way1;

/**
 * 橡皮鸭（吱吱叫；不会飞）
 * @author Administrator
 *
 */
public class RubberDuck extends Duck {
	
	@Override
	public void quack() {
		System.out.println("I can squeak!");
	}
	
	@Override
	public void fly() {
		// 重写，什么事不做
	}

	@Override
	protected void display() {
		System.out.println("I'm rubber duck!");
	}

}

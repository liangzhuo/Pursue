package com.liang.strategy.way2;

/**
 * 橡皮鸭（吱吱叫；不会飞）
 * @author Administrator
 *
 */
public class RubberDuck extends Duck implements Quackable {

	@Override
	public void quack() {
		System.out.println("I can squeak!");
	}

	@Override
	protected void display() {
		System.out.println("I'm rubber duck!");
	}

}

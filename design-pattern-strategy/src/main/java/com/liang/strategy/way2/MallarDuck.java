package com.liang.strategy.way2;

/**
 * 绿头鸭
 * @author Administrator
 *
 */
public class MallarDuck extends Duck implements Flyable,Quackable {

	@Override
	public void quack() {
		System.out.println("I can quack!");
	}

	@Override
	public void fly() {
		System.out.println("I can fly!");
	}

	@Override
	protected void display() {
		System.out.println("I'm green duck!");
	}

}

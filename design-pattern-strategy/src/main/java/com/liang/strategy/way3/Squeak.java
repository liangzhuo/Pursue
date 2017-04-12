package com.liang.strategy.way3;

public class Squeak implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("I can squeak!");
	}

}

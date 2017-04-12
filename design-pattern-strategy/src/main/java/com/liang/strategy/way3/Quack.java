package com.liang.strategy.way3;

public class Quack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("I can quack!");
	}

}

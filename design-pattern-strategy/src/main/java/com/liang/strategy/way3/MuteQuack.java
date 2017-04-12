package com.liang.strategy.way3;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("I can't quack!");
	}

}

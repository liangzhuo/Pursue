package com.liang.strategy.way3;

/**
 * 诱饵鸭（不会飞；不会叫）
 * @author Administrator
 *
 */
public class DecoyDuck extends Duck {

	@Override
	protected void display() {
		System.out.println("I'm decoy duck!");
	}

}

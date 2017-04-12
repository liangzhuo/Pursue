package com.liang.strategy.way1;

/**
 * 诱饵鸭（不会飞；不会叫）
 * @author Administrator
 *
 */
public class DecoyDuck extends Duck {
	
	@Override
	public void quack() {
		// 覆盖，什么事也不做
	}
	
	@Override
	public void fly() {
		// 覆盖，什么事也不做
	}

	@Override
	protected void display() {
		// 诱饵鸭
		System.out.println("I'm decoy duck!");
	}

}

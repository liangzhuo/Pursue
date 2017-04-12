package com.liang.strategy.way3;

/**
 * 橡皮鸭（吱吱叫；不会飞）
 * @author Administrator
 *
 */
public class RubberDuck extends Duck {

	@Override
	protected void display() {
		System.out.println("I'm rubber duck!");
	}

}

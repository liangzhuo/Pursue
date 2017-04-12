package com.liang.strategy.way3;

public class DuckTest {
	public static void main(String[] args) {
		Duck mallarDuck = new MallarDuck();
		mallarDuck.setFlyBehavior(new FlyWithWings());
		mallarDuck.setQuackBehavior(new Quack());
		mallarDuck.display();
		mallarDuck.performFly();
		mallarDuck.performQuack();
		
		System.out.println("======================");
		Duck rubberDuck = new RubberDuck();
		rubberDuck.setFlyBehavior(new FlyNoWay());
		rubberDuck.setQuackBehavior(new Squeak());
		rubberDuck.display();
		rubberDuck.performFly();
		rubberDuck.performQuack();
		
		System.out.println("======================");
		Duck decoyDuck = new DecoyDuck();
		decoyDuck.setFlyBehavior(new FlyNoWay());
		decoyDuck.setQuackBehavior(new MuteQuack());
		decoyDuck.display();
		decoyDuck.performFly();
		decoyDuck.performQuack();
	}
}

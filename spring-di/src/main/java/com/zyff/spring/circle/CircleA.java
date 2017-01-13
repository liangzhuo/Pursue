package com.zyff.spring.circle;

public class CircleA {
	private CircleB circleB;

	public CircleA() {
		super();
	}

	public CircleA(CircleB circleB) {
		super();
		this.circleB = circleB;
	}

	public CircleB getCircleB() {
		return circleB;
	}

	public void setCircleB(CircleB circleB) {
		this.circleB = circleB;
	}
	
	public void a(){
		circleB.b();
	}
	
}

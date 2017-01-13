package com.zyff.spring.circle;

public class CircleC {
	private CircleA circleA;
	
	public CircleC() {
		super();
	}

	public CircleC(CircleA circleA) {
		super();
		this.circleA = circleA;
	}
	
	public CircleA getCircleA() {
		return circleA;
	}

	public void setCircleA(CircleA circleA) {
		this.circleA = circleA;
	}

	public void c() {
		circleA.a();
	}

}

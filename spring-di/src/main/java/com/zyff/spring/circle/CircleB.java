package com.zyff.spring.circle;

public class CircleB {
	private CircleC circleC;
	
	public CircleB() {
		super();
	}

	public CircleB(CircleC circleC) {
		super();
		this.circleC = circleC;
	}

	public CircleC getCircleC() {
		return circleC;
	}

	public void setCircleC(CircleC circleC) {
		this.circleC = circleC;
	}

	public void b() {
		circleC.c();
	}

}

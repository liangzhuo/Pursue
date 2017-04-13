package com.liang.observer.way1;

/**
 * 目前状况布告板
 * @author Administrator
 *
 */
public class CurrentConditionDisplay implements Displayable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public void update(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}

	@Override
	public void display() {
		System.out.println("CurrentConditionDisplay:" + "...");
	}

}

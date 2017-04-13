package com.liang.observer.way1;

/**
 * 预测布告板
 * @author Administrator
 *
 */
public class ForecastDisplay implements Displayable {
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
		System.out.println("ForecastDisplay:" + "...");
	}

}

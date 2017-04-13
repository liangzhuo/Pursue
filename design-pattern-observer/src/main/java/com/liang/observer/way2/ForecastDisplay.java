package com.liang.observer.way2;

/**
 * 实现了Observer接口，可以从weatherData对象中获得改变
 * 
 * @author Administrator
 *
 */
public class ForecastDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;
	
	/**
	 * 
	 * @param weatherData 作为观察者注册之用
	 */
	public ForecastDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Forecast: " + temperature + " F degrees and "
				+ humidity + "% humidity.");
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}

}

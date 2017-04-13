package com.liang.observer.way3;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
	Observable observable;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public StatisticsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature + " F degrees and "
				+ humidity + "% humidity " + pressure);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if(obs instanceof WeatherData){
			WeatherData weatherData = (WeatherData)obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			this.pressure = weatherData.getPressure();
			display();
		}
	}

}

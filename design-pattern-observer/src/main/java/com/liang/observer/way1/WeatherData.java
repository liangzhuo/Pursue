package com.liang.observer.way1;

public class WeatherData {
	private float temperature;
	private float humidity;
	private float pressure;
	
	private CurrentConditionDisplay currentConditionDisplay;
	private StatisticsDisplay statisticsDisplay;
	private ForecastDisplay forecastDisplay;
	
	public WeatherData(CurrentConditionDisplay currentConditionDisplay, StatisticsDisplay statisticsDisplay, ForecastDisplay forecastDisplay){
		this.currentConditionDisplay = currentConditionDisplay;
		this.statisticsDisplay = statisticsDisplay;
		this.forecastDisplay = forecastDisplay;
	}
	
	public float getTemperature() {
		return temperature;
	}



	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}



	public float getHumidity() {
		return humidity;
	}



	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}



	public float getPressure() {
		return pressure;
	}



	public void setPressure(float pressure) {
		this.pressure = pressure;
	}



	public void measurementsChanged(){
		// 你的代码写在这里
		/**
		 * 1.显示的参数可能会发生改变
		 * 2.针对接口编程，而不是针对实现编程（增加或修改布告板都要修改代码）
		 */
		currentConditionDisplay.update(temperature, humidity, pressure);
		statisticsDisplay.update(temperature, humidity, pressure);
		forecastDisplay.update(temperature, humidity, pressure);
	}
}

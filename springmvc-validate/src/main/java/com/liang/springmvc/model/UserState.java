package com.liang.springmvc.model;

public enum UserState {
	blocked("blocked","锁定");
	
	private String key;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	UserState(String key, String message){
		this.key = key;
		this.message = message;
	}
}

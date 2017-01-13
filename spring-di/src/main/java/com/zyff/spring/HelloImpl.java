package com.zyff.spring;

public class HelloImpl implements HelloApi {
	private String message;
	private int index;
	
	public HelloImpl(){
		
	}
	
	//@java.beans.ConstructorProperties({"message","index"})
	public HelloImpl(String message, int index){
		this.message = message;
		this.index = index;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

	public void sayHello() {
		System.out.println("say hello:" + message + "," + index);
	}

}

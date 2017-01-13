package com.zyff.spring;

public class HelloImpl2 implements HelloApi {
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public HelloImpl2(){
		this.message = "hello world";
	}
	
	public HelloImpl2(String message){
		this.message = message;
	}

	public void sayHello() {
		System.out.println(this.message);
	}

}

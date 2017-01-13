package com.zyff.spring;

public class HelloApiDecorator implements HelloApi {
	private HelloApi helloApi;
	
	//使用setter方法进行注入时，一定是有默认的无参构造函数
	public HelloApiDecorator() {
		
	}

	public HelloApiDecorator(HelloApi helloApi) {
		this.helloApi = helloApi;
	}

	public HelloApi getHelloApi() {
		return helloApi;
	}


	public void setHelloApi(HelloApi helloApi) {
		this.helloApi = helloApi;
	}


	public void sayHello() {
		System.out.println("###########装饰一下");
		helloApi.sayHello();
	}
	
	

}

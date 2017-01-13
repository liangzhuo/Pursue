package com.liang.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liang.dubbo.service.UserService;

public class Provider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService)cx.getBean("userService");
		userService.getUser(1l);
		
		System.in.read();
	}
}

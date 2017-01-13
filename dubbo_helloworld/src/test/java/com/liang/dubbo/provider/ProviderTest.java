package com.liang.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderTest {
	public static void main(String[] args) throws IOException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-provider.xml"});
		context.start();
		
		System.in.read();//为保证服务一直开着，利用输入流的阻塞来模拟
	}
}

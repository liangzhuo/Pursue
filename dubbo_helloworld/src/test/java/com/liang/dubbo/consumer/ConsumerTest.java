package com.liang.dubbo.consumer;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liang.dubbo.provider.DubboService;

public class ConsumerTest {
	@Test
	public void testConsumer(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring-consumer.xml"});
		DubboService dubboService = context.getBean("dubboService",DubboService.class);
		String result = dubboService.sayHello();
		System.out.println(result);
	}
}

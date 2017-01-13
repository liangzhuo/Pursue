package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApiDecoratorTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloApiDecorator hello1 = ac.getBean("hello1",HelloApiDecorator.class);
		hello1.sayHello();
		HelloApiDecorator hello2 = ac.getBean("hello2",HelloApiDecorator.class);
		hello2.sayHello();
	}
}

package com.zyff.spring;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBeanTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloApiDecorator innerBean = ac.getBean("inner",HelloApiDecorator.class);
		innerBean.sayHello();
	}
}

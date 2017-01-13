package com.zyff.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class InstantBeanTest extends TestCase {
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_instantBean.xml");
		HelloApi hello = ac.getBean("bean1",HelloApi.class);
		hello.sayHello();
		
		HelloApi bean2 = ac.getBean("bean2",HelloApi.class);
		bean2.sayHello();
		
		HelloApi bean3 = ac.getBean("bean3",HelloApi.class);
		bean3.sayHello();
		
		HelloApi bean4 = ac.getBean("bean4",HelloApi.class);
		bean4.sayHello();
	}
}

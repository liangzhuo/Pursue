package com.zyff.spring;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BooleanBeanTest extends TestCase {
	@Test
	public void testBoolean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BooleanBean booleanBean1 = ac.getBean("booleanBean1",BooleanBean.class);
		booleanBean1.printSuccess();
		BooleanBean booleanBean2 = ac.getBean("booleanBean2",BooleanBean.class);
		booleanBean2.printSuccess();
		BooleanBean booleanBean3 = ac.getBean("booleanBean3",BooleanBean.class);
		booleanBean3.printSuccess();
	}
}

package com.zyff.spring;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LocalAndParentTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext parentAc = new ClassPathXmlApplicationContext("applicationContext_parent.xml");
		ApplicationContext childrenAc = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"},parentAc);
		HelloApiDecorator parent = childrenAc.getBean("parent",HelloApiDecorator.class);
		parent.sayHello();//该bean引用parent bean
		HelloApiDecorator children = childrenAc.getBean("children",HelloApiDecorator.class);
		children.sayHello();//该bean引用local bean
	}
}

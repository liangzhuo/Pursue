package com.zyff.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class HelloImplTest extends TestCase {
	@Test
	public void testConstructorDependencyInject(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取根据参数索引依赖注入的bean
		HelloApi byIndex = ac.getBean("byIndex",HelloApi.class);
		byIndex.sayHello();
		
		//获取根据参数类型依赖注入的bean
		HelloApi byType = ac.getBean("byType",HelloApi.class);
		byType.sayHello();
		
		//获取根据参数名称依赖注入的bean
		HelloApi byName = ac.getBean("byName",HelloApi.class);
		byName.sayHello();
	}
	
	@Test
	public void testSetterDependencyInject(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloApi bean = ac.getBean("bean",HelloApi.class);
		bean.sayHello();
	}
}

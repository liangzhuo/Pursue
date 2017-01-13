package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertiesBeanTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PropertiesBean propertiesBean = ac.getBean("propertiesBean",PropertiesBean.class);
		Assert.assertEquals(2, propertiesBean.getProperties().size());
	}
}

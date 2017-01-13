package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapBeanTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		MapBean mapBean = ac.getBean("mapBean",MapBean.class);
		Assert.assertEquals(2, mapBean.getValues().size());
	}
}

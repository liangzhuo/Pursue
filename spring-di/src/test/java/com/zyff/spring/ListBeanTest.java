package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListBeanTest extends TestCase{
	@Test
	public void testListBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ListBean listBean = ac.getBean("listBean",ListBean.class);
		listBean.printValues();
		Assert.assertEquals(3, listBean.getValues().size());
	}
}

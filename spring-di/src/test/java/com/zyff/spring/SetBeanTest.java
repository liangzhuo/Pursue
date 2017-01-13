package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetBeanTest extends TestCase{
	@Test
	public void testListBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CollectionBean setBean = ac.getBean("setBean",CollectionBean.class);
		setBean.printValues();
		Assert.assertEquals(3, setBean.getValues().size());
	}
}

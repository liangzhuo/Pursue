package com.zyff.spring;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArrayBeanTest extends TestCase{
	@Test
	public void testArrayBean(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArrayBean arrayBean = ac.getBean("array",ArrayBean.class);
		Assert.assertEquals(2, arrayBean.getArray().length);
		Assert.assertEquals(2, arrayBean.getArray2()[0].length);
	}
}

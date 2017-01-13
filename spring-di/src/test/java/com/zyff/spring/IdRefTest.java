package com.zyff.spring;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IdRefTest extends TestCase{
	@Test
	public void testIdRef(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IdRefBean bean1 = ac.getBean("idRefBean1",IdRefBean.class);
		bean1.printId();
		
		IdRefBean bean2 = ac.getBean("idRefBean2",IdRefBean.class);
		bean2.printId();
	}
}

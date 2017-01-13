package com.zyff.spring.circle;

import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CircleTest {
	@Test(expected = BeanCurrentlyInCreationException.class)
	public void testCircleByConstructor() throws Throwable{
		try {
			new ClassPathXmlApplicationContext("circleInjectByConstructor.xml");
		} catch (Exception e) {
			Throwable e1 = e.getCause().getCause().getCause();
			throw e1;
		}
	}
	
	@Test(expected = BeanCurrentlyInCreationException.class)
	public void testCircleBySetterAndPrototype() throws Throwable{
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("circleInjectBySetterAndPrototype.xml");
			System.out.println(ac.getBean("circleA"));
		} catch (Exception e) {
			Throwable e1 = e.getCause().getCause().getCause();
			throw e1;
		}
	}
}

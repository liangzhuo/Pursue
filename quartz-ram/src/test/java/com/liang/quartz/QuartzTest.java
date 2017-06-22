package com.liang.quartz;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzTest {
	
	@Test
	public void test() throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("quartz.xml");
		
		Thread.sleep(100000);
		
		System.exit(0);
	}
}

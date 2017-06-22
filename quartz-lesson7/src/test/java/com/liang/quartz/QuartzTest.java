package com.liang.quartz;

import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	
	@Test
	public void test() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			scheduler.start();
			
			scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

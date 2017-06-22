package com.liang.quartz;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liang.quartz.job.HelloWorldJob;

public class QuartzTest {
	
	@Test
	public void test() throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("quartz.xml");
		
		Scheduler scheduler = (StdScheduler)ac.getBean("scheduler");
		
		startSchedule(scheduler);
		
		Thread.sleep(10000);
		
		System.exit(0);
	}

	private void startSchedule(Scheduler scheduler) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(HelloWorldJob.class)
					.withIdentity("job1", "group2")
					.build();
			
			CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "tGroup2")
					.withSchedule(builder)
					.build();
			
			scheduler.start();
			
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

package com.liang.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.liang.quartz.job.DumbJob;

public class Quartz {
	
	public static void main(String[] args) {
		try {
			// 1.从调度工厂获取Scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			// 2.启动和关闭任务调度
			scheduler.start();
			
			// 定义job并与HelloJob进行关联
			JobDetail job = JobBuilder.newJob(DumbJob.class)
					.withIdentity("job1", "group1")
					.usingJobData("jobSays", "hello world!")
					.usingJobData("myFloatValue", 3.14f)
					.build();
			
			// 定义job运行的触发器，40秒重复一次
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
					.build();
			
			// 通知quartz使用触发器触发job
			scheduler.scheduleJob(job, trigger);
			//scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

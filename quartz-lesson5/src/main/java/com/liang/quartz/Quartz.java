package com.liang.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import com.liang.quartz.job.DumbJob;

public class Quartz {
	
	public static void main(String[] args) {
		try {
			
			// 1.从调度工厂获取Scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			HolidayCalendar cal = new HolidayCalendar();
			cal.addExcludedDate(new Date());
			cal.addExcludedDate(new Date());
			scheduler.addCalendar("myHolidays", cal, false, false);
			
			// 2.启动和关闭任务调度
			scheduler.start();
			
			// 定义job并与HelloJob进行关联
			JobDetail job = JobBuilder.newJob(DumbJob.class)
					.withIdentity("job1", "group1")
					.usingJobData("jobSays", "hello world!")
					.usingJobData("myFloatValue", 3.14f)
					.build();
			
			JobDetail job2 = JobBuilder.newJob(DumbJob.class)
					.withIdentity("job2", "group1")
					.usingJobData("jobSays", "hello world!")
					.usingJobData("myFloatValue", 3.14f)
					.build();
			
			// 设定在指定时间触发，不重复执行
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startAt(new Date())
					.forJob(job)
					.build();
			
			// 设定在指定时间触发，间隔时间为10s，重复10次
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group1")
					.startAt(new Date())
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10))
					.forJob(job)
					.build();
			
			// 设定在将的时间5分钟触发一次
			Trigger trigger3 = TriggerBuilder.newTrigger()
					.withIdentity("trigger3", "group1")
					.startAt(DateBuilder.futureDate(5, IntervalUnit.MINUTE))
					.forJob(job)
					.build();
			
			// 设定现在触发，每5分钟重复一次，直到10：00
			Trigger trigger4 = TriggerBuilder.newTrigger()
					.withIdentity("trigger4", "group1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever())
					.endAt(DateBuilder.dateOf(22, 0, 0))
					.forJob(job)
					.build();
			
			// 设定在下一个零点，每两小时重复一次，一直重复
			Trigger trigger5 = TriggerBuilder.newTrigger()
					.withIdentity("trigger5") // 不指定组，触发器将在默认组中
					.startAt(DateBuilder.evenHourDate(null)) // 获取下一个零点 00:00
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(2).repeatForever())
					.endAt(DateBuilder.dateOf(22, 0, 0))
					//.forJob(job)
					// forJob不被调用，触发器和job一起传递给Scheduler是有效的
					.build();
			
			// 增加失败处理指令
			Trigger trigger6 = TriggerBuilder.newTrigger()
					.withIdentity("trigger6") // 不指定组，触发器将在默认组中
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever()
							.withMisfireHandlingInstructionNextWithExistingCount())
					.endAt(DateBuilder.dateOf(22, 0, 0))
					.build();
			
			
			// 通知quartz使用触发器触发job
			scheduler.scheduleJob(job, trigger);
			//scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

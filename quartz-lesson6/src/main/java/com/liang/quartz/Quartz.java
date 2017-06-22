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
			
			// 设定在每天的上午8点至下午5点，每2分钟执行一次
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
					.forJob(job)
					.build();
			
			// 设定在上午10:42触发
			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group1")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 42))
					.forJob(job)
					.build();
			
			Trigger trigger3 = TriggerBuilder.newTrigger()
					.withIdentity("trigger3", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ?"))
					.forJob(job)
					.build();
			
			// 每个星期三的上午10:42触发
			Trigger trigger4 = TriggerBuilder.newTrigger()
					.withIdentity("trigger4", "group1")
					.withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(DateBuilder.WEDNESDAY, 10, 42))
					.forJob(job)
					.build();
			
			Trigger trigger5 = TriggerBuilder.newTrigger()
					.withIdentity("trigger5", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 ? * WED"))
					.forJob(job)
					.build();
			
			// 触发失败则开户失败处理指令进行触发
			Trigger trigger6 = TriggerBuilder.newTrigger()
					.withIdentity("trigger6", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 ? * WED")
							.withMisfireHandlingInstructionFireAndProceed())
					.forJob(job)
					.build();
			
			
			// 通知quartz使用触发器触发job
			scheduler.scheduleJob(job, trigger);
			//scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

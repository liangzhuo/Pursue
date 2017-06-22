package com.liang.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.impl.matchers.OrMatcher;
import org.quartz.listeners.JobChainingJobListener;

import com.liang.quartz.job.DumbJob;
import com.liang.quartz.listener.MyJobListener;

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
			
			JobListener jobListener = new MyJobListener();
			
			
			// 定义job并与HelloJob进行关联
			JobDetail job = JobBuilder.newJob(DumbJob.class)
					.withIdentity("job1", "group1")
					.usingJobData("jobSays", "hello world!")
					.usingJobData("myFloatValue", 3.14f)
					.build();
			
			
			
			// 设定在每天的上午8点至下午5点，每2分钟执行一次
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
					.forJob(job)
					.build();
			
			scheduler.getListenerManager().addJobListener(jobListener, KeyMatcher.keyEquals(JobKey.jobKey("job1", "group1")));
			
			scheduler.getListenerManager().addJobListener(jobListener, GroupMatcher.jobGroupEquals("group1"));
			
			scheduler.getListenerManager().addJobListener(jobListener, OrMatcher.or(GroupMatcher.jobGroupEquals("group1"), GroupMatcher.jobGroupEquals("group2")));
			
			scheduler.getListenerManager().addJobListener(jobListener, EverythingMatcher.allJobs());
			
			// 通知quartz使用触发器触发job
			scheduler.scheduleJob(job, trigger);
			//scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

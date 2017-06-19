package com.liang.quartz.job;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DumbJob implements Job {

	private String jobSays;
	private float myFloatValue;

	public String getJobSays() {
		return jobSays;
	}

	public void setJobSays(String jobSays) {
		this.jobSays = jobSays;
	}

	public float getMyFloatValue() {
		return myFloatValue;
	}

	public void setMyFloatValue(float myFloatValue) {
		this.myFloatValue = myFloatValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobKey key = context.getJobDetail().getKey();

		JobDataMap dataMap = context.getMergedJobDataMap();

		// String jobSays = dataMap.getString("jobSays");
		// float myFloatValue = dataMap.getFloat("myFloatValue");
		// List state = (ArrayList)dataMap.get("myStateData");
		// state.add(new Date());

		System.err.println("Instance " + key + " of DumbJob says: " + jobSays
				+ ", val is: " + myFloatValue);
	}

}

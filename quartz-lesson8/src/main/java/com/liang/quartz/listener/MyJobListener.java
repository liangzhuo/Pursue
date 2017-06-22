package com.liang.quartz.listener;

import org.quartz.listeners.JobListenerSupport;

public class MyJobListener extends JobListenerSupport{

	@Override
	public String getName() {
		return "myJobLister";
	}

}

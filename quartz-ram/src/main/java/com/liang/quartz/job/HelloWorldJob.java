package com.liang.quartz.job;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldJob implements Job {
	private final Logger LOG = LoggerFactory.getLogger(getClass());


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("This is a first spring combine quartz !");
		LOG.info("Welcome to Spring Quartz World!" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		LOG.info("Let's begin ! \n\n");
	}

}

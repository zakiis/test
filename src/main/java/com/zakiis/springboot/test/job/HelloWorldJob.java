package com.zakiis.springboot.test.job;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloWorldJob extends QuartzJobBean {
	
	Logger log = LoggerFactory.getLogger(HelloWorldJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Trigger trigger = context.getTrigger();
		Date fireTime = context.getFireTime();
		log.info("job executed, trigger:{} , fire time:{}, data map:{}", trigger, fireTime, jobDataMap);
	}

}

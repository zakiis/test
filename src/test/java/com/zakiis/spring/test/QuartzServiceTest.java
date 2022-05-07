package com.zakiis.spring.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zakiis.common.JsonUtil;
import com.zakiis.job.dto.JobDTO;
import com.zakiis.job.service.QuartzService;
import com.zakiis.spring.test.job.HelloWorldJob;

@SpringBootTest
public class QuartzServiceTest {

	@Autowired
	QuartzService quartzService;
	
	@Test
	public void test() throws IOException {
		List<JobDTO> jobList = quartzService.queryAllJob();
		System.out.println(JsonUtil.toJson(jobList));
		
		/**
		 * DELETE from QRTZ_CRON_TRIGGERS where SCHED_NAME = 'quartzScheduler' and TRIGGER_NAME = 'hello-job' and TRIGGER_GROUP  = 'zakiis';
		 * delete from QRTZ_TRIGGERS where SCHED_NAME = 'quartzScheduler' and TRIGGER_NAME = 'hello-job' and TRIGGER_GROUP  = 'zakiis';
		 * DELETE FROM QRTZ_JOB_DETAILS where SCHED_NAME = 'quartzScheduler' and JOB_NAME = 'hello-job' and JOB_GROUP = 'zakiis';
		 */
		for (JobDTO jobDTO : jobList) {
			quartzService.deleteJob(jobDTO.getJobName(), jobDTO.getJobGroupName());
		}
		
		HashMap<String, Object> jobData = new HashMap<String, Object>();
		jobData.put("name", "Jack");
		quartzService.addCronJob(HelloWorldJob.class, "hello-job", "zakiis", "0 0/1 * * * ?", jobData);
		
		quartzService.pauseJob("hello-job", "zakiis");
		JobDTO helloJob = quartzService.queryJob("hello-job", "zakiis").get(0);
		System.out.println("After pause job: " + JsonUtil.toJson(helloJob));
		quartzService.resumeJob("hello-job", "zakiis");
		helloJob = quartzService.queryJob("hello-job", "zakiis").get(0);
		System.out.println("After resume job: " + JsonUtil.toJson(helloJob));
		
		System.in.read();
	}
}

package com.zakiis.spring.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.zakiis.common.TraceIdUtil;
import com.zakiis.spring.test.service.CallHealthApiService;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TraceIdFilterTest {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CallHealthApiService callHealthApiService;

	@Test
	public void test() {
		TraceIdUtil.set("Zakiis-" + System.currentTimeMillis());
		ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8080/health", String.class);
		assertEquals(result.getBody(), "ok");
		
		String str = callHealthApiService.health();
		assertEquals(str, "ok");
	}
}

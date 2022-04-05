package com.zakiis.springboot.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TraceIdFilterTest {
	
	@Value("${log.trace-id.header}")
	private String traceIdHeaderName;

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(traceIdHeaderName, "Zakiis-" + System.currentTimeMillis());
		ResponseEntity<String> result = restTemplate.exchange("http://localhost:8080/health", HttpMethod.GET, new HttpEntity<String>(headers), String.class);
		assertEquals(result.getBody(), "ok");
	}
}

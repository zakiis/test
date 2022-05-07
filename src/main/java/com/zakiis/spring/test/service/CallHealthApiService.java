package com.zakiis.spring.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8080", name="FAKE-APP")
public interface CallHealthApiService  {

	@GetMapping("/health")
	public String health();
}

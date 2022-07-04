package com.zakiis.spring.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zakiis.common.ContextUtil;
import com.zakiis.security.annotation.Permission;

@RestController
@RequestMapping("/")
public class HealthController {
	
	@GetMapping("/health")
	@Permission(bypass = true)
	public String health() throws InterruptedException {
		ContextUtil.put("test", "123");
		return "ok";
	}
}

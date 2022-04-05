package com.zakiis.springboot.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

	@GetMapping("/health")
	public String health() {
		return "ok";
	}
}

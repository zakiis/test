package com.zakiis.spring.test.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zakiis.spring.test.model.User;

@FeignClient(url = "http://fake-address", name="FAKE-USER-SERVICE")
public interface CallUserApiService {

	@GetMapping("/api/v1/user/{userId}")
	User getUserById(@RequestParam("userId")Long userId);
	
	@GetMapping("/api/v1/user/selectAll")
	List<User> selectAll();
}

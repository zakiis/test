package com.zakiis.spring.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.zakiis.common.JsonUtil;
import com.zakiis.spring.test.model.User;
import com.zakiis.spring.test.service.CallUserApiService;

@SpringBootTest
public class DamBoardTest {
	
	@Autowired
	CallUserApiService callUserApiService;
	
	@Autowired
	RestTemplate restTemplate;

	@Test
	public void test() {
		User user = callUserApiService.getUserById(123L);
		System.out.println(JsonUtil.toJson(user));
		List<User> users = callUserApiService.selectAll();
		System.out.println(JsonUtil.toJson(users));
		
		User user2 = restTemplate.postForObject("http://fake-url/api/v1/user/123", null, User.class);
		System.out.println(JsonUtil.toJson(user2));
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange("http://fake-url/api/v1/user/selectAll", HttpMethod.POST, null, new ParameterizedTypeReference<List<User>>() {});
		System.out.println(JsonUtil.toJson(responseEntity.getBody()));
	}
}

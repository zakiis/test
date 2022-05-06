package com.zakiis.springboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.zakiis.common.constants.CommonConstants;
import com.zakiis.springboot.test.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthorizationTest {

	@Autowired
	RestTemplate restTemplate;
	
	@Test
	public void test() {
		User user = new User();
		user.setUsername("zhangsan");
		user.setPassword("123456");
		ResponseEntity<Object> userReponse = restTemplate.postForEntity("http://localhost:8080/user/login", user, Object.class);
		String jwtToken = userReponse.getHeaders().get(CommonConstants.JWT_TOKEN).get(0);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add(CommonConstants.JWT_TOKEN, jwtToken);
		user.setPassword("654321");
		HttpEntity<User> httpEntity = new HttpEntity<User>(user, httpHeaders);
		String changePasswordResponse = restTemplate.postForObject("http://localhost:8080/user/change-password", httpEntity, String.class);
		System.out.println("change password response: " + changePasswordResponse);
		
		HttpEntity<Object> httpEntity2 = new HttpEntity<Object>(null, httpHeaders);
		String userListResponse = restTemplate.postForObject("http://localhost:8080/user/list", httpEntity2, String.class);
		System.out.println("user list response: " + userListResponse);
	}
}

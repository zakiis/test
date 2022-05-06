package com.zakiis.springboot.test.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zakiis.common.constants.CommonConstants;
import com.zakiis.security.annotation.Permission;
import com.zakiis.security.jwt.JWTUtil;
import com.zakiis.security.jwt.algorithm.Algorithm;
import com.zakiis.springboot.test.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/login")
	@Permission(bypass = true)
	public void login(User user, HttpServletResponse response) {
		user.setId(1L);
		String jwtToken = JWTUtil.create()
				.withSubject(user.getId().toString())
				.withClaim("roles", "USER_QUERY")
				.sign(Algorithm.HMAC256("123456"));
		response.addHeader(CommonConstants.JWT_TOKEN, jwtToken);
		log.info("login success");	
	}
	
	@PostMapping("/change-password")
	@Permission(roles = {"USER_QUERY", "USER_MANAGE"})
	public String changePassword(User user) {
		log.info("change password success");
		return "ok";
	}
	
	
	@PostMapping("/list")
	@Permission(roles = "USER_MANAGE")
	public List<User> userList() {
		log.info("query user list success");
		return Collections.emptyList();
	}
}

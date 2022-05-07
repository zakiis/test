package com.zakiis.spring.test.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zakiis.common.constants.CommonConstants;
import com.zakiis.security.jwt.JWTUtil;
import com.zakiis.security.jwt.algorithm.Algorithm;
import com.zakiis.security.jwt.interfaces.DecodedJwt;
import com.zakiis.spring.Realm;

@Service
public class JWTRealm implements Realm {
	
	@Override
	public List<String> getRoles(HttpServletRequest request) {
		String jwtToken = request.getHeader(CommonConstants.JWT_TOKEN);
		if (StringUtils.isEmpty(jwtToken)) {
			return Collections.emptyList();
		}
		DecodedJwt decodedJwt = JWTUtil.decode(jwtToken);
		Long userId = Long.valueOf(decodedJwt.getSubject());
		String password = getPassword(userId);
		JWTUtil.require(Algorithm.HMAC256(password)).verify(decodedJwt);
		return Arrays.asList(decodedJwt.getClaim("roles").split(","));
	}

	private String getPassword(Long userId) {
		return "123456";
	}

}

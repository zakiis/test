package com.zakiis.springboot.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DesensitizationTest {

	Logger log = LoggerFactory.getLogger(DesensitizationTest.class);
	
	@Test
	public void test() {
		log.info("{\"userName\":\"zhangsan123\", \"password\":\"123456\", \"mobile\":\"13112341234\", \"sex\":\"male\", \"country\":\"China\", \"age\":65}");
		log.info("{userName=zhangsan123, age = , password=123456, mobile = 13112341234, sex=male, country=China}");
		log.info("add user start, param: {\"userName\":\"zhangsan123\", \"password\":\"123456\", \"mobile\":\"13112341234\", \"sex\":\"male\", \"country\":\"China\", \"age\":65}");
		log.info("add user start, params: {userName=zhangsan123, age = , password=123456, mobile = 13112341234, sex=male, country=China}");
	}
}

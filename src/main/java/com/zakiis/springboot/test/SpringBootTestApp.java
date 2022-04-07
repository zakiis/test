package com.zakiis.springboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootTestApp {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApp.class, args);
	}

}

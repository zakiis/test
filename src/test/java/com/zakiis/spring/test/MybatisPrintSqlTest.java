package com.zakiis.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zakiis.common.JsonUtil;
import com.zakiis.spring.test.bo.UserBO;
import com.zakiis.spring.test.mapper.AddressMapper;
import com.zakiis.spring.test.mapper.UserMapper;
import com.zakiis.spring.test.model.Address;
import com.zakiis.spring.test.model.User;

@SpringBootTest
public class MybatisPrintSqlTest {

	@Autowired
	AddressMapper addressMapper;
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void test() {
		Address address = addressMapper.selectByPrimaryKey(1L);
		System.out.println(JsonUtil.toJson(address));
		
		User userModel = new User();
		userModel.setUsername("ZhangSan");
		userModel.setPassword("123456");
		UserBO userBO = userMapper.selectUserBOByExample(userModel);
		System.out.println(JsonUtil.toJson(userBO));
		
		UserBO userBO2 = userMapper.selectUserBOByNameAndCity("张三", "深圳");
		System.out.println(JsonUtil.toJson(userBO2));
	}
}

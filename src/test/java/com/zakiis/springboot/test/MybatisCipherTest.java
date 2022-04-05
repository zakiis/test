package com.zakiis.springboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zakiis.common.JsonUtil;
import com.zakiis.springboot.test.bo.UserBO;
import com.zakiis.springboot.test.mapper.AddressMapper;
import com.zakiis.springboot.test.mapper.UserMapper;
import com.zakiis.springboot.test.model.Address;
import com.zakiis.springboot.test.model.User;

@SpringBootTest
public class MybatisCipherTest {

	@Autowired
	AddressMapper addressMapper;
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void test() {
		userMapper.truncate();
		addressMapper.truncate();
		
		Address addressModel = new Address();
		addressModel.setCountry("中国");
		addressModel.setProvince("广东");
		addressModel.setCity("深圳");
		addressModel.setRegion("罗湖");
		addressModel.setStreet("黄贝");
		addressModel.setDetails("黄贝岭3路1号");
		addressMapper.insert(addressModel);
		
		User userModel = new User();
		userModel.setName("张三");
		userModel.setPassword("123456");
		userModel.setUsername("ZhangSan");
		userModel.setResAddressId(addressModel.getId());
		userMapper.insert(userModel);
		
		UserBO userBO = userMapper.selectUserBOByPrimaryKey(1L);
		System.out.println(JsonUtil.toJson(userBO));
	}
}

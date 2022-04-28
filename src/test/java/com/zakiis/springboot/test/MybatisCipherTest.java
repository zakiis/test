package com.zakiis.springboot.test;

import java.util.List;
import java.util.stream.Collectors;

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
		prepareData();
		UserBO userBO = userMapper.selectUserBOByPrimaryKey(1L);
		System.out.println(JsonUtil.toJson(userBO));
		
		// no filter required if the criteria length is a multiple of 2 in full-width characters case or a multiple of 4 in half-width characters case. 
		System.out.println("===========fuzzy query 黄贝");
		Address addressModel = new Address();
		addressModel.setDetails("黄贝");
		List<Address> addressList1 = addressMapper.selectByModel(addressModel);
		System.out.println(JsonUtil.toJson(addressList1));
		
		// filter required in other cases of fuzzy query.
		System.out.println("===========fuzzy query 黄贝村");
		addressModel = new Address();
		addressModel.setDetails("黄贝村");
		addressList1 = addressMapper.selectByModel(addressModel);
		System.out.println("未经过筛选前：" + JsonUtil.toJson(addressList1));
		List<Address> addressList2 = addressList1.stream()
				.filter(addr -> addr.getDetails().indexOf("黄贝村") != -1)
				.collect(Collectors.toList());
		System.out.println("经过筛选后：" + JsonUtil.toJson(addressList2));
		
		// no filter need in precise query case
		System.out.println("===========precise query 518000");
		addressModel = new Address();
		addressModel.setZipCode("518000");
		addressList1 = addressMapper.selectByModel(addressModel);
		System.out.println(JsonUtil.toJson(addressList1));
	}
	
	private void prepareData() {
		userMapper.truncate();
		addressMapper.truncate();
		
		Address addressModel = new Address();
		addressModel.setCountry("中国");
		addressModel.setProvince("广东");
		addressModel.setCity("深圳");
		addressModel.setRegion("罗湖");
		addressModel.setStreet("黄贝");
		addressModel.setZipCode("518001");
		addressModel.setDetails("黄贝岭3路1号");
		addressMapper.insert(addressModel);
		
		User userModel = new User();
		userModel.setName("张三");
		userModel.setPassword("123456");
		userModel.setUsername("ZhangSan");
		userModel.setResAddressId(addressModel.getId());
		userMapper.insert(userModel);
		
		Address addressModel2 = new Address();
		addressModel2.setCountry("中国");
		addressModel2.setProvince("广东");
		addressModel2.setCity("深圳");
		addressModel2.setRegion("罗湖");
		addressModel2.setStreet("黄贝");
		addressModel2.setZipCode("518001");
		addressModel2.setDetails("黄贝村1巷");
		addressMapper.insert(addressModel2);
		
		User userModel2 = new User();
		userModel2.setName("李四");
		userModel2.setPassword("123465");
		userModel2.setUsername("LiSi");
		userModel2.setResAddressId(addressModel2.getId());
		userMapper.insert(userModel2);
		
		Address addressModel3 = new Address();
		addressModel3.setCountry("中国");
		addressModel3.setProvince("广东");
		addressModel3.setCity("深圳");
		addressModel3.setRegion("福田");
		addressModel3.setStreet("香蜜湖");
		addressModel3.setZipCode("518000");
		addressModel3.setDetails("福田区农林路1号");
		addressMapper.insert(addressModel3);
		
		User userModel3 = new User();
		userModel3.setName("王五");
		userModel3.setPassword("123462");
		userModel3.setUsername("Wangwu");
		userModel3.setResAddressId(addressModel3.getId());
		userMapper.insert(userModel3);
	}
}

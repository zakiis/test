package com.zakiis.springboot.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zakiis.springboot.test.model.Address;

@Mapper
public interface AddressMapper {
    int deleteByPrimaryKey(Long id);
    
    int truncate();

    int insert(Address row);

    int insertSelective(Address row);

    Address selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Address row);

    int updateByPrimaryKey(Address row);
}
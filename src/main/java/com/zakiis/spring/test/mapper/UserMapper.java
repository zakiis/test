package com.zakiis.spring.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zakiis.spring.test.bo.UserBO;
import com.zakiis.spring.test.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    
    int truncate();

    int insert(User row);

    int insertSelective(User row);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);
    
    UserBO selectUserBOByPrimaryKey(Long id);
    
    UserBO selectUserBOByExample(User user);
    
    UserBO selectUserBOByNameAndCity(String name, String city); 
}
package com.zakiis.spring.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zakiis.spring.test.model.Archive;

@Mapper
public interface ArchiveMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Archive row);

    int insertSelective(Archive row);

    Archive selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Archive row);

    int updateByPrimaryKeyWithBLOBs(Archive row);

    int updateByPrimaryKey(Archive row);
}
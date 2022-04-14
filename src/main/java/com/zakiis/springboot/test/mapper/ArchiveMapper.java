package com.zakiis.springboot.test.mapper;

import com.zakiis.springboot.test.model.Archive;
import org.apache.ibatis.annotations.Mapper;

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
package com.zakiis.spring.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zakiis.spring.test.mapper.ArchiveMapper;
import com.zakiis.spring.test.model.Archive;

@SpringBootTest
public class MysqlTimeZoneTest {

	@Autowired
	ArchiveMapper archiveMapper;
	
	@Test
	public void test() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("1981-7-27");
		
		Archive archive = new Archive();
		archive.setField1("{\"key1\":\"value1\"}");
		archive.setField2(BigDecimal.TEN);
		archive.setAddTime(date);
		archive.setAddTime2(date);
		archive.setAddTime3(date);
		archiveMapper.insert(archive);
	}
}

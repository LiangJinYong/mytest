package com.jdbc;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class Demo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
//		String sql = "SELECT * FROM app_version WHERE os_type = ? AND version_key = (SELECT MAX(version_key) FROM app_version WHERE os_type = ?)";
//		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[] {"Android", "Android"});
//		System.out.println(map);
		
		Date date = new Date();
		System.out.println(date.getTime());
		
//		long mstime = System.currentTimeMillis();
//		long seconds = mstime / 1000;
//		double decimal = (mstime - (seconds * 1000)) / 1000d;
//		System.out.println(decimal + " " + seconds);
		
		long mstime = System.currentTimeMillis();
		float seconds = mstime / 1000;
		float decimal = (mstime - (seconds * 1000)) / 1000;
		System.out.println(decimal + " " + seconds);
		
	}
}

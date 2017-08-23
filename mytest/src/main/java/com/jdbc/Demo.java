package com.jdbc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

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
	
	@Test
	public void testDate() throws ParseException {
		
		System.out.println("Demo.testDate()");
		String sql = "SELECT CODE_EXPIRE_DT FROM lian_order_info";

		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		
		String expireDate = (String) list.get(0).get("CODE_EXPIRE_DT");
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		
		Date date = df.parse(expireDate);
		
		Date today = new Date();
		
		if (date.before(today)) {
			
		} else {
			
		}
		
		System.out.println(date);

	}
	
	public static void main(String[] args) {
		System.out.println("sdf");
	}
}

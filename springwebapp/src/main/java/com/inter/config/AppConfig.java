package com.inter.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.inter.controller", "com.inter.service", "com.inter.dao"})
public class AppConfig {

	@Bean
	public DataSource dataSource() {
	
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://219.248.136.26:3306/lianshu");
		dataSource.setUsername("yuyang");
		dataSource.setPassword("1234");
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}

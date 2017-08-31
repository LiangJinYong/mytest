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
@ComponentScan(basePackages= {"com.inter.consumer.controller", "com.inter.consumer.service", "com.inter.consumer.dao", "com.inter.enterprise.controller", "com.inter.enterprise.service", "com.inter.enterprise.dao"})
public class AppConfig {

	@Bean
	public DataSource dataSource() {
	
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://219.248.136.26:3306/lianshu");
		dataSource.setUsername("yuyang");
		dataSource.setPassword("1234");
		dataSource.setInitialSize(80);
		
		return dataSource;
	}
	
	/*
	@Bean
	public JndiObjectFactoryBean dataSource2() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/appInterface");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}
	*/
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}

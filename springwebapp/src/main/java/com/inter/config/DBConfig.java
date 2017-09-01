package com.inter.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DBConfig {
	@Value("${database.driverClass}")
	private String driverClass;
	@Value("${database.jdbcUrl}")
	private String jdbcUrl;
	@Value("${database.user}")
	private String user;
	@Value("${database.password}")
	private String password;
	@Value("${database.initialPoolSize}")
	private int initialPoolSize;
	@Value("${database.minPoolSize}")
	private int minPoolSize;
	@Value("${database.maxPoolSize}")
	private int maxPoolSize;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setInitialPoolSize(initialPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setMaxPoolSize(maxPoolSize);
		
		return dataSource;
	}
	
	@Value("${local.database.driverClass}")
	private String localDriverClass;
	@Value("${local.database.jdbcUrl}")
	private String localJdbcUrl;
	@Value("${local.database.user}")
	private String localUser;
	@Value("${local.database.password}")
	private String localPassword;
	@Value("${local.database.initialPoolSize}")
	private int localInitialPoolSize;
	@Value("${local.database.minPoolSize}")
	private int localMinPoolSize;
	@Value("${local.database.maxPoolSize}")
	private int localMaxPoolSize;
	
	@Bean
	public DataSource localDataSource() throws PropertyVetoException {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setDriverClass(localDriverClass);
		dataSource.setJdbcUrl(localJdbcUrl);
		dataSource.setUser(localUser);
		dataSource.setPassword(localPassword);
		dataSource.setInitialPoolSize(localInitialPoolSize);
		dataSource.setMinPoolSize(localMinPoolSize);
		dataSource.setMaxPoolSize(localMaxPoolSize);
		
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
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public NamedParameterJdbcTemplate localJdbcTemplate(@Qualifier("localDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}

package com.app.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringMyBatisTest {
	
	@Autowired
	private ApplicationContext context;

	@Test
	public void test() {
		String[] names = context.getBeanDefinitionNames();
		for(String name : names) {
			System.err.println(name);
		}
		System.out.println("==>" + System.getProperty("java.class.path"));
	}

}

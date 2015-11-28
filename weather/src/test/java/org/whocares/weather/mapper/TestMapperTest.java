package org.whocares.weather.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.whocares.weather.client.mapper.TestMapper;

public class TestMapperTest {

	private TestMapper testMapper;
	
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.testMapper = context.getBean("testMapper", TestMapper.class);
	}
	
	@Test
	public void testQuery() {
		org.whocares.weather.client.entity.Test test = this.testMapper.query("id1");
		System.out.println(test.getId() + " : " + test.getContent());
	}
}

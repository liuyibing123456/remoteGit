package org.whocares.weather.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.StringHttpMessageConverter;

public class SystemTest {
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml", "applicationContext.xml");
		StringHttpMessageConverter instance = context.getBean("stringHttpMessageConverter", StringHttpMessageConverter.class);
		System.out.println(instance.toString() + "-----------------------------------=========================");
	}

}

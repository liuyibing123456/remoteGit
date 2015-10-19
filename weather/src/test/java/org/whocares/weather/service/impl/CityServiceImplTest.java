package org.whocares.weather.service.impl;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.whocares.weather.service.ICityService;

public class CityServiceImplTest {

	@Test
	public void testQueryCityInfo() {
		ICityService service = new CityServiceImpl();
//		service.queryCityInfo("����");
		service.queryCityInfo("\u5317\u4EAC");
	}
	
	@Test
	public void testQueryCityList() {
		ICityService service = new CityServiceImpl();
		service.queryCityList("����");
		//service.queryCityList("haidian");
	}
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("==========================");
//		RedisTemplate<String, String> redisTemplate = context.getBean("redisTemplate", StringRedisTemplate.class);
		CityServiceImpl service = context.getBean("cityService", CityServiceImpl.class);
		System.out.println("==========================");
		System.out.println("����".hashCode());
		System.out.println("����".hashCode());
	}

}

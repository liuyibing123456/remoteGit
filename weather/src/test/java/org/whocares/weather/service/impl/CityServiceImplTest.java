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
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ICityService service = context.getBean("cityService", CityServiceImpl.class);
		service.queryCityInfo("北京");
	}
	
	@Test
	public void testQueryCityList() {
		ICityService service = new CityServiceImpl();
		service.queryCityList("海淀");
		//service.queryCityList("haidian");
		String repsonse = "{\"errNum\": 0, \"retMsg\": \"success\", \"retData\": { \"cityName\": \"北京\", \"provinceName\": \"北京\", \"cityCode\": \"101010100\", \"zipCode\": \"100000\", \"telAreaCode\": \"010\" } } ";
	}
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("==========================");
//		RedisTemplate<String, String> redisTemplate = context.getBean("redisTemplate", StringRedisTemplate.class);
		RedisTemplate<String, String> template = context.getBean("redisTemplate", RedisTemplate.class);
		String value = template.boundValueOps("org.whocares.tes").get();
		System.out.println(value == null);
		System.out.println("==========================");
	}

}

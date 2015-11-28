package org.whocares.weather.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.whocares.weather.app.entity.city.City;
import org.whocares.weather.app.service.ICityService;
import org.whocares.weather.app.service.impl.CityServiceImpl;
import org.whocares.weather.util.GlobalUtils;

import com.fasterxml.jackson.core.type.TypeReference;

public class CityServiceImplTest {
	
	private ICityService cityService;
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		cityService = context.getBean("cityService", CityServiceImpl.class);
	}
	
	@Test
	public void testQueryProvinceList() {
		String str = cityService.queryProvinceList();
		System.out.println(str.split(",").length);
		System.out.println(str);
	}
	
	@Test
	public void testQueryCityList() {
		String province = "吉林";
		String str = cityService.queryCityList(province);
		System.out.println(str.split(",").length);
		System.out.println(str);
	}
	
	@Test
	public void testQueryDistrictList() {
		String province = "吉林";
		String city = "白城";
		String str = cityService.queryDistrictList(province, city);
		System.out.println(str.split(",").length);
		System.out.println(str);
	}
	
	@Test
	public void testCacheCityInfo() {
		cityService.cacheCityInfo();
		
	}

	@Test
	public void test() {
		String jsonStr = GlobalUtils.readFileAsString(GlobalUtils.CITYCODE_JSON);
		System.out.println(jsonStr);
//		ArrayList list = GlobalUtils.parseResponseJson(jsonStr, ArrayList.class);
//		System.out.println(list.getDistrictId());
		ArrayList<City> list = (ArrayList<City>)GlobalUtils.parseResponseListJson(jsonStr, City.class);
		System.out.println(list);
		for(City city : list) {
			System.out.println(city.getDistrictId());
		}
	}

}

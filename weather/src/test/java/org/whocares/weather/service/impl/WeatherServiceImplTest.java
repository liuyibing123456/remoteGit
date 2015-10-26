package org.whocares.weather.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.whocares.weather.entity.HeWeather;

public class WeatherServiceImplTest {

	private ApplicationContext context;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testQueryWeatherInfoToCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		weatherService.queryWeatherInfoToCache("CN101010100");
	}
	
	@Test
	public void testQueryCityByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.queryCityByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryRealTimeWeatherByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.queryRealTimeWeatherByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryAirQualityByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.queryAirQualityByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQuerySuggestionByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.querySuggestionByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryDailyWeatherByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.queryDailyWeatherByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryHourlyWeatherByCache() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		String retMsg = weatherService.queryHourlyWeatherByCache("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testGetObjectFromHttp() {
		WeatherServiceImpl weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
		try {
			Method method = weatherService.getClass().getDeclaredMethod("getObjectFromHttp", String.class);
			method.setAccessible(true);
			HeWeather heWeather = (HeWeather) method.invoke(weatherService, "CN101010100");
			System.out.println("===========" + heWeather.getStatus());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

}

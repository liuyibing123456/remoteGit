package org.whocares.weather.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.whocares.weather.entity.HeWeather;
import org.whocares.weather.service.IWeatherService;

public class WeatherServiceImplTest {

	private IWeatherService weatherService;
	
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		weatherService = context.getBean("weatherService", WeatherServiceImpl.class);
	}
	
	@After
	public void destory() {
	}
	
	@Test
	public void testQueryCityInfo() {
		String retMsg = weatherService.queryCityInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryRealTimeWeatherInfo() {
		String retMsg = weatherService.queryRealTimeWeatherInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryAirQualityInfo() {
		String retMsg = weatherService.queryAirQualityInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQuerySuggestionInfo() {
		String retMsg = weatherService.querySuggestionInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryFutureWeatherInfo() {
		String retMsg = weatherService.queryFutureWeatherInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryHistoryWeatherInfo() {
		String retMsg = weatherService.queryHistoryWeatherInfo("CN101010100", new Date(), 6);
		System.out.println(retMsg);
	}
	
	@Test
	public void testQueryHourlyWeatherInfo() {
		String retMsg = weatherService.queryHourlyWeatherInfo("CN101010100");
		System.out.println(retMsg);
	}
	
	@Test
	public void testGetObjectFromHttp() {
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

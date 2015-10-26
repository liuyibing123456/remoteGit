package org.whocares.weather.entity;

import org.junit.Test;
import org.whocares.weather.util.GlobalUtils;

public class RealTimeWeatherTest {
	
	@Test
	public void test() {
		String json = GlobalUtils.readFileAsString("realTimeWeather.json");
		System.out.println(json);
		RealTimeWeather instance = GlobalUtils.parseResponseJson(json, RealTimeWeather.class);
		String retMsg = GlobalUtils.parseOject2Json(instance);
		System.out.println(retMsg);
	}
}


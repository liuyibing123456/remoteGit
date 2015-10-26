package org.whocares.weather.entity;

import org.junit.Test;
import org.whocares.weather.util.GlobalUtils;

public class DailyWeatherTest {

	@Test
	public void test() {
		String json = GlobalUtils.readFileAsString("dailyWeather.json");
		System.out.println(json);
		DailyWeather instance = GlobalUtils.parseResponseJson(json, DailyWeather.class);
		String retMsg = GlobalUtils.parseOject2Json(instance);
		System.out.println(retMsg);
	}
}
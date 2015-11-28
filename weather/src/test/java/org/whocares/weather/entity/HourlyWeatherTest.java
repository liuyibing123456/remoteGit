package org.whocares.weather.entity;

import org.junit.Test;
import org.whocares.weather.app.entity.weather.HourlyWeather;
import org.whocares.weather.util.GlobalUtils;

public class HourlyWeatherTest {

	@Test
	public void test() {
		String json = GlobalUtils.readFileAsString("hourlyWeather.json");
		System.out.println(json);
		HourlyWeather instance = GlobalUtils.parseResponseJson(json, HourlyWeather.class);
		String retMsg = GlobalUtils.parseOject2Json(instance);
		System.out.println(retMsg);
	}
}

package org.whocares.weather.entity;

import org.junit.Test;
import org.whocares.weather.util.GlobalUtils;

public class CityTest {
	@Test
	public void test() {
		String json = GlobalUtils.readFileAsString("basic.json");
		System.out.println(json);
		City instance = GlobalUtils.parseResponseJson(json, City.class);
		String retMsg = GlobalUtils.parseOject2Json(instance);
		System.out.println(retMsg);
	}
}

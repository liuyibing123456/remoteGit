package org.whocares.weather.entity;

import org.junit.Test;
import org.whocares.weather.util.GlobalUtils;

public class SuggestionTest {

	@Test
	public void test() {
		String json = GlobalUtils.readFileAsString("suggestion.json");
		System.out.println(json);
		Suggestion instance = GlobalUtils.parseResponseJson(json, Suggestion.class);
		String retMsg = GlobalUtils.parseOject2Json(instance);
		System.out.println(retMsg);
	}
}

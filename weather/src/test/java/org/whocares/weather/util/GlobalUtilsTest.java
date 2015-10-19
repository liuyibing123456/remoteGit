package org.whocares.weather.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

import org.whocares.weather.util.GlobalUtils;

public class GlobalUtilsTest {

	@Test
	public void testGetPropVal() {
		assertEquals(
				GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"),
				"1e9c449b4bf78ee4bd95fc59a5d5b99c");

		assertNull(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS,
				"url.citylist.null"));
	}
	
	@Test
	public void testHttpConnection() {
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.weather.city_id");
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", "1e9c449b4bf78ee4bd95fc59a5d5b99c");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityid", "101010100");
		String response = GlobalUtils.httpConnection(url, heads, params);
		System.out.println(response);
	}
	
	@Test
	public void testParseResponseJson() {
		String jsonStr = "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}";
		Map map = GlobalUtils.parseResponseJson(jsonStr, Map.class);
		System.out.println(map.get("uid"));
	}

}

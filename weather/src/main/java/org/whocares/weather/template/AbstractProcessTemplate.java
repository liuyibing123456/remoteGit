package org.whocares.weather.template;

import java.util.HashMap;
import java.util.Map;

import org.whocares.weather.util.GlobalUtils;

public abstract class AbstractProcessTemplate {
	
	public final String template(String urlProperty, String key, String value) {
		String url = this.getURL(urlProperty);
		Map<String, String> heads = this.getHeads();
		Map<String, String> params = this.getParams(key, value);
		
		String resMessage = this.getResponseMessage(url, heads, params);
		return this.dealResMessage(resMessage);
	}
	
	private String getURL(String property) {
		return GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, property);
	}
	
	private Map<String, String> getHeads() {
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
		return heads;
	}
	
	private Map<String, String> getParams(String key, String value) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(key, value);
		return params;
	}
	
	protected abstract String getResponseMessage(String url, Map<String, String> heads, Map<String, String> params);
	
	protected boolean cacheFlag() {
		return true;
	}
	
	private String dealResMessage(String responseStr) {
		/*
		 * 将Map值类型声明为Object，是因为json转换后有Integer类型无法转为String
		 */
		@SuppressWarnings("unchecked")
		Map<String, Object> resPackage = GlobalUtils.parseResponseJson(responseStr, Map.class);
		
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
		if (!successCode.equals(resPackage.get("errNum"))) {
			System.out.println("log...");
			return null;
		}
		return responseStr;
	}
}

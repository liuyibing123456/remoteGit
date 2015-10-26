package org.whocares.weather.template;

import java.util.Map;

import org.whocares.weather.util.GlobalUtils;

public class ProcessTemplate extends AbstractProcessTemplate {

	@Override
	public String getResponseMessage(String url, Map<String, String> heads,
			Map<String, String> params) {
		return GlobalUtils.httpConnection(url, heads, params);
	}

}

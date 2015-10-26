package org.whocares.weather.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.whocares.weather.domain.CityInfoResponsePackage;
import org.whocares.weather.service.IWeatherInfoService;
import org.whocares.weather.template.AbstractProcessTemplate;
import org.whocares.weather.template.ProcessTemplate;
import org.whocares.weather.util.GlobalUtils;

@Service("weatherInfoService")
public class WeatherInfoServiceImpl implements IWeatherInfoService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherInfoServiceImpl.class);
	
	@Override
	public String queryWeatherInfo(String cityId) {
		
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.weather.city." + cityId);
		String value = bvOper.get();
		
		if (value == null) {
			String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.weather.city");
			
			Map<String, String> heads = new HashMap<String, String>();
			heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("city", cityId);
			
			value = GlobalUtils.httpConnection(url, heads, params);
			LOGGER.debug("responseStr : " + value);
			
			Map<String, String> resPackage = GlobalUtils.parseResponseJson(value, Map.class);
			
			String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
			if (!successCode.equals(resPackage.get(null))) {
				System.out.println("log...");
				return null;
			}
			long timeout = Long.valueOf(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "global.timeout.city_info"));
			
			bvOper.set(value, timeout, TimeUnit.SECONDS);
		}
		return value;
	}

}

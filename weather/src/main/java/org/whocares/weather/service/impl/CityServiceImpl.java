package org.whocares.weather.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.whocares.weather.domain.CityInfoResponsePackage;
import org.whocares.weather.domain.CityListResponsePackage;
import org.whocares.weather.service.ICityService;
import org.whocares.weather.util.GlobalUtils;

@Service("cityService")
public class CityServiceImpl implements ICityService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Override
	public String queryCityInfo(String cityName) {
		/*
		 * 以url + 参数的hashCode值作为redis的key
		 * e.g. : url.city_info.679541 （679541为“北京”的hashcode值）
		 * 
		 * key url.city_info.679541 的两种情况
		 * 1.key不存在 ----> null
		 * 2.key值不为空
		 */
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.city_info." + cityName.hashCode());
//		ValueOperations<String, String> valOper = redisTemplate.opsForValue();
		
		String value = bvOper.get();
		
		LOGGER.debug("value : " + "url.city_info." + cityName.hashCode());
		LOGGER.debug("value : " + value);
		// value is not exsit or value is expired
		if (value == null) {
			String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.city_info");
			
			Map<String, String> heads = new HashMap<String, String>();
			heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("cityname", cityName);
			
			value = GlobalUtils.httpConnection(url, heads, params);
			LOGGER.debug("responseStr : " + value);
			
			CityInfoResponsePackage resPackage = GlobalUtils.parseResponseJson(value, CityInfoResponsePackage.class);
			
			String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
			if (!successCode.equals(resPackage.getErrNum())) {
				System.out.println("log...");
				return null;
			}
			long timeout = Long.valueOf(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "global.timeout.city_info"));
			
			bvOper.set(value, timeout, TimeUnit.SECONDS);
		}
		return value;
	}

	@Override
	public String queryCityList(String cityName) {
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.city_list");
		
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityname", cityName);
		
		String responseStr = GlobalUtils.httpConnection(url, heads, params);
		System.out.println(responseStr);
		
		CityListResponsePackage resPackage = GlobalUtils.parseResponseJson(responseStr, CityListResponsePackage.class);
		
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
		if (!successCode.equals(resPackage.getErrNum())) {
			System.out.println("log...");
			return null;
		}
		return responseStr;
	}

}

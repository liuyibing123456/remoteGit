package org.whocares.weather.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.whocares.weather.domain.CityInfoResponsePackage;
import org.whocares.weather.domain.CityListResponsePackage;
import org.whocares.weather.service.ICityService;
import org.whocares.weather.util.GlobalUtils;

@Service("cityService")
public class CityServiceImpl implements ICityService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String queryCityInfo(String cityName) {
		/*
		 * 以url + 参数的hashCode值作为redis的key
		 * e.g. : url.city_info.679541 （679541为“北京”的hashcode值）
		 */
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.city_info." + cityName.hashCode());
		String value = bvOper.get();
		if (value == null) {
//			bvOper.set(value, timeout, unit);
		}
		
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.city_info");
		
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityname", cityName);
		
		String responseStr = GlobalUtils.httpConnection(url, heads, params);
		System.out.println(responseStr);
		
		CityInfoResponsePackage resPackage = GlobalUtils.parseResponseJson(responseStr, CityInfoResponsePackage.class);
		
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
		if (!successCode.equals(resPackage.getErrNum())) {
			System.out.println("log...");
			return null;
		}
		return responseStr;
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

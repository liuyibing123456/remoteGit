package org.whocares.weather.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whocares.weather.dao.ICityDao;
import org.whocares.weather.entity.city.City;
import org.whocares.weather.service.ICityService;
import org.whocares.weather.util.GlobalUtils;

@Service("cityService")
public class CityServiceImpl implements ICityService {

	@Autowired
	private ICityDao cityDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Override
	public String queryProvinceList() {
		return cityDao.queryProvincesByCache();
	}

	@Override
	public String queryCityList(String province) {
		return cityDao.queryCitysByCache(String.valueOf(province.hashCode()));
	}

	@Override
	public String queryDistrictList(String province, String city) {
		return cityDao.queryDistrictsByCache(String.valueOf(province.hashCode()), String.valueOf(city.hashCode()));
	}

	@Override
	public void cacheCityInfo() {
		String jsonStr = GlobalUtils.readFileAsString(GlobalUtils.CITYCODE_JSON);
		LOGGER.debug(GlobalUtils.CITYCODE_JSON + "\n" + jsonStr);
		//@SuppressWarnings("unchecked")
		ArrayList<City> list = GlobalUtils.parseResponseJson(jsonStr, ArrayList.class);
		
		for(City city : list) {
			cityDao.cacheProvinces(city.getProvinceCN());
			
			cityDao.cacheCitys(String.valueOf(city.getProvinceCN().hashCode()), city.getCityCN());
			
			City instance = new City.Builder(city.getDistrictId(), city.getDistrictEN(), city.getCityCN()).build();
			cityDao.cacheDistricts(String.valueOf(city.getProvinceCN().hashCode()), 
					String.valueOf(city.getCityCN().hashCode()), GlobalUtils.parseOject2Json(instance));
		}
		
	}
	
	

}

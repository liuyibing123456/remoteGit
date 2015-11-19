package org.whocares.weather.service;

public interface ICityService {
	
	String queryProvinceList();
	
	String queryCityList(String province);
	
	String queryDistrictList(String province, String city);
	
	void cacheCityInfo();
}

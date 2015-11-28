package org.whocares.weather.app.service;

public interface ICityService {
	
	String queryProvinceList();
	
	String queryCityList(String province);
	
	String queryDistrictList(String province, String city);
	
	void cacheCityInfo();
}

package org.whocares.weather.service;

public interface ICityService {
	/**
	 * 
	 * @return
	 */
	String queryCityInfo(String cityName);
	
	String queryCityList(String cityName);
}

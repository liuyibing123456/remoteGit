package org.whocares.weather.service;

public interface IWeatherInfoService {

	String queryHistory(String cityId);
	
	String queryByCitySpell(String spell);
	
	String queryByCityName(String cityNameCN);
	
	String queryByCityId(String cityId);
}

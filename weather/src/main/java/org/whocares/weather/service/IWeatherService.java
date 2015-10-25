package org.whocares.weather.service;

public interface IWeatherService {
	
	void queryWeatherInfoToCache(String cityId);
	
	String queryCityByCache(String cityId);

	String queryRealTimeWeatherByCache(String cityId);

	String queryAirQualityByCache(String cityId);

	String querySuggestionByCache(String cityId);

	String queryDailyWeatherByCache(String cityId);

	String queryHourlyWeatherByCache(String cityId);
}
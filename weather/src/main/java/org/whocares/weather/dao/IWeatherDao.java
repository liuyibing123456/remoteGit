package org.whocares.weather.dao;

public interface IWeatherDao {
	String queryCityByCache(String cityId);
	
	String queryRealTimeWeatherByCache(String cityId);
	
	String queryAirQualityByCache(String cityId);
	
	String querySuggestionByCache(String cityId);
	
	String queryFutureWeatherByCache(String cityId);
	
	String queryDailyWeatherByCache(String cityId, String date);
	
	String queryHourlyWeatherByCache(String cityId);

}
